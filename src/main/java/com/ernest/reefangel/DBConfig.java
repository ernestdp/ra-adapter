package com.ernest.reefangel;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by ernest on 2017/04/17.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.ernest.reefangel.db")
public class DBConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "ra_contol";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "com.ernest.reefangel";
    }
}