/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.components;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;
import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.components.Motherboard;

/**
 *
 * @author 041AlikinOS
 */
@Repository
public interface MotherboardRepo extends SvtModelsRepo<Motherboard>{
    boolean existsByModelIgnoreCase(String model);
    List<Motherboard> findByModelIgnoreCase(String model);
    
}
