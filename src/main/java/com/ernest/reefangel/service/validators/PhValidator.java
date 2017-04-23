package com.ernest.reefangel.service.validators;

import com.ernest.reefangel.domain.RA;
import com.ernest.reefangel.exceptions.RValidationException;

/**
 * Created by ernest on 2017/04/18.
 */
public class PhValidator implements Validator<RA> {

    int min=820;
    int max=860;


    @Override
    public void validate(RA ra) throws RValidationException {
        if(ra.getPh()!=null) {
            Integer ph = Integer.valueOf(ra.getPh());
            if (ph <= min || ph >= max) {
                throw new RValidationException(String.format("PH %dout of range. [min=%d,max=%d]", ph, min, max));
            }
        }

    }
}
