/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author 041AlikinOS
 */

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ObjectAlreadyExists extends Exception {
    
    public ObjectAlreadyExists(String message) {
        super(message);
    }
    
}
