/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Asuo;
import ru.gov.sfr.aos.monitoring.entities.Ats;
import ru.gov.sfr.aos.monitoring.entities.Conditioner;
import ru.gov.sfr.aos.monitoring.entities.Display;
import ru.gov.sfr.aos.monitoring.entities.Fax;
import ru.gov.sfr.aos.monitoring.entities.Infomat;
import ru.gov.sfr.aos.monitoring.entities.Monitor;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;
import ru.gov.sfr.aos.monitoring.entities.Phone;
import ru.gov.sfr.aos.monitoring.entities.Router;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.entities.Server;
import ru.gov.sfr.aos.monitoring.entities.SwitchHub;
import ru.gov.sfr.aos.monitoring.entities.SwitchingUnit;
import ru.gov.sfr.aos.monitoring.entities.SystemBlock;
import ru.gov.sfr.aos.monitoring.entities.Terminal;
import ru.gov.sfr.aos.monitoring.entities.ThermoPrinter;
import ru.gov.sfr.aos.monitoring.entities.Ups;
import ru.gov.sfr.aos.monitoring.enums.ObjectBuingType;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class ObjectBuingFactory {
    
    public ObjectBuing getSvtObj(ObjectBuingType objType) {
        ObjectBuing ob = null;
        switch (objType) {
            case ATS:
                 ob = new Ats();
                break;
            case ASUO:
                 ob = new Asuo();
                break;
            case CONDITIONER:
                ob = new Conditioner();
                break;    
            case DISPLAY:
                ob = new Display();
                break;     
            case FAX:
                ob = new Fax();
                break;
            case INFOMAT:
                ob = new Infomat();
                break;
            case MONITOR:
                ob = new Monitor();
                break;
            case PHONE:
                ob = new Phone();
                break;
            case ROUTER:
                ob = new Router();
                break;
            case SCANNER:
                ob = new Scanner();
                break;
            case SERVER:
                ob = new Server();
                break;
            case SWITCHHUB:
                ob = new SwitchHub();
                break;
            case SWITCHHUBUNIT:
                ob = new SwitchingUnit();
                break;
            case SYSTEMBLOCK:
                ob = new SystemBlock();
                break;
            case TERMINAL:
                ob = new Terminal();
                break;
            case THERMOPRINTER:
                ob = new ThermoPrinter();
                break;
            case UPS:
                ob = new Ups();
                break;
      
        }
        return ob;
                
    }
}
