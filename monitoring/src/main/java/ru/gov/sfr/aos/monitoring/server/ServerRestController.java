package ru.gov.sfr.aos.monitoring.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class ServerRestController {

    @Autowired
    private ServerModelService serverModelService;
    @Autowired
    private ServerMapper serverMapper;
    @Autowired
    private ServerService serverService;
    @Autowired
    private ServerManufacturerService serverManufacturerService;
    @Autowired
    private ServerManufacturerMapper serverManufacturerMapper;
    @Autowired
    private ServerModelMapper serverModelMapper;
    
     @PostMapping("/save-server-manufacturer")
    public ResponseEntity<?> saveServerManufacturer(String name) throws ObjectAlreadyExists {
        ServerManufacturer savedManufacturer = null;
        ServerManufacturer potencialManufacturer = new ServerManufacturer();
        potencialManufacturer.setName(name);
        try{
            savedManufacturer = serverManufacturerService.save(potencialManufacturer);
        } catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        ManufacturerDTO dto = serverManufacturerMapper.getDto(savedManufacturer);
        return ResponseEntity.ok(dto);
    }
    
     @GetMapping("/get-server-manufacturers")
    public List<ManufacturerDTO> getServerManufacturers() {
        List<ServerManufacturer> allManufacturers = serverManufacturerService.getAllManufacturers();
        List<ManufacturerDTO> out = new ArrayList<>();
        for(ServerManufacturer el : allManufacturers) {
            ManufacturerDTO dto = serverManufacturerMapper.getDto(el);
            out.add(dto);
        }
        return out;
    }
    
        @GetMapping("/get-server-modelsby-manufacturer")
    public List<SvtModelDto> getServerModelsByManufacturer(@RequestParam(value="id", required = true) Long id) {
        ServerManufacturer manufacturer = serverManufacturerService.getManufacturer(id);
        Set<ServerModel> upsListModel = manufacturer.getModels();
        List<SvtModelDto> out = new ArrayList<>();
        for(ServerModel model : upsListModel) {
            SvtModelDto modelDto = serverModelMapper.getDto(model);
            out.add(modelDto);
        }
        return out;
    }
    
     @GetMapping("/getserver")
    public SvtServerDTO getServer(Long serverId) {
        Server server = serverService.getById(serverId);
        SvtServerDTO serverDto = serverMapper.getDto(server);
        return serverDto;
    }
    
       @GetMapping("/modserver")
    public List<SvtModelDto> getModelServer() {
        List<ServerModel> allModels = serverModelService.getAllModels();
        List<SvtModelDto> serverDtoes = new ArrayList<>();
        for(ServerModel model : allModels) {
            SvtModelDto dto = serverModelMapper.getDtoForSelectize(model);
            serverDtoes.add(dto);
        }
        return serverDtoes;
    } 
    
}
