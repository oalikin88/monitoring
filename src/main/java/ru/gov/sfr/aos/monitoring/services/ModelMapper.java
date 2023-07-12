/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.repositories.ManufacturerRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class ModelMapper {
    
    @Autowired
    private ModelPrinterRepo modelRepo;
    @Autowired
    private ManufacturerRepo manufacturerRepo;
    
    public List<ModelDTO> showModels() {
        List<ModelDTO> list = new ArrayList<>();
        List<Model> inputList = modelRepo.findAll();
        for(int i = 0; i < inputList.size(); i++) {
            if(!inputList.get(i).getName().isEmpty() || !inputList.get(i).getName().isBlank() || inputList.get(i).getName() != null) {
                ModelDTO dto = new ModelDTO();
                dto.model = inputList.get(i).getName();
                if(!inputList.get(i).getManufacturer().getName().isEmpty() || !inputList.get(i).getManufacturer().getName().isBlank() || inputList.get(i).getManufacturer().getName() != null) {
                    dto.manufacturer = inputList.get(i).getManufacturer().getName();
                }
                list.add(dto);
            }
        }
        return list;
    }
    
    public List<ModelDTO> showModelsByManufacturer(String manufacturer) {
        List<ModelDTO> list = new ArrayList<>();
        List<Model> inputList = modelRepo.findByManufacturerNameContainingIgnoreCase(manufacturer);
        for(int i = 0; i < inputList.size(); i++) {
            if(!inputList.get(i).getName().isEmpty() || !inputList.get(i).getName().isBlank() || inputList.get(i).getName() != null) {
                ModelDTO dto = new ModelDTO();
                dto.setManufacturer(inputList.get(i).getManufacturer().getName());
                dto.setModel(inputList.get(i).getName());
                list.add(dto);
            }
        }
        return list;
    }
    
    public void saveModelByManufacturer(String manufacturer, String target) {
        boolean duplicate = false;
        List<ModelDTO> dtoes = showModelsByManufacturer(manufacturer);
        for(int i = 0; i < dtoes.size(); i++) {
            if(dtoes.get(i).getModel().toLowerCase().trim().equals(target.toLowerCase().trim())) {
               duplicate = true; 
            }
        }
        
        if(!duplicate) {
            
            Manufacturer entity = manufacturerRepo.findByNameContainingIgnoreCase(manufacturer.toLowerCase()).get(0);
            Model model = new Model();
            model.setManufacturer(entity);
            model.setName(target);
            modelRepo.save(model);
            System.out.println("Модель " + target + " успешно внесена в базу данных");
        } else {
            System.out.println("Такая модель уже есть в базе данных");
        }
        
        
    }
    
    
}
