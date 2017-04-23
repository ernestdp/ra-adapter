package com.ernest.reefangel.service;

import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.exceptions.RValidationException;
import com.ernest.reefangel.service.validators.ATOHighValidator;
import com.ernest.reefangel.service.validators.PhValidator;
import com.ernest.reefangel.service.validators.Validator;
import com.ernest.reefangel.slack.SlackPushService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ernest on 2017/04/18.
 */
@Service
public class ValidateService {

    private SlackPushService slackPushService;
    private Set<Validator> validators = new HashSet<>();
    private Logger log;

    @Autowired
    public ValidateService(SlackPushService slackPushService)
    {
        this.slackPushService = slackPushService;
        validators.add(new PhValidator());
        validators.add(new ATOHighValidator());
        this.log = Logger.getLogger(ValidateService.class);
    }

    public void validate(RA ra) {
        try
        {
            for (Validator validator : validators) {
                validator.validate(ra);
            }
        }catch (RValidationException exception)
        {
            log.error(String.format("Validation applied and a alert will be raised. %s", exception.getMessage()),exception);
            slackPushService.send(exception.getMessage());
        }
    }
}
