/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.exceptions;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

/**
 *
 * @author 041AlikinOS
 */
public class MyConstraintViolationException extends ConstraintViolationException{

    public MyConstraintViolationException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
    public String getInterpolatedMessage() {
        Set<ConstraintViolation<?>> constraintViolations = super.getConstraintViolations();
        Set<ConstraintViolation<?>> collect = constraintViolations.stream().filter(e -> e.getExecutableParameters().equals("interpolatedMessage"))
                .collect(Collectors.toSet());
        Iterator<ConstraintViolation<?>> iterator = collect.iterator();
        String mes = null;
        if(iterator.hasNext()) {
            mes = iterator.next().toString();
        }
        return mes;
    }
   
    
}
