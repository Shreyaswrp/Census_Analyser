package com.CensusAnalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyser {

    //Ability to load Indian census information from a file
    public static int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCensusCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCensusCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCensusCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();;
            int namOfEateries = 0;
            while (censusCSVIterator.hasNext()) {
                namOfEateries++;
                IndiaCensusCSV censusData = censusCSVIterator.next();
            }
            return namOfEateries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }
    }

    //Ability to load Indian States code information from a csv file
    public static int loadIndiaCodeData(String csvFilePath) throws CensusAnalyserException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBeanBuilder<IndiaCodeCSV> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(IndiaCodeCSV.class);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            CsvToBean<IndiaCodeCSV> csvToBean = csvToBeanBuilder.build();
            Iterator<IndiaCodeCSV> codeCSVIterator = csvToBean.iterator();;
            int namOfEateries = 0;
            while (codeCSVIterator.hasNext()) {
                namOfEateries++;
                IndiaCodeCSV codeData = codeCSVIterator.next();
            }
            return namOfEateries;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CODE_FILE_PROBLEM);
        } catch (RuntimeException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.WRONG_HEADER);
        }
    }

    public static void main(String args[]){

        System.out.println("Welcome to census analyser program.");
        int result = 0;
        try {
            result = CensusAnalyser.loadIndiaCodeData("./src/test/resources/IndiaStateCode.csv");
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

}
