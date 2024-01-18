/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.auth.AuthenticationService;
import ru.gov.sfr.aos.monitoring.entities.CartridgeModel;
import ru.gov.sfr.aos.monitoring.models.CartridgeInstallDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeDeviceLocationDTO;
import ru.gov.sfr.aos.monitoring.models.ChangeLocationForCartridges;
import ru.gov.sfr.aos.monitoring.models.ContractFromInputDto;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.models.PrinterStatusDto;

/**
 *
 * @author 041AlikinOS
 */
@Component
@Aspect
public class AspectLog {
    
    @Autowired
    private AuthenticationService authService;
    //editPrinterLocation()
    
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Service.installCartridge(*))")
    public void installCartridgeMethod() {
    
    }
    
    
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Service.saveCartridgeModel(*))")
    public void saveCartridgeModelMethod() {
    
    }
    
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Service.changeCartridgeLocation(*))")
    public void changeCartridgeLocationMethod() {
        
    }
    
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Service.changeCartridgesLocation(*))")
    public void changeCartridgesLocationMethod() {
        
    }
    
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Service.addLocation(*))")
    public void addLocationMethod() {
        
    }
    
    //changeCartridgesLocation
    
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Mapper.editPrinterLocation(*))")
    public void changePrinterLocationMethod() {
        
    }
    
    
        @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Mapper.saveManufacturer(*))")
    public void saveManufacturerMethod() {
        
    }
    
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Mapper.saveModelByManufacturer(*))")
    public void savePrinterModelMethod() {
        
    }
    
    
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*Mapper.editPrinterStatus(*))")
    public void changePrinterStatusMethod() {
        
    }
    
    
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControlerLayer() {
    
    }
    
    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)" +
    "|| @annotation(org.springframework.web.bind.annotation.PutMapping)" +
    "|| @annotation(org.springframework.web.bind.annotation.DeleteMapping)") 
    public void mappingAnnotations() {

}
    @Pointcut("execution(public * ru.gov.sfr.aos.monitoring.services.*ServiceMapper.createContract(*))")
    public void createContractMethod() {
    
    }
    
    @AfterReturning("installCartridgeMethod() && args(dto)")
    public void addLoggingInstallCartridge(JoinPoint joinPoint,  CartridgeInstallDTO dto) {
        String username = authService.authUser().getUsername();
       Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Пользователь: " + username + " произвёл установку картриджа id = " + dto.getIdCartridge() + " в принтер id = " + dto.getIdPrinter() + ". Сотрудник выполнивший работу: " + dto.getEmployeeToDoWork() + ", за кем закреплено: " + dto.getEmployeeToSetDevice() + ", МОЛ: " + dto.getEmployeeMOL());
    }
    
    @AfterReturning("saveCartridgeModelMethod() && args(cartridgeModel)")
    public void addLogingSaveCartridgeModel(JoinPoint joinPoint, CartridgeModel cartridgeModel) {
        String username = authService.authUser().getUsername();
       Logger log = LoggerFactory.getLogger(this.getClass());
       log.info("Пользователь: " + username + ", добавил модель картриджа: " + cartridgeModel.getModel() + ", указав связь на принтеры: " + cartridgeModel.getModelsPrinters().toString());
    }
    
    @AfterReturning("addLocationMethod() && args(nameLocation)")
    public void addLogingAddLocation(JoinPoint joinPoint, String nameLocation) {
       String username = authService.authUser().getUsername();
       Logger log = LoggerFactory.getLogger(this.getClass());
       log.info("Пользователь: " + username + ", добавил локацию: " + nameLocation);
    }
    
    @AfterReturning("createContractMethod() && args(dto)")
    public void addLogingCreateContract(JoinPoint joinPoint, ContractFromInputDto dto) {
        String username = authService.authUser().getUsername();
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Пользователь: " + username + ", создал контракт с номером: " + dto.getContractNumber() + ", от " + dto.getDateStartContract() + ". Принтеров: " + dto.getPrinters().size() + ". Картриджей: " + dto.getCartridges().size());
    }
    
    @AfterReturning("saveManufacturerMethod() && args(value)")
    public void addLogingSaveManufacturer(JoinPoint joinPoint, String value) {
        String username = authService.authUser().getUsername();
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Пользователь: " + username + ", добавил производителя: " + value);
    }
    
    @AfterReturning("changeCartridgeLocationMethod() && args(dto)")
    public void addLogingChangeCartridgeLocation(JoinPoint joinPoint, ChangeDeviceLocationDTO dto) {
        String username = authService.authUser().getUsername();
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Пользователь: " + username + ", изменил локацию картриджа id = " + dto.getId() + " на " + dto.getLocation());
    }
    
    @AfterReturning("changeCartridgesLocationMethod() && args(dto)")
    public void addLogingChangeCartridgesLocation(JoinPoint joinPoint, ChangeLocationForCartridges dto) {
        String username = authService.authUser().getUsername();
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Пользователь: " + username + ", изменил локацию картриджей id = " + dto.getIdCartridge().toString() + " на " + dto.getLocation());
    }
    
    
    @AfterReturning("changePrinterLocationMethod() && args(dto)")
    public void addLogingChangePrinterLocation(JoinPoint joinPoint, ChangeDeviceLocationDTO dto) {
        String username = authService.authUser().getUsername();
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Пользователь: " + username + ", изменил локацию принтера id = " + dto.getId() + " на " + dto.getLocation());
    }
    
    @AfterReturning("changePrinterStatusMethod() && args(dto)")
    public void addLogingChangePrinterStatus(JoinPoint joinPoint, PrinterStatusDto dto) {
        String username = authService.authUser().getUsername();
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Пользователь: " + username + ", изменил статус принтера id = " + dto.getId() + " на " + dto.getStatus());
    }
    
    @AfterReturning("savePrinterModelMethod() && args(dto)")
    public void addLogingSaveModelPrinter(JoinPoint joinPoint, ModelDTO dto) {
        String username = authService.authUser().getUsername();
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("Пользователь: " + username + ", добавил модель принтера: " + dto.getManufacturer() + " " + dto.getModel());
    }
    
}
