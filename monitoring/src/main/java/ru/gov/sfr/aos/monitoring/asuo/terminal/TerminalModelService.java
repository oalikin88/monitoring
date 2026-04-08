/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo.terminal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalModelService extends SvtModelService<TerminalModel, TerminalModelRepo> {
    @Autowired
    private TerminalModelRepo repo;
    
    public List<TerminalModel> getModelsByManufacturerId(Long id) {
        return repo.findByManufacturerId(id);
    }
}
