package com.ernest.reefangel.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by ernest on 2017/01/11.
 */
public class RA {


    @JsonProperty("ID")
    private String ID;
    @JsonProperty("T1")
    private String temp1;
    @JsonProperty("T2")
    private String temp2;
    @JsonProperty("T3")
    private String temp3;
    @JsonProperty("PH")
    private String ph;
    @JsonProperty("R")
    private String r;
    @JsonProperty("RON")
    private String relayOn;
    @JsonProperty("ROFF")
    private String relayOFF;
    @JsonProperty("ATOLOW")
    private String atoLOW;
    @JsonProperty("ATOHIGH")
    private String atoHIGH;
    @JsonProperty("EM")
    private String em;
    @JsonProperty("EM1")
    private String em1;
    @JsonProperty("REM")
    private String rem;
    @JsonProperty("BID")
    private String bid;
    @JsonProperty("AF")
    private String af;
    @JsonProperty("SF")
    private String sf;
    @JsonProperty("PHE")
    private String phe;
    @JsonProperty("WL")
    private String waterLevel;

    public RA()
    {

    }
}

