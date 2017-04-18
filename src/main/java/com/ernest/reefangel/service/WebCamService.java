package com.ernest.reefangel.service;

import com.github.sarxos.webcam.Webcam;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by ernest on 2017/04/18.
 */
@Service
public class WebCamService {

    public WebCamService()
    {

    }

    public void snapshot() throws IOException {
        Webcam webcam = Webcam.getDefault();
        webcam.open();
        ImageIO.write(webcam.getImage(), "png", new File("/home/ernest/hello-world.png"));
    }
}
