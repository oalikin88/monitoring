/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.aspects;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ru.gov.sfr.aos.monitoring.entities.ApplicationLog;
import ru.gov.sfr.aos.monitoring.services.ApplicationLogService;

/**
 *
 * @author Alikin Oleg
 */
@Aspect
@Configuration
public class ApplicationUpdLog {
    @Autowired
    private ApplicationLogService applicationLogService;
    private Logger logger = Logger.getLogger(ApplicationLogingAspect.class.getCanonicalName());
    
    
    @Pointcut("@annotation(ru.gov.sfr.aos.monitoring.anotations.UpdLog)")
    public void pointcut() {
    }
    
 
    @Around("pointcut()")
   // @Around("execution(* ru.gov.sfr.aos.monitoring.annotation.*(..))")
    public Object around(ProceedingJoinPoint point) {
        Object result = null;
        try {
            //Execution method
            result = point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        //Save log
        saveLog(point);
        return result;
    }
    
    private void saveLog(ProceedingJoinPoint joinPoint) {
        ApplicationLog log = new ApplicationLog();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StringBuilder sb = new StringBuilder();
  
        
        sb.append("Отредактировано: ");
        sb.append(joinPoint.getArgs()[0].toString());
        log.setOperation(sb.toString());
        log.setUsername(request.getUserPrincipal().getName());
        log.setRequestTime(LocalDateTime.now());
        applicationLogService.saveApplicationLog(log);
    }
}
