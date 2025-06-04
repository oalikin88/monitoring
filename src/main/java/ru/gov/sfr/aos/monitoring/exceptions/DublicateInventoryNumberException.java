package ru.gov.sfr.aos.monitoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Alikin Oleg
 */
@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
public class DublicateInventoryNumberException extends Exception {
    
     public DublicateInventoryNumberException(String message) {
        super(message);
    }

}
