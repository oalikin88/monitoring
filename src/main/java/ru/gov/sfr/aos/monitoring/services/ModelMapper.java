/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gov.sfr.aos.monitoring.PrintColorType;
import ru.gov.sfr.aos.monitoring.PrintFormatType;
import ru.gov.sfr.aos.monitoring.dictionaries.DeviceType;
import ru.gov.sfr.aos.monitoring.entities.Manufacturer;
import ru.gov.sfr.aos.monitoring.entities.Model;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
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
    
    
    public List<ModelDTO> listModelsToListModelsDto(List<Model> list) {
        List<ModelDTO> out = new ArrayList<>();
        for(int i = 0; i < list.size(); i++) {
                ModelDTO dto = new ModelDTO();
                dto.model = list.get(i).getName();
                dto.idModel = list.get(i).getId();
                dto.printColorType = list.get(i).getPrintColorType().getType();
                dto.printFormatType = list.get(i).getPrintFormatType().name();
                dto.printSpeed = list.get(i).getPrintSpeed().toString();
                dto.manufacturer = list.get(i).getManufacturer().getName();
                dto.setDeviceType(list.get(i).getDeviceType().getValue());
                out.add(dto);
        }
        
        return out;
    }
    
    public List<ModelDTO> showModelsByManufacturer(String manufacturer) {
        List<ModelDTO> list = new ArrayList<>();
        List<Model> inputList = modelRepo.findByManufacturerNameContainingIgnoreCase(manufacturer.trim());
        for(int i = 0; i < inputList.size(); i++) {
            if(!inputList.get(i).getName().isEmpty() || !inputList.get(i).getName().isBlank() || inputList.get(i).getName() != null) {
                ModelDTO dto = new ModelDTO();
                dto.setManufacturer(inputList.get(i).getManufacturer().getName());
                dto.model = inputList.get(i).getName();
                dto.idModel = inputList.get(i).getId();
                dto.printColorType = inputList.get(i).getPrintColorType().getType();
                dto.printFormatType = inputList.get(i).getPrintFormatType().name();
                dto.printSpeed = inputList.get(i).getPrintSpeed().toString();
                list.add(dto);
            }
        }
        return list;
    }
    
    @Transactional
    public void saveModelByManufacturer(ModelDTO dto) throws ObjectAlreadyExists {
        boolean duplicate = false;
        List<ModelDTO> dtoes = showModelsByManufacturer(dto.getManufacturer().trim());
            String firstFromInput = dto.getModel().toLowerCase().trim();
            String replaceAllBrakesFromInput = firstFromInput.replaceAll(" ", "");
            String replaceAllFromInput = replaceAllBrakesFromInput.replaceAll("-", "");
        for(int i = 0; i < dtoes.size(); i++) {
            String firstFromList = dtoes.get(i).getModel().toLowerCase().trim();
            String replaceAllBrakesFromList = firstFromList.replaceAll(" ", "");
            String replaceAllFromList = replaceAllBrakesFromList.replaceAll("-", "");
            
            
            if(replaceAllFromList.equals(replaceAllFromInput)) {
               duplicate = true; 
               throw new ObjectAlreadyExists("Модель " + dto.getModel() + " производителя " + dto.getManufacturer() + " уже есть в базе данных!");
            }
        }
        
        if(!duplicate) {
            Manufacturer manufacturer = null;
            Optional<Manufacturer> findManufacturerByNameContainingIgnoreCase = manufacturerRepo.findByNameContainingIgnoreCase(dto.getManufacturer().toLowerCase().trim());
            if(findManufacturerByNameContainingIgnoreCase.isPresent()) {
                manufacturer = findManufacturerByNameContainingIgnoreCase.get();
            } else {
                manufacturer = new Manufacturer(dto.getManufacturer().trim());
            }
            Model model = new Model();
            model.setManufacturer(manufacturer);
            model.setName(dto.getModel());
            switch (dto.getPrintColorType()) {
                case "чёрно-белый":
                    model.setPrintColorType(PrintColorType.BLACKANDWHITE);
                    break;
                case "цветной":
                    model.setPrintColorType(PrintColorType.COLOR);
                    break;
            }
            
            switch (dto.getPrintFormatType()) {
                case "A4":
                    model.setPrintFormatType(PrintFormatType.A4);
                    break;
                case "A3":
                    model.setPrintFormatType(PrintFormatType.A3);
                    break;
            }
            
            switch (dto.getDeviceType()) {
                case "PRINTER":
                    model.setDeviceType(DeviceType.PRINTER);
                    break;
                case "MFU":
                    model.setDeviceType(DeviceType.MFU);
                    break;
               
            }
            Long speed = Long.parseLong(dto.getPrintSpeed().trim());
            model.setPrintSpeed(speed);
            modelRepo.save(model);
            System.out.println("Модель " + dto.getModel() + " успешно внесена в базу данных");
        } else {
            System.out.println("Такая модель уже есть в базе данных");
        }
        
        
    }
    
    
    public void saveModelPrinter(ModelDTO dto) {
        Model modelFromDB = modelRepo.findById(dto.idModel).orElseThrow();
        modelFromDB.setName(dto.getModel().trim());
        modelFromDB.setPrintSpeed(Long.parseLong(dto.getPrintSpeed()));
        if(dto.getPrintFormatType().equals("A4")) {
                modelFromDB.setPrintFormatType(PrintFormatType.A4);
        }else{
                modelFromDB.setPrintFormatType(PrintFormatType.A3);
        }
        if(dto.getPrintColorType().equals("COLOR")) {
            modelFromDB.setPrintColorType(PrintColorType.COLOR);
        } else {
            modelFromDB.setPrintColorType(PrintColorType.BLACKANDWHITE);
        }
        if(dto.getDeviceType().equals("PRINTER")) {
            modelFromDB.setDeviceType(DeviceType.PRINTER);
        } else {
            modelFromDB.setDeviceType(DeviceType.MFU);
        }
        if(!dto.getManufacturer().trim().equals(modelFromDB.getManufacturer().getName())) {
            Manufacturer manufacturer = manufacturerRepo.findByNameContainingIgnoreCase(dto.getManufacturer().trim()).orElseThrow();
            modelFromDB.setManufacturer(manufacturer);
        }
        modelRepo.save(modelFromDB);
    }
    
}
