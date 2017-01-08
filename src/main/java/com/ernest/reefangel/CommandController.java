package com.ernest.reefangel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ernest on 2017/01/07.
 */
@RestController
@RequestMapping(path = "reef")
public class CommandController {


    private CommandService commandService;

    @Autowired
    public CommandController(CommandService commandService)
    {
        this.commandService = commandService;
    }


    @RequestMapping(path = "/feed/{flag}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public void feedMode(@PathVariable boolean flag)
    {
        commandService.feed();
    }

    @RequestMapping(path = "/command/{value}", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public String command(@PathVariable String value) throws InterruptedException {
        return commandService.custom(value);
    }


    @RequestMapping(path = "/reboot", method = RequestMethod.GET)
    @ResponseStatus(code = HttpStatus.OK)
    public void reboot()
    {
        commandService.reboot();
    }
}
