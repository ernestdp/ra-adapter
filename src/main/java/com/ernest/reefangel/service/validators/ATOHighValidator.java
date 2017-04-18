package com.ernest.reefangel.service.validators;

import com.ernest.reefangel.StatusUtil;
import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.exceptions.RValidationException;

/**
 * Created by ernest on 2017/04/18.
 */
public class ATOHighValidator implements Validator<RA> {

    String off="0";


    @Override
    public void validate(RA ra) throws RValidationException {
        if(ra.getAtoHIGH()!=null) {
            if (off.equalsIgnoreCase(ra.getAtoHIGH())) {
                throw new RValidationException(String.format("Ato high status : %s", StatusUtil.atoPretty(ra.getAtoHIGH())));
            }
        }

    }
}
