package com.ernest.reefangel.service;

import com.ernest.reefangel.db.RecordRepository;
import com.ernest.reefangel.db.entity.Record;
import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.mapper.RecordMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.mongodb.util.JSON;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ernest on 2017/04/17.
 */
@Service
public class RecordService {

    private RecordRepository recordRepository;
    private Logger log;


    @Autowired
    public RecordService(RecordRepository recordRepository)
    {
        this.recordRepository = recordRepository;
        this.log = Logger.getLogger(RecordService.class);
    }

    public void save(RA ra)
    {
        Record record = RecordMapper.INSTANCE.raToRecording(ra);
        record.setCaptureDate(LocalDateTime.now());
        recordRepository.save(record);
        try {
            log.info(String.format("Saved : %s", new ObjectMapper().writeValueAsString(record)));
        } catch (JsonProcessingException e) {
            log.error("Error printing object : "+e);
        }
    }

    public List<Record> retrieveAll()
    {
        return recordRepository.findAll();
    }

    public List<Record> retrieveTopPh()
    {
        return recordRepository.findAll();
    }


}
