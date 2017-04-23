package com.ernest.reefangel.ws;

import com.ernest.reefangel.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by ernest on 2017/04/17.
 */
@RestController
@RequestMapping(path = "/camera")
public class CamController {

    private PhotoService photoService;

    @Autowired
    public CamController(PhotoService photoService)
    {
        this.photoService = photoService;
    }

    @RequestMapping(path = "/snap",method = RequestMethod.GET)
    public void snap() throws IOException {
         photoService.snapshot();
    }
}
