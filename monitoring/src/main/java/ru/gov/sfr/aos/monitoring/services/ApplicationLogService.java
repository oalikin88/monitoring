/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.ApplicationLog;
import ru.gov.sfr.aos.monitoring.repositories.ApplicationLogRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class ApplicationLogService {
    
    @Autowired
    private ApplicationLogRepo applicationLogRepo;
    
    public void saveApplicationLog(ApplicationLog applicationLog) {
        applicationLogRepo.save(applicationLog);
    }
    
}
