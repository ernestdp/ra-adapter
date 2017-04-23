package com.ernest.reefangel.domain;

/**
 * Created by ernest on 2017/03/25.
 */
public class Status {

    public static String atoPretty(String result)
    {
        if("1".equalsIgnoreCase(result))
        {
            return "green";
        }
        return "red";
    }
}
