package com.ernest.reefangel.db.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

/**
 * Created by ernest on 2017/04/17.
 */

@Document
public class Record {

    @Indexed(unique=true)
    private LocalDateTime captureDate;
    private String temp1;
    private String temp2;
    private String temp3;
    private String ph;
    private String atoLOW;
    private String atoHIGH;
    private String em;
    private String em1;
    private String rem;
    private String bid;
    private String af;
    private String sf;
    private String waterLevel;
    private String phe;

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getAtoLOW() {
        return atoLOW;
    }

    public void setAtoLOW(String atoLOW) {
        this.atoLOW = atoLOW;
    }

    public String getAtoHIGH() {
        return atoHIGH;
    }

    public void setAtoHIGH(String atoHIGH) {
        this.atoHIGH = atoHIGH;
    }

    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public String getEm1() {
        return em1;
    }

    public void setEm1(String em1) {
        this.em1 = em1;
    }

    public String getRem() {
        return rem;
    }

    public void setRem(String rem) {
        this.rem = rem;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAf() {
        return af;
    }

    public void setAf(String af) {
        this.af = af;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getPhe() {
        return phe;
    }

    public void setPhe(String phe) {
        this.phe = phe;
    }


    public LocalDateTime getCaptureDate() {
        return captureDate;
    }

    public void setCaptureDate(LocalDateTime captureDate) {
        this.captureDate = captureDate;
    }
}
