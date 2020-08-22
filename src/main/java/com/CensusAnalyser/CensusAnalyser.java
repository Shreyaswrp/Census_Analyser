package com.CensusAnalyser;


import CensusAnalyserJarDemo.CSVBuilderException;
import CensusAnalyserJarDemo.CSVBuilderFactory;
import CensusAnalyserJarDemo.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    List<IndiaCensusCSV> censusCSVList = null;
    List<IndiaCodeCSV> codeCSVList = null;

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder= CSVBuilderFactory.createCSVBuilder();
            censusCSVList = csvBuilder.getCSVFileList(reader, IndiaCensusCSV.class);
            return censusCSVList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }
    }

    public int loadIndiaCodeData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            codeCSVList = csvBuilder.getCSVFileList(reader, IndiaCodeCSV.class);
            return codeCSVList.size();

        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM);
        }catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(),e.type.name());
        }
    }

    private <E> int getCount(Iterator<E> iterator) {
        Iterable<E> csvIterable = () -> iterator;
        return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
    }
    public String getStateWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        censusCSVList = sort(censusComparator, censusCSVList);
        return new Gson().toJson(censusCSVList);
    }

    private <E> List<E> sort(Comparator<E> csvComparator, List<E> censusList) {
        for (int i = 0; i < censusList.size() - 1; i++ ){
            for (int j = 0; j < censusList.size() - i - 1; j++ ){
                E census1 = censusList.get(j);
                E census2 = censusList.get(j + 1);
                if (csvComparator.compare(census1, census2) > 0){
                    censusList.set(j,census2);
                    censusList.set(j+1, census1);
                }
            }
        }
        return censusList;
    }
    public String getStateCodeWiseSortedCensusData() throws CensusAnalyserException {
        if (codeCSVList == null || codeCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCodeCSV> stateComparator = Comparator.comparing(census -> census.stateCode);
        codeCSVList = sort(stateComparator, codeCSVList);
        return new Gson().toJson(codeCSVList);
    }
    public String getPopulationWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
        censusCSVList = sort(censusComparator, censusCSVList);
        return new Gson().toJson(censusCSVList);
    }

    public String getDensityWiseSortedCensusData() throws CensusAnalyserException {
        if (censusCSVList == null || censusCSVList.size() == 0){
            throw new CensusAnalyserException("No Census Data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        censusCSVList = sort(censusComparator, censusCSVList);
        return new Gson().toJson(censusCSVList);
    }

    public static void main(String args[]){
        CensusAnalyser cenususAnalyser=new CensusAnalyser();
        int result= 0;
        try {
            result = cenususAnalyser.loadIndiaCodeData("./src/test/resources/IndiaStateCode.csv");
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }


}