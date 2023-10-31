/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.Response;

/**
 *
 * @author 041AlikinOS
 */
@ControllerAdvice
public class DefaultAdvice {
    
//     @ExceptionHandler(ObjectAlreadyExists.class)
//    public ResponseEntity<Response> handleException(ObjectAlreadyExists e) {
//        Response response = new Response(e.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//    }
//
}

