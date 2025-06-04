/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo.tv;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;


/**
 *
 * @author Alikin Oleg
 */
@Service
public class DisplayModelService extends SvtModelService<DisplayModel, DisplayModelRepo> {
   
    @Autowired
   private DisplayModelRepo repo;
    
    public List<DisplayModel> getModelsByManufacturerId(Long id) {
        return repo.findByManufacturerId(id);
    }
}
