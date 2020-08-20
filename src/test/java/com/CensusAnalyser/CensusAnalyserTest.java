package com.CensusAnalyser;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyserTest {

    private static final String INDIA_CENSUS_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
    private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private static final String WRONG_TYPE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.pdf";
    private static final String DELIMITER_INCORRECT_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData/csv";
    private static final String INDIA_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
    private static final String WRONG_CODE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private static final String WRONG_TYPE_CODE_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.pdf";
    private static final String DELIMITER_INCORRECT_CODE_CSV_FILE = "./src/test/resources/IndiaStateCode/csv";
    @Test
    public void givenStateCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
        } catch (CensusAnalyserException e) { }
    }

    @Test
    public void givenStateCensusData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenStateCensusData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(WRONG_TYPE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenStateCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(DELIMITER_INCORRECT_CSV_FILE_PATH );
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenStateCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            System.out.println("Inside try block");
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER, e.type);
        }
    }
    @Test
    public void givenStateCodeCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCodeData(INDIA_CODE_CSV_FILE_PATH );
            System.out.println(numOfRecords);
            Assert.assertEquals(37,numOfRecords);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
        }
    }
    @Test
    public void givenStateCodeData_WithWrongFile_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCodeData(WRONG_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenStateCodeData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCodeData(WRONG_TYPE_CODE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenStateCodeData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCodeData(DELIMITER_INCORRECT_CODE_CSV_FILE );
            System.out.println("Inside try block");
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenStateCodeData_WithWrongHeader_ShouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCodeData(INDIA_CODE_CSV_FILE_PATH);
            System.out.println("Inside try block");
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER, e.type);
        }
    }
}