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
import ru.gov.sfr.aos.monitoring.models.ManufacturerDTO;
import ru.gov.sfr.aos.monitoring.repositories.ManufacturerRepo;
import ru.gov.sfr.aos.monitoring.repositories.ModelPrinterRepo;

/**
 *
 * @author user
 */
@Service
public class ManufacturersMapper {
    
    @Autowired
    private ManufacturerRepo manufacturerRepo;
    @Autowired
    private ModelPrinterRepo modelPrinterRepo;
    
    public List<ManufacturerDTO> showManufacturers() {
        List<ManufacturerDTO> dtoes = new ArrayList<>();
        List<Manufacturer> findAll = manufacturerRepo.findAll();
        List<Model> findModels = modelPrinterRepo.findAll();
        
        
        
        for(Manufacturer el : findAll) {
            ManufacturerDTO dto = new ManufacturerDTO();
            List<String> temp = new ArrayList<>();
            dto.setManufacturer(el.getName());
            for(Model m : findModels) {
            if(m.getManufacturer().getName().equals(dto.manufacturer)) {
                temp.add(m.getName());
            }
            dto.setModels(temp);
        }
            dtoes.add(dto);
        }
        
        return dtoes;
        
    }
    
}
