package com.ernest.reefangel.domain;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by ernest on 2017/01/11.
 */
public class RA {

    private String id;
    private String T1;
    private String T2;
    private String T3;
    private String PH;
    private String R;
    private String RON;
    private String ROFF;
    private String ATOLOW;
    private String ATOHIGH;
    private String EM;
    private String EM1;
    private String REM;
    private String BID;
    private String AF;
    private String SF;
    private String PHE;
    private String WL;

public RA()
{

}



    public String getT1() {
        return T1;
    }

    public String getT2() {
        return T2;
    }

    public String getT3() {
        return T3;
    }

    public String getPH() {
        return PH;
    }

    public String getR() {
        return R;
    }

    public String getRON() {
        return RON;
    }

    public String getROFF() {
        return ROFF;
    }

    public String getATOLOW() {
        return ATOLOW;
    }

    public String getATOHIGH() {
        return ATOHIGH;
    }

    public String getEM() {
        return EM;
    }

    public String getEM1() {
        return EM1;
    }

    public String getREM() {
        return REM;
    }

    public String getBID() {
        return BID;
    }

    public String getAF() {
        return AF;
    }

    public String getSF() {
        return SF;
    }

    public String getPHE() {
        return PHE;
    }

    public String getWL() {
        return WL;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

