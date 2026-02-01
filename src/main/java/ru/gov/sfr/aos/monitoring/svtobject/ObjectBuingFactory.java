/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.svtobject;

import org.springframework.stereotype.Component;

import ru.gov.sfr.aos.monitoring.asuo.Asuo;
import ru.gov.sfr.aos.monitoring.asuo.terminal.Terminal;
import ru.gov.sfr.aos.monitoring.asuo.tv.Display;
import ru.gov.sfr.aos.monitoring.ats.Ats;
import ru.gov.sfr.aos.monitoring.conditioner.Conditioner;
import ru.gov.sfr.aos.monitoring.enums.ObjectBuingType;
import ru.gov.sfr.aos.monitoring.fax.Fax;
import ru.gov.sfr.aos.monitoring.infomat.Infomat;
import ru.gov.sfr.aos.monitoring.monitor.Monitor;
import ru.gov.sfr.aos.monitoring.phone.Phone;
import ru.gov.sfr.aos.monitoring.router.Router;
import ru.gov.sfr.aos.monitoring.scanner.Scanner;
import ru.gov.sfr.aos.monitoring.server.Server;
import ru.gov.sfr.aos.monitoring.switchhub.SwitchHub;
import ru.gov.sfr.aos.monitoring.systemblock.SystemBlock;
import ru.gov.sfr.aos.monitoring.ups.Ups;

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
            case SYSTEMBLOCK:
                ob = new SystemBlock();
                break;
            case TERMINAL:
                ob = new Terminal();
                break;
            case UPS:
                ob = new Ups();
                break;
      
        }
        return ob;
                
    }
}
