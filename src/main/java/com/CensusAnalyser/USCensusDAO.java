package com.CensusAnalyser;

public class USCensusDAO {

    public int populationDensity;
    public int area;
    public String state;
    public int population;

    public USCensusDAO(USCensusCSV usCensusCSV) {
        state = usCensusCSV.state;
        population = usCensusCSV.population;
        populationDensity = usCensusCSV.populationDensity;
        area = usCensusCSV.area;

    }
}
