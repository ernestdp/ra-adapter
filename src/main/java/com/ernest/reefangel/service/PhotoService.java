package com.ernest.reefangel.service;

import com.ernest.reefangel.slack.SlackFileUploadService;
import com.ernest.reefangel.slack.SlackPushService;
import gnu.io.SerialPort;
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
public class PhotoService {

    private SlackFileUploadService fileUploadService;
    private Logger log;
    private int cameras=3;

    @Autowired
    public PhotoService(SlackFileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
        this.log = Logger.getLogger(SlackPushService.class);
    }

    public void snapshot() throws IOException {
        log.info("Running webcams");
        for (int i = 1; i <= cameras; i++) {
            try {
                LocalDateTime localDateTime = LocalDateTime.now();
                String filename = String.format("%s_snap.png", localDateTime);
                String command = "fswebcam -d/dev/video"+(i-1)+" -r800x600 -S20 " + filename;
                log.info(command);
                String response = executeCommand(command);
                log.info("Webcam image writing to disk : " + filename);
                fileUploadService.sendFile(filename);

            }catch(Exception e){
                log.error("Webcam failure : " + e);
            }
        }
    }

    private String executeCommand(String command) {
       StringBuffer output = new StringBuffer();
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}
