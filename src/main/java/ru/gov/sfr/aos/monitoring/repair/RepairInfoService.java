/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repair;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.repair.RepairRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class RepairInfoService {
    
    @Autowired
    private RepairRepo repairRepo;
    @Autowired
    private RepairMapper repairMapper;
    
    public List<RepairDto> getRepairs(Long id) {
        List<Repair> repairs = repairRepo.findByObjectBuingId(id);
        List<RepairDto> dtoes = new ArrayList<>();
        for(Repair repair : repairs) {
            RepairDto dto = repairMapper.toRepairDto(repair);
            dtoes.add(dto);
        }
        return dtoes;
    }
    

     public void deleteRepair(Long id) {
        Repair repair = repairRepo.findById(id).get();
        repairRepo.delete(repair);
     }
}
