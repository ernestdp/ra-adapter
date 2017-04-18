package com.ernest.reefangel.ws;

import com.ernest.reefangel.db.entity.Record;
import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.service.RecordService;
import com.ernest.reefangel.service.WebCamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by ernest on 2017/04/17.
 */
@RestController
@RequestMapping(path = "/camera")
public class CamController {

    private WebCamService webCamService;

    @Autowired
    public CamController(WebCamService webCamService)
    {
        this.webCamService=webCamService;
    }

    @RequestMapping(path = "/snap",method = RequestMethod.GET)
    public void snap() throws IOException {
         webCamService.snapshot();
    }
}
