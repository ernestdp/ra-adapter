package com.ernest.reefangel.mapper;

import com.ernest.reefangel.db.entity.Record;
import com.ernest.reefangel.domain.RA;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Created by ernest on 2017/04/17.
 */
@Mapper
public interface RecordMapper {

    RecordMapper INSTANCE = Mappers.getMapper(RecordMapper.class);

    Record raToRecording(RA ra);

    RA recordingToRA(Record record);
}
