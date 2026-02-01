/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.List;

/*import org.opfr.springBootStarterDictionary.fallback.FallbackEmployeeClient;
import org.opfr.springBootStarterDictionary.models.DictionaryEmployee;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 *
 * @author 041AlikinOS
 */

@Cacheable("dictonaryEmployee")
@Component
public class DictionaryEmployeeHolder {
/*    @Autowired
    private FallbackEmployeeClient employeeClient;
    
    @Cacheable("dictonaryEmployee_employees")
    public List<DictionaryEmployee> getEmployees() {
         return employeeClient.getList();*/
//    }
}
