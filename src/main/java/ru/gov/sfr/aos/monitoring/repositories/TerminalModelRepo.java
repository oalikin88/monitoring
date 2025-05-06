/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.entities.TerminalModel;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface TerminalModelRepo extends SvtModelsRepo<TerminalModel> {
    List<TerminalModel>findByManufacturerId(Long id);
}
