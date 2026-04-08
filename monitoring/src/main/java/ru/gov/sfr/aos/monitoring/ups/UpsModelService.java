/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.ups;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.services.RegularOperation;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class UpsModelService extends SvtModelService<UpsModel, UpsModelRepo> {
    @Autowired
    private UpsModelRepo upsModelRepo;
    @Autowired
    private UpsManufacturerRepo upsManufacturerRepo;
    
    
    public void saveModel(UpsModel e) throws ObjectAlreadyExists {
        
       
        String modelFromInputNameClear = RegularOperation.getForCompareValue(e.getModel());
       
            List<UpsModel> listModels = upsModelRepo.findByManufacturerId(e.getManufacturer().getId());
            
            for(UpsModel model : listModels) {
                String modelFromDBClear = RegularOperation.getForCompareValue(model.getModel());
                if(modelFromInputNameClear.toLowerCase().equals(modelFromDBClear.toLowerCase())) {
                    throw new ObjectAlreadyExists("Модель " + e.getModel() + " уже есть в базе данных");
                }
            }
        
        
        
        
        upsModelRepo.save(e);

    }
}
