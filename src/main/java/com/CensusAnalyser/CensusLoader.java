package com.CensusAnalyser;


import CensusAnalyserJarDemo.CSVBuilderException;
import CensusAnalyserJarDemo.CSVBuilderFactory;
import CensusAnalyserJarDemo.ICSVBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class CensusLoader {

    public <E> Map loadCensusData(Class<E> censusCSVClass, String... csvFilePath) throws CensusAnalyserException {

            Map<String,CensusDAO> censusMap = new HashMap<>();
            try(Reader reader = Files.newBufferedReader(Paths.get(csvFilePath[0]))){
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<E> censusCSVIterator = csvBuilder.getCSVFileIterator(reader, censusCSVClass);
            Iterable<E> censusCSVIterable = () -> censusCSVIterator;
            if(censusCSVClass.getName().equals("com.CensusAnalyser.IndiaCensusCSV")){
                StreamSupport.stream(censusCSVIterable.spliterator(), false)
                        .map(IndiaCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            }else if (censusCSVClass.getName().equals("com.CensusAnalyser.USCensusCSV")){
                StreamSupport.stream(censusCSVIterable.spliterator(), false)
                        .map(USCensusCSV.class::cast)
                        .forEach(censusCSV -> censusMap.put(censusCSV.state, new CensusDAO(censusCSV)));
            } if (csvFilePath.length == 1) return censusMap;
                this.loadIndiaCodeData(censusMap, csvFilePath[1]);
            return censusMap;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.WRONG_HEADER);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }

    private int loadIndiaCodeData(Map map, String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IndiaCodeCSV> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IndiaCodeCSV.class);
            Iterable<IndiaCodeCSV> censusCSVIterable = () -> csvFileIterator;
            StreamSupport.stream(censusCSVIterable.spliterator(), false)
                    .forEach(csvState -> map.put(csvState.stateCode, new CensusDAO(csvState)));
            return map.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    e.type.name());
        }
    }
    public Map<String, CensusDAO> loadCensusData(CensusAnalyser.Country country, String... csvFilePath) throws CensusAnalyserException {
        if (country.equals(CensusAnalyser.Country.INDIA)) {
            return this.loadCensusData(IndiaCensusCSV.class, csvFilePath);
        } else if (country.equals(CensusAnalyser.Country.US)) {
            return this.loadCensusData(USCensusCSV.class, csvFilePath);
        } else {
            throw new CensusAnalyserException("Incorrect", CensusAnalyserException.ExceptionType.INVALID_COUNTRY);
        }
    }
}
