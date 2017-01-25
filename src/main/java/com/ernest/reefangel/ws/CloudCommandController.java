package com.ernest.reefangel.ws;

import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.service.CloudCommandService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by ernest on 2017/01/07.
 */
@RestController
public class CloudCommandController {


    private CloudCommandService cloudCommandService;
    private Logger log;

    @Autowired
    public CloudCommandController(CloudCommandService cloudCommandService) {
        this.cloudCommandService = cloudCommandService;
        this.log = Logger.getLogger(CloudCommandController.class);
    }


    @RequestMapping(path = "/status", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.OK)
    public RA statusAll() throws InterruptedException, IOException {
        return cloudCommandService.statusAll();
    }


    @RequestMapping(path = "/{command}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public String command(@PathVariable String command) throws IOException, InterruptedException {
        String response = cloudCommandService.command(command);
        log.info(response);
        return response;
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public String welcome() throws IOException, InterruptedException {
        return "<h1>Reef Angel Controller Web Server</h1>";
    }
}
