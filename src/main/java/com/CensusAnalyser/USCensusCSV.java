package com.CensusAnalyser;

import com.opencsv.bean.CsvBindByName;

public class USCensusCSV {

        @CsvBindByName(column = "State")
        public String state;

        @CsvBindByName(column = "Population")
        public int population;

        @CsvBindByName(column = "Population Density")
        public int populationDensity;

        @CsvBindByName(column = "Land area")
        public int area;

        @Override
        public String toString() {
        return "USCensusCSV{" +
                "State='" + state + '\'' +
                ", Population='" + population + '\'' +
                ", Population Density='" +populationDensity  + '\'' +
                ", Land area='" + area + '\'' +
                '}';
        }


}
