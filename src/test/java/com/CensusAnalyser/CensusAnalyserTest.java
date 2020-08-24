package com.CensusAnalyser;

import com.google.gson.Gson;
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
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";

    @Test
    public void givenStateCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);
            System.out.println("Inside try block");
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
            System.out.println("Inside catch block");
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
    public void givenStateCodeData_WhenWrongHeader_ShouldThrowException() {
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

    @Test
    public void givenIndianCensusCSVFile_whenSortedSortedOnState_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndiaStateCodeFile_whenSortedSortedOnStateCode_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadIndiaCodeData(INDIA_CODE_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getStateCodeWiseSortedCensusData();
            IndiaCodeCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCodeCSV[].class);
            Assert.assertEquals("AD", censusCSV[0].stateCode);
        } catch (CensusAnalyserException e) {
        }
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
        IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(607688, CensusCSV[0].population);
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnDensityPerKm_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getDensityWiseSortedCensusData();
        IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(50, CensusCSV[0].densityPerSqKm);
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnAreaPerSQKM_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
        IndiaCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
        Assert.assertEquals(3702, CensusCSV[0].areaInSqKm);
    }

    @Test
    public void givenUSCsvFile_WhenChecked_ReturnCorrectRecords() {
        try {
            CensusAnalyser censusAanlyser = new CensusAnalyser();
            int numberOfRecords = censusAanlyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
            System.out.println("Inside try block");
            Assert.assertEquals(51, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationWiseSortedUSCensusData();
        USCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
        Assert.assertEquals(563626, CensusCSV[0].population);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getPopulationDensityWiseSortedUSCensusData();
        USCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
        Assert.assertEquals("0.46", CensusCSV[0].populationDensity);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnTotalArea_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getAreaWiseSortedUSCensusData();
        USCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
        Assert.assertEquals("104655.80", CensusCSV[0].totalArea);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnStateId_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getStateIdWiseSortedUSCensusData();
        USCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
        Assert.assertEquals("AK", CensusCSV[0].stateId);
    }

    @Test
    public void givenUSCensusData_WhenSortedOnState_ShouldReturnSortedResult() throws CensusAnalyserException {
        CensusAnalyser censusAnalyser = new CensusAnalyser();
        censusAnalyser.loadUSCensusData(US_CENSUS_CSV_FILE_PATH);
        String sortedCensusData = censusAnalyser.getStateWiseSortedUSCensusData();
        USCensusCSV[] CensusCSV = new Gson().fromJson(sortedCensusData, USCensusCSV[].class);
        Assert.assertEquals("Alabama", CensusCSV[0].state);
    }
}