package com.ernest.reefangel;

import com.ernest.reefangel.domain.RA;
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

    @Autowired
    public CommandController(CommandService commandService)
    {
        this.commandService = commandService;
    }


    @RequestMapping(path = "/feed", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public void feedMode()
    {
        commandService.feed();
    }

    @RequestMapping(path = "/status/all", method = RequestMethod.GET,   produces = {"application/json", "application/xml" })
    @ResponseStatus(code = HttpStatus.OK)
    public RA statusAll() throws InterruptedException, IOException {
        return commandService.statusAll();
    }

    @RequestMapping(path = "/reboot", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public void reboot()
    {
        commandService.reboot();
    }

    @RequestMapping(path = "/water", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public void waterChange()
    {
        commandService.waterChange();
    }

    @RequestMapping(path = "/clear", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public void clear()
    {
        commandService.clear();
    }

    @RequestMapping(path = "/{command}", method = RequestMethod.GET,produces = {"text/plain"} )
    @ResponseStatus(code = HttpStatus.OK)
    public String command(@PathVariable String command) throws IOException, InterruptedException {
        return commandService.command(command);
    }


    @RequestMapping(path = "/", method = RequestMethod.GET,produces = {"text/plain"} )
    @ResponseStatus(code = HttpStatus.OK)
    public String welcome() throws IOException, InterruptedException {
        return "<h1>Reef Angel Controller Web Server</h1>";
    }


}
