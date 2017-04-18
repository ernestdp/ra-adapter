package com.ernest.reefangel.service;

import com.ernest.reefangel.slack.SlackFileUploadService;
import com.github.sarxos.webcam.Webcam;
import org.apache.catalina.webresources.FileResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by ernest on 2017/04/18.
 */
@Service
public class PhotoService {

    private SlackFileUploadService fileUploadService;

    @Autowired
    public PhotoService(SlackFileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }


    public BufferedImage snapshot() throws IOException {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        BufferedImage image = webcam.getImage();
        LocalDateTime localDateTime = LocalDateTime.now();
        String filename = String.format("%s_snapshot.jpg", localDateTime);
        ImageIO.write(image, "jpg", new File(filename));
        fileUploadService.sendFile(filename);

        return image;
    }
}
