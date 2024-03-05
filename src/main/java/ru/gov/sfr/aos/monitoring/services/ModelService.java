/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;

/**
 *
 * @author 041AlikinOS
 */
@Component
public class ModelService {
    
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;

    
    @Transactional
    public void deleteModel(Long id) {
        Model modelFromDB = modelPrinterRepo.findById(id).orElseThrow();
        if(modelFromDB.getPrintersList().size() < 1) {
            modelPrinterRepo.deleteModelPrinterById(id);
        } else {
            modelFromDB.setArchived(true);
        }
    }
    
    public List<Model> showAllModels() {
        List<Model> inputList = modelPrinterRepo.findAllNotArchived();
        return inputList;
    }
    
    public List<Model> showArchivedModels() {
        List<Model> inputList = modelPrinterRepo.findAllArchived();
        return inputList;
    }
    
    public void recoveryModel(Long id) {
        Model model = modelPrinterRepo.findById(id).orElseThrow();
        model.setArchived(false);
        modelPrinterRepo.save(model);
    }
    
}
