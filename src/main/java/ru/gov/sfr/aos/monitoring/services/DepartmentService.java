/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.opfr.springBootStarterDictionary.clientImpl.OrganizationClient;
import org.opfr.springBootStarterDictionary.fallback.FallbackOrganizationClient;
import org.opfr.springBootStarterDictionary.models.DictionaryOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.Place;
import ru.gov.sfr.aos.monitoring.models.DepartmentDTO;
import ru.gov.sfr.aos.monitoring.repositories.PlaceRepo;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class DepartmentService {
    @Autowired
    private FallbackOrganizationClient organizationClient;
    
    @Autowired
    private PlaceRepo placeRepo;
    
    public List<DepartmentDTO> getDepartments() {
        List<DictionaryOrganization> departments = organizationClient.getList();
        List<DepartmentDTO> out = new ArrayList<>();
        for(DictionaryOrganization el : departments) {
            DepartmentDTO dto = new DepartmentDTO(el.getFullName(), el.getCode());
            out.add(dto);
        }
        return out;
    }
    
    public Set<DepartmentDTO> getDepartmentsByPlaces() {
        List<Place> places = placeRepo.findByPlaceTypeAndArchivedFalse(PlaceType.EMPLOYEE);
        Map<String, List<Place>> collect = places.stream().collect(Collectors.groupingBy(Place::getDepartmentCode));
        List<DictionaryOrganization> departments = organizationClient.getList();
        Set<DepartmentDTO> departmentsDtoes = new HashSet<>();
        for(Map.Entry<String, List<Place>> entry : collect.entrySet()) {
            for(DictionaryOrganization dep : departments) {
                if(entry.getKey().equals(dep.getCode())) {
                    DepartmentDTO dto = new DepartmentDTO(dep.getFullName(), entry.getKey());
                    departmentsDtoes.add(dto);
                }
            }
        }
    return departmentsDtoes;
    }
}
