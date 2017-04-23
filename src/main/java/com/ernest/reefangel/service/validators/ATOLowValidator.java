package com.ernest.reefangel.service.validators;

import com.ernest.reefangel.StatusUtil;
import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.exceptions.RValidationException;

/**
 * Created by ernest on 2017/04/18.
 */
public class ATOLowValidator implements Validator<RA> {

    String off="0";


    @Override
    public void validate(RA ra) throws RValidationException {
        if(ra.getAtoLOW()!=null) {
            if (off.equalsIgnoreCase(ra.getAtoLOW())) {
                throw new RValidationException(String.format("Ato low status : %s", StatusUtil.atoPretty(ra.getAtoLOW())));
            }
        }

    }
}
