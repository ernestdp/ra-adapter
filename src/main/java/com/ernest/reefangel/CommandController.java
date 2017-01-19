package com.ernest.reefangel;

import com.ernest.reefangel.domain.RA;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodType;

/**
 * Created by ernest on 2017/01/07.
 */
@RestController
public class CommandController {


    private CommandService commandService;
    private Logger log;

    @Autowired
    public CommandController(CommandService commandService) {
        this.commandService = commandService;
        this.log = Logger.getLogger(CommandController.class);
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
