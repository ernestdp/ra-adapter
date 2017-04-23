package com.ernest.reefangel.service;

import com.ernest.reefangel.slack.SlackFileUploadService;
import com.ernest.reefangel.slack.SlackPushService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

/**
 * Created by ernest on 2017/04/18.
 */
@Service
public class UsbService {

    private Logger log;

    @Autowired
    public UsbService(SlackFileUploadService fileUploadService) {
        this.log = Logger.getLogger(SlackPushService.class);
    }

    public String list() throws IOException {

                StringBuffer buffer = new StringBuffer();
                final String videos = executeCommand("ls /dev/video*");
                buffer.append(videos);
                buffer.append("\n");
                log.info(videos);
                final String usbs = executeCommand("ls -l /dev/ttyUSB*");
                log.info(usbs);
                buffer.append(usbs);
                return buffer.toString();
    }

    private String executeCommand(String command) {
        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.wait();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            output.append(e.getMessage() + "\n");
        }

        return output.toString();

    }
}


