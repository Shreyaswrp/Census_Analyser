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
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";
    private static final String WRONG_US_CSV_FILE_PATH = "./src/main/resources/USCensusData.csv";
    private static final String WRONG_TYPE_US_CSV_FILE_PATH = "./src/main/resources/USCensusData.pdf";
    private static final String DELIMITER_INCORRECT_US_CSV_FILE = "./src/test/resources/USCensusData/csv";


    @Test
    public void givenCensusCSVFileReturnsCorrectRecords() {
        try {
            CensusAnalyser censusAanlyser = new CensusAnalyser();
            int numberOfRecords = censusAanlyser.loadCensusData(CensusAnalyser.Country.INDIA, INDIA_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, numberOfRecords);
            System.out.println("Inside try block");
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenCensusData_WithWrongFilePath_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA, WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenCensusData_WithWrongType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,WRONG_TYPE_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,DELIMITER_INCORRECT_CSV_FILE_PATH );
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
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
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Uttar Pradesh", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
        }
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnDensityPerKm_ShouldReturnSortedResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Sikkim", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }
    @Test
    public void givenIndianCensusData_WhenSortedOnAreaPerSQKM_ShouldReturnSortedResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.INDIA,INDIA_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Bihar", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    @Test
    public void givenUSCsvFile_WhenChecked_ReturnCorrectRecords() {
        try {
            CensusAnalyser censusAanlyser = new CensusAnalyser();
            int numberOfRecords = censusAanlyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, numberOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }

    @Test
    public void givenUSCensusData_WithWrongFilePath_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, WRONG_US_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenUSCensusData_WithWrongFileType_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US, WRONG_TYPE_US_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM, e.type);
        }
    }
    @Test
    public void givenUSCensusData_WithWrongDelimiter_ShouldThrowException() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,DELIMITER_INCORRECT_US_CSV_FILE );
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.FILE_TYPE_PROBLEM,e.type);
        }
    }
    @Test
    public void givenUSCensusData_WithWrongHeader_ShouldThrowException() {
        try {
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            System.out.println("Inside try block");
        } catch (CensusAnalyserException e) {
            System.out.println("Inside catch block");
            Assert.assertEquals(CensusAnalyserException.ExceptionType.WRONG_HEADER, e.type);
        }
    }
    @Test
    public void givenUSCensusCSVFile_whenSortedSortedOnState_shouldReturnSortedResult() {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getStateWiseSortedCensusData();
            USCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Alabama", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }
    @Test
    public void givenUSCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData();
            USCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("California", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenUSCensusData_WhenSortedOnPopulationDensity_ShouldReturnSortedResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getPopulationDensityWiseSortedCensusData();
            USCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("California", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void givenUSCensusData_WhenSortedOnTotalArea_ShouldReturnSortedResult() throws CensusAnalyserException {
        try {
            CensusAnalyser censusAnalyser = new CensusAnalyser();
            censusAnalyser.loadCensusData(CensusAnalyser.Country.US,US_CENSUS_CSV_FILE_PATH);
            String stateWiseSortedCensusData = censusAnalyser.getAreaWiseSortedCensusData();
            USCensusCSV[] censusCSV = new Gson().fromJson(stateWiseSortedCensusData, USCensusCSV[].class);
            Assert.assertEquals("Indiana", censusCSV[0].state);
        } catch (CensusAnalyserException e) {
            e.printStackTrace();
        }
    }

}