/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.phone;

import java.util.List;

import org.springframework.stereotype.Repository;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelsRepo;

/**
 *
 * @author 041AlikinOS
 */

@Repository
public interface PhoneModelRepo extends SvtModelsRepo<PhoneModel> {
    List<PhoneModel> findByManufacturerId(Long id);
}
