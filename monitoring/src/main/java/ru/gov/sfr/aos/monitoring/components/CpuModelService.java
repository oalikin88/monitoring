/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class CpuModelService extends SvtModelService<Cpu, CpuRepo> {

    @Autowired
    private CpuRepo cpuRepo;

    public void update(Cpu e) throws ObjectAlreadyExists {
        Cpu cpu = cpuRepo.findById(e.getId()).get();
        cpu.setCore(e.getCore());
        cpu.setModel(e.getModel());
        cpu.setFreq(e.getFreq());
        cpuRepo.save(cpu);
    }
    
    
}
