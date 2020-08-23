package com.CensusAnalyser;

public class IndiaCodeDAO {

    public String stateName;
    public String stateCode;
    public int srNo;
    public int TIN;

    public IndiaCodeDAO(IndiaCodeCSV indiaCodeCSV) {
        stateCode = indiaCodeCSV.stateCode;
        stateName = indiaCodeCSV.state;
        srNo= indiaCodeCSV.srNo;
        TIN= indiaCodeCSV.TIN;
    }
}
