package com.ernest.reefangel.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

import static com.ernest.reefangel.domain.Field.*;

/**
 * Created by ernest on 2017/01/11.
 */
public class RA {


    @JsonProperty(ID)
    private String id;
    @JsonProperty(T1)
    private String temp1;
    @JsonProperty(T2)
    private String temp2;
    @JsonProperty(T3)
    private String temp3;
    @JsonProperty(PH)
    private String ph;
    @JsonProperty(R)
    private String r;
    @JsonProperty(RON)
    private String relayOn;
    @JsonProperty(ROFF)
    private String relayOFF;
    @JsonProperty(ATOLOW)
    private String atoLOW;
    @JsonProperty(ATOHIGH)
    private String atoHIGH;
    @JsonProperty(EM)
    private String em;
    @JsonProperty(EM1)
    private String em1;
    @JsonProperty(REM)
    private String rem;
    @JsonProperty(BID)
    private String bid;
    @JsonProperty(AF)
    private String af;
    @JsonProperty(SF)
    private String sf;
    @JsonProperty(WL)
    private String waterLevel;
    @JsonProperty(PHE)
    private String phe;


    public RA() {

    }

    public String getId() {
        return id;
    }

    public String getTemp1() {
        return temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public String getPh() {
        return ph;
    }

    public String getR() {
        return r;
    }

    public String getRelayOn() {
        return relayOn;
    }

    public String getRelayOFF() {
        return relayOFF;
    }

    public String getAtoLOW() {
        return atoLOW;
    }

    public String getAtoHIGH() {
        return atoHIGH;
    }

    public String getEm() {
        return em;
    }

    public String getEm1() {
        return em1;
    }

    public String getRem() {
        return rem;
    }

    public String getBid() {
        return bid;
    }

    public String getAf() {
        return af;
    }

    public String getSf() {
        return sf;
    }

    public String getWaterLevel() {
        return waterLevel;
    }

    public String getPhe() {
        return phe;
    }
}

