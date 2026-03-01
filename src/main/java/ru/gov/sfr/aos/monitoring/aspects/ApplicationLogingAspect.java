package ru.gov.sfr.aos.monitoring.aspects;

import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.gov.sfr.aos.monitoring.entities.ApplicationLog;
import ru.gov.sfr.aos.monitoring.services.ApplicationLogService;

/**
 *
 * @author Alikin Oleg
 */
@Aspect
@Component
public class ApplicationLogingAspect {
    @Autowired
    private ApplicationLogService applicationLogService;
    private Logger logger = Logger.getLogger(ApplicationLogingAspect.class.getCanonicalName());
    
    

    @Before("execution(* ru.gov.sfr.aos.monitoring.controllers.*.*(..))")
public void logMethodAccessBefore(JoinPoint joinPoint) {
    if (RequestContextHolder.getRequestAttributes() == null) {
        return;
    }

    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    
    // 1. Безопасно получаем имя пользователя
    String username = "Anonymous";
    if (request.getUserPrincipal() != null) {
        username = request.getUserPrincipal().getName();
    }

    // 2. Безопасно получаем первый аргумент метода (если он есть)
    String firstArg = (joinPoint.getArgs().length > 0 && joinPoint.getArgs()[0] != null) 
                      ? joinPoint.getArgs()[0].toString() 
                      : "no args";

    String method = request.getMethod();
    
    // Используем switch для логирования
    switch (method) {
        case "POST":
            logger.info("Создана запись: " + firstArg + ". User: " + username);
            break;
        case "PUT":
            logger.info("Отредактирована запись: " + firstArg + ". User: " + username);
            break;
        case "DELETE":
            logger.info("Удалена запись: " + firstArg + ". User: " + username);
            break;
        case "GET":
            logger.info(username + " запросил " + joinPoint.getSignature().toShortString());
            break;
        default:
            logger.info(username + " выполнил " + method + " для " + joinPoint.getSignature().getName());
            break;
    }
}
    
    
    
    
    
    
    
    
//    @Pointcut("@annotation(ru.gov.sfr.aos.monitoring.anotations.Log)")
//    public void pointcut() {
//    }
//    
// 
//    @Around("pointcut()")
//   // @Around("execution(* ru.gov.sfr.aos.monitoring.annotation.*(..))")
//    public Object around(ProceedingJoinPoint point) {
//        Object result = null;
//        try {
//            //Execution method
//            result = point.proceed();
//            
//        } catch (Throwable e) {
//            e.printStackTrace();
//        }
//        //Save log
//        saveLog(point);
//        return result;
//    }
//    
//    private void saveLog(ProceedingJoinPoint joinPoint) {
//        ApplicationLog log = new ApplicationLog();
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        StringBuilder sb = new StringBuilder();
//        switch (request.getMethod()) {
//            case "POST":
//                sb.append("Создана запись:");
//                break;
//            case "PUT":
//                sb.append("Отредактирована запись:");
//                break;
//            case "DELETE":
//                sb.append("Удалена запись:");
//                break;
//        }
//        
//        sb.append(" ");
//        sb.append(joinPoint.getArgs()[0].toString());
//        log.setOperation(sb.toString());
//        log.setUsername(request.getUserPrincipal().getName());
//        log.setRequestTime(LocalDateTime.now());
//        applicationLogService.saveApplicationLog(log);
//    }
    
}
