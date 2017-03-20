package com.ernest.reefangel.ws;

import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.service.CommandService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * Created by ernest on 2017/01/07.
 */
@RestController
@RequestMapping(path = "/ra")
public class CloudCommandController {


    private CommandService commandService;
    private Logger log;

    @Autowired
    public CloudCommandController(CommandService commandService) {
        this.commandService = commandService;
        this.log = Logger.getLogger(CloudCommandController.class);
    }


    @RequestMapping(path = "/status", method = RequestMethod.GET, produces = {"application/json", "application/xml"})
    @ResponseStatus(code = HttpStatus.OK)
    public RA statusAll() throws InterruptedException, IOException {
        return commandService.statusAll();
    }


    @RequestMapping(path = "/{command}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public String command(@PathVariable String command) throws IOException, InterruptedException {
        String response = commandService.command(command);
        log.info(response);
        return response;
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public String welcome() throws IOException, InterruptedException {
        return "<h1>Reef Angel Controller Web Server</h1>";
    }





}
