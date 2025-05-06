/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.controllers;

import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.DispatcherServlet;
import ru.gov.sfr.aos.monitoring.exceptions.MyConstraintViolationException;
import ru.gov.sfr.aos.monitoring.models.Response;

/**
 *
 * @author 041AlikinOS
 */
@Controller
public class ExceptionController implements ErrorController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final static String PRINTERSERIAL = "printer.UK_do900kbf3erfvk8qu9ub2n8iq";
    private final static String PRINTERINVENTARY = "printer.UK_hpgoobl2df6n3wh6mag0bcmgw";

    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model map) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        log.info("Http status code >> " + statusCode);
        log.info("Exception >> " + exception);
        Exception e = (Exception) request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE);
        ConstraintViolationException cause = null;
        String message = null;

        Class<?> exceptionType = (Class<?>) request.getAttribute("javax.servlet.error.exception_type");
        String errorMessage = (String) request.getAttribute("javax.servlet.error.message");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");

        log.info("exceptionType >> " + exceptionType);
        log.info("errorMessage >> " + errorMessage);
        log.info("requestUri >> " + requestUri);
        log.info("servletName >> " + servletName);

        map.addAttribute("statusCode", "ОШИБКА " + statusCode);
        if (statusCode == 404) {
            String exceptionValue = "Страницы не существует";
            map.addAttribute("exception", exceptionValue);
            return "page404";
        } else if (statusCode == 409) {
            String exceptionValue = e.getMessage();

            map.addAttribute("exception", exceptionValue);
            return "page409";
        } else if (statusCode == 403) {
            String exceptionValue = "Доступ запрещён";
            map.addAttribute("exception", exceptionValue);
            return "page403";
        } else {
            if (null != exception) {
                if (exception.getCause() instanceof ConstraintViolationException) {
                    cause = (ConstraintViolationException) exception.getCause();
                    message = cause.getConstraintViolations().iterator().next().getMessage();
                } else if (exception.getCause() instanceof DataIntegrityViolationException) {
                    Throwable dublicateFieldEntity = exception.getCause().getCause();
                    message = dublicateFieldEntity.getCause().getMessage();
                    if (message.indexOf(PRINTERSERIAL) >= 0) {
                        message = "Серийный номер " + message.substring(message.indexOf("entry") + 5, message.indexOf("for")) + " уже есть в базе данных.";
                    } else if (message.indexOf(PRINTERINVENTARY) >= 0) {
                        message = "Инвентарный номер " + message.substring(message.indexOf("entry") + 5, message.indexOf("for")) + " уже есть в базе данных.";
                    }
                }
            } else if (e instanceof BindException) {
                BindException bException = (BindException) e;
                String errorsMes = bException
                        .getBindingResult()
                        .getFieldErrors().stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toSet()).toString().replaceAll("\\[*]*", "");
                map.addAttribute("exception", errorsMes);
                return "page500";
            } else {
                try {
                    if (null != e.getMessage()) {
                        message = e.getMessage();
                    }
                } catch (Exception error) {
                    error.printStackTrace();
                    message = "Временные неполадки на сервере";
                }

            }
            map.addAttribute("exception", message);
            if(exception != null) {
                exception.getClass().getConstructors();
                exception.getCause().getClass();
                exception.getLocalizedMessage();
            }
            
            return "page500";

        }
    }
    public String getErrorPath() {
        return "/error";
    }

    @ExceptionHandler(MyConstraintViolationException.class)
    public Response handleException(MyConstraintViolationException e) {
        return new Response(e.getInterpolatedMessage());
    }
}
