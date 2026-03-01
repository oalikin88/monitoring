package ru.gov.sfr.aos.monitoring.svtobject;

import ru.gov.sfr.aos.monitoring.asuo.AsuoService;
import ru.gov.sfr.aos.monitoring.infomat.InfomatService;
import ru.gov.sfr.aos.monitoring.router.RouterService;
import ru.gov.sfr.aos.monitoring.conditioner.ConditionerService;
import ru.gov.sfr.aos.monitoring.ats.AtsService;
import ru.gov.sfr.aos.monitoring.switchhub.SwitchHubService;
import ru.gov.sfr.aos.monitoring.server.ServerService;
import ru.gov.sfr.aos.monitoring.systemblock.SystemBlockService;
import ru.gov.sfr.aos.monitoring.ups.UpsService;
import ru.gov.sfr.aos.monitoring.scanner.ScannerService;
import ru.gov.sfr.aos.monitoring.fax.FaxService;
import ru.gov.sfr.aos.monitoring.monitor.MonitorService;
import ru.gov.sfr.aos.monitoring.phone.PhoneService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.asuo.Asuo;
import ru.gov.sfr.aos.monitoring.ats.Ats;
import ru.gov.sfr.aos.monitoring.conditioner.Conditioner;
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
import ru.gov.sfr.aos.monitoring.enums.ObjectBuingType;
import ru.gov.sfr.aos.monitoring.models.DeviceDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class ObjectBuingServiceImpl {

    @Autowired
    private SystemBlockService systemBlockService;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private PhoneService phoneService;
    @Autowired
    private ScannerService scannerService;
    @Autowired
    private UpsService upsService;
    @Autowired
    private ServerService serverService;
    @Autowired
    private SwitchHubService switchHubService;
    @Autowired
    private RouterService routerService;
    @Autowired
    private AtsService atsService;
    @Autowired
    private ConditionerService conditionerService;
    @Autowired
    private FaxService faxService;
    @Autowired
    private InfomatService infomatService;
    @Autowired
    private AsuoService asuoService;
    
    
    public List<DeviceDto> getAllDevicesByPlaceId(Long id) {
        List<DeviceDto> out = new ArrayList<>();
        List<SystemBlock> systemblocks = systemBlockService.findAllByPlaceId(id);
        List<Monitor> monitors = monitorService.findAllByPlaceId(id);
        List<Phone> phones = phoneService.findAllByPlaceId(id);
        List<Scanner> scanners = scannerService.findAllByPlaceId(id);
        List<Ups> upses = upsService.findAllByPlaceId(id);
        List<Server> servers = serverService.findAllByPlaceId(id);
        List<SwitchHub> switchubs = switchHubService.findAllByPlaceId(id);
        List<Router> routers = routerService.findAllByPlaceId(id);
        List<Ats> atses = atsService.findAllByPlaceId(id);
        List<Conditioner> conditioners = conditionerService.findAllByPlaceId(id);
        List<Fax> faxes = faxService.findAllByPlaceId(id);
        List<Infomat> infomates = infomatService.findAllByPlaceId(id);
        List<Asuo> asuos = asuoService.findAllByPlaceId(id);
        
        
        if(!systemblocks.isEmpty()) {
            for(SystemBlock el : systemblocks) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.SYSTEMBLOCK, el.getSystemBlockModel().getManufacturer().getName() + " " + el.getSystemBlockModel().getModel(), el.getInventaryNumber()));
            }
           }
        if(!monitors.isEmpty()) {
            for(Monitor el : monitors) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.MONITOR, el.getMonitorModel().getManufacturer().getName() + " " + el.getMonitorModel().getModel(), el.getInventaryNumber()));
            }
        }
        if(!phones.isEmpty()) {
            for(Phone el : phones) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.PHONE, el.getPhoneModel().getManufacturer().getName() + " " + el.getPhoneModel().getModel(), el.getInventaryNumber()));
            }
        }
        if(!scanners.isEmpty()) {
            for(Scanner el : scanners) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.SCANNER, el.getScannerModel().getManufacturer().getName() + " " + el.getScannerModel().getModel(), el.getInventaryNumber()));
            }
        }
     
        if(!upses.isEmpty()) {
            for(Ups el : upses) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.UPS, el.getUpsModel().getManufacturer().getName() + " " + el.getUpsModel().getModel(), el.getInventaryNumber()));
            }
        }
        
        if(!servers.isEmpty()) {
            for(Server el : servers) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.SERVER, el.getServerModel().getManufacturer().getName() + " " + el.getServerModel().getModel(), el.getInventaryNumber()));
            }
        }
        
        if(!switchubs.isEmpty()) {
            for(SwitchHub el : switchubs) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.SWITCHHUB, el.getSwitchHubModel().getManufacturer().getName() + " " + el.getSwitchHubModel().getModel(), el.getInventaryNumber()));
            }
        }
        
        if(!routers.isEmpty()) {
            for(Router el : routers) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.ROUTER, el.getRouterModel().getManufacturer().getName() + " " + el.getRouterModel().getModel(), el.getInventaryNumber()));
            }
        }
        
        if(!atses.isEmpty()) {
            for(Ats el : atses) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.ATS, el.getAtsModel().getManufacturer().getName() + " " + el.getAtsModel().getModel(), el.getInventaryNumber()));
            }
        }
        
        if(!conditioners.isEmpty()) {
            for(Conditioner el : conditioners) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.CONDITIONER, el.getConditionerModel().getManufacturer().getName() + " " + el.getConditionerModel().getModel(), el.getInventaryNumber()));
            }
        }
        
        if(!faxes.isEmpty()) {
            for(Fax el : faxes) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.FAX, el.getModel().getManufacturer().getName() + " " + el.getModel().getModel(), el.getInventaryNumber()));
            }
        }
        
        if(!infomates.isEmpty()) {
            for(Infomat el : infomates) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.INFOMAT, el.getInfomatModel().getManufacturer().getName() + " " + el.getInfomatModel().getModel(), el.getInventaryNumber()));
            }
        }
        
        if(!asuos.isEmpty()) {
            for(Asuo el : asuos) {
                out.add(new DeviceDto(el.getId(), ObjectBuingType.ASUO, "Электронная очередь", el.getInventaryNumber()));
            }
        }
        
        return out;
    }
}
