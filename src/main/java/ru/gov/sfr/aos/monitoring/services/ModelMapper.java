/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.models.ModelDTO;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;

/**
 *
 * @author 041AlikinOS
 */

@Service
public class ModelMapper {
    
    @Autowired
    private ModelPrinterRepo modelRepo;
    
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
    
    public List<String> showModelsByManufacturer(String manufacturer) {
        List<String> list = new ArrayList<>();
        List<Model> inputList = modelRepo.findByManufacturerName(manufacturer);
        for(int i = 0; i < inputList.size(); i++) {
            if(!inputList.get(i).getName().isEmpty() || !inputList.get(i).getName().isBlank() || inputList.get(i).getName() != null) {
                list.add(inputList.get(i).getName());
            }
        }
        return list;
    }
    
}
