package com.ernest.reefangel.ws;

import com.ernest.reefangel.db.entity.Record;
import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * Created by ernest on 2017/04/17.
 */
public class RecordController {

    private RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService)
    {
        this.recordService=recordService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void saveRecord(@RequestBody RA ra)
    {
     recordService.save(ra);
    }

    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public List<Record> retrieveAll()
    {
        return recordService.retrieveAll();
    }
}
