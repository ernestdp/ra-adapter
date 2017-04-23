package com.ernest.reefangel.db;

import com.ernest.reefangel.db.entity.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by ernest on 2017/04/17.
 */
@RepositoryRestResource(collectionResourceRel = "records", path = "records")
public interface RecordRepository extends MongoRepository<Record,LocalDateTime>
{

    List<Record> findTop3BycaptureDate();
}
