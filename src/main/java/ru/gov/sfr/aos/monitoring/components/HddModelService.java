/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class HddModelService extends SvtModelService<Hdd, HddRepo>{
    
    @Autowired
    private HddRepo hddRepo;
    
    
    public void update(Hdd e) throws ObjectAlreadyExists {
       Hdd hdd = hddRepo.findById(e.getId()).get();
       hdd.setCapacity(e.getCapacity());
       hdd.setUnit(e.getUnit());
       hdd.setInventaryNumber(e.getInventaryNumber());
       hdd.setSerialNumber(e.getSerialNumber());
       hdd.setModel(e.getModel());
       hddRepo.save(hdd);
    }
    
    public List<Hdd> getHddListBySysBlock(Long idSysBlock) {
        List<Hdd> result = hddRepo.findBySystemblocksId(idSysBlock);
        return result;
    }
    
}
