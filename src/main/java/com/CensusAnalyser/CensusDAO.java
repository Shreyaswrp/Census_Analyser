package com.CensusAnalyser;

public class CensusDAO {

    public String state;
    public String stateCode;
    public int population;
    public String populationDensity;
    public String totalArea;

    public CensusDAO(IndiaCensusCSV indiaCensusCSV) {
        state = indiaCensusCSV.state;
        totalArea = indiaCensusCSV.areaInSqKm;
        populationDensity =indiaCensusCSV.densityPerSqKm;
        population = indiaCensusCSV.population;
    }

    public CensusDAO(IndiaCodeCSV indiaStateCSV){
        stateCode = indiaStateCSV.stateCode;
    }

    public CensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        stateCode = usCensusCSV.stateId;
        population = usCensusCSV.population;
        populationDensity = usCensusCSV.populationDensity;
        totalArea = usCensusCSV.totalArea;
    }
}
