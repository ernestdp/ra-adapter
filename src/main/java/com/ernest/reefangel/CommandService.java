package com.ernest.reefangel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ernest on 2017/01/07.
 */
@Service
public class CommandService {

    private USBComm usbComm;


    @Autowired
    public CommandService(USBComm usbComm)
    {
        this.usbComm=usbComm;
    }

    public void reboot()
    {
        usbComm.write(new String("GET /boot ").getBytes());
        usbComm.receivedBytes.clear();

    }

    public void feed()
    {
        usbComm.write(new String("GET /mf ").getBytes());
        usbComm.receivedBytes.clear();
    }

    public String custom(String value) {
        byte[] bytes = new byte[1024];

        try {
            usbComm.write(new String("GET /" + value +" ").getBytes("UTF-8"));
        int i=0;
        while(true) {
            System.out.println(new Date());
            final Byte take = usbComm.receivedBytes.take();
            bytes[++i]=take;
            String s = new String(bytes);
            System.out.println(s);
            if(s.trim().contains("<RA>") && s.trim().contains(("</RA>"))){
                return s.substring(s.indexOf("<RA>"),s.indexOf("</RA>"));
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
}
