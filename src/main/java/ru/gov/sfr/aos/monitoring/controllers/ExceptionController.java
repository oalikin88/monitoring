/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import ru.gov.sfr.aos.monitoring.models.ResponseDto;

/**
 *
 * @author 041AlikinOS
 */
@Controller
public class ExceptionController implements ErrorController {
    
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model map) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        log.info("Http status code >> " + statusCode);
        log.info("Exception >> " + exception);
        String status = request.getAttribute(RequestDispatcher.ERROR_MESSAGE).toString();
        Exception e = (Exception) request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE);
        System.out.println(e);
        Class<?> exceptionType = (Class<?>) request.getAttribute("javax.servlet.error.exception_type");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        log.info("exceptionType >> " + exceptionType);
        log.info("errorMessage >> " + errorMessage);
        log.info("requestUri >> " + requestUri);
        log.info("servletName >> " + servletName);
        
        map.addAttribute("statusCode", "ОШИБКА " + statusCode);
        if(statusCode == 404) {
            String exceptionValue = "Страницы не существует";
            map.addAttribute("exception", exceptionValue);
            return "page404";
        }
        else if(statusCode == 409) {
            String exceptionValue = e.getMessage();
            
            map.addAttribute("exception", exceptionValue);
            return "page409";
        }
        else {
            map.addAttribute("exception", exception);
            return "page500";
        }
    }
    
    @GetMapping("/404")
    public String objectAlreadyExist(ResponseDto dto, Model model) {
        model.addAttribute("statusCode", dto.getStatus());
        model.addAttribute("exception", dto.getMessage());
        
        
        return "page409";
    }

    
    
    
    
    public String getErrorPath() {
        return "/error";
    }
}
