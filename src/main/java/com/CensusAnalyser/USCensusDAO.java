package com.CensusAnalyser;

public class USCensusDAO {

    public String stateId;
    public String state;
    public int population;
    public String totalArea;
    public String populationDensity;


    public USCensusDAO(USCensusCSV usCensusCSV) {

        stateId = usCensusCSV.stateId;
        state = usCensusCSV.state;
        population = usCensusCSV.population;
        populationDensity = usCensusCSV.populationDensity;
        totalArea = usCensusCSV.totalArea;


    }
}
