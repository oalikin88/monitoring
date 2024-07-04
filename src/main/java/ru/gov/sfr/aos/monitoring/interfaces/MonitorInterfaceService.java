/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import ru.gov.sfr.aos.monitoring.entities.Monitor;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;

/**
 *
 * @author 041AlikinOS
 */

public interface MonitorInterfaceService {
    Monitor getMonitorById(Long id);
   void saveMonitor(Monitor monitor) throws ObjectAlreadyExists;
}
