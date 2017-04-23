package com.ernest.reefangel.domain;

/**
 * Created by ernest on 2017/03/25.
 */
public class Port {

    String no;
    String status;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Port(String no, String status) {
        this.no = no;
        this.status = status;
    }

}
