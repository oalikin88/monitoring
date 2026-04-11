/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.monitor;

import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface MonitorModelRepo extends SvtModelsRepo<MonitorModel> {
    
}
