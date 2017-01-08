package com.ernest.reefangel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;

/**
 * Created by ernest on 2017/01/07.
 */
@Service
public class CommandService {

    private USBPort usbPort;


    @Autowired
    public CommandService(USBPort usbPort)
    {
        this.usbPort=usbPort;
    }

    public void reboot()
    {
        usbPort.write("GET /boot ");
    }

    public void feed()
    {
        usbPort.write("GET /mf ");
    }

    public String custom(String value) throws InterruptedException {
        return usbPort.write("GET /" + value);
    }
}
