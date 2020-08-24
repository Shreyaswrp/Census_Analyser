package com.CensusAnalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {


        @CsvBindByName(column = "State Id")
        public String stateId;

        @CsvBindByName(column = "State")
        public String state;

        @CsvBindByName(column = "Population")
        public int population;

        @CsvBindByName(column = "Population Density")
        public String populationDensity;

        @CsvBindByName(column = "Total area")
        public String totalArea;






}
