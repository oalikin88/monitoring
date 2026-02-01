package ru.gov.sfr.aos.monitoring.svtobject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gov.sfr.aos.monitoring.department.DepartmentTreeDto;
import ru.gov.sfr.aos.monitoring.location.Location;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;

/**
 *
 * @author Alikin Oleg
 */
@Service
public abstract class ObjectOutDtoTreeService <E extends ObjectBuing, T extends ObjectBuingMapper, D extends MainSvtDto> {
    
    @Autowired
    T mapper;
    
    
    public List<LocationByTreeDto> getTreeSvtDto(Map<Location, List<E>> input) {
        List<LocationByTreeDto> out = new ArrayList<>();
        for (Map.Entry<Location, List<E>> outEntry : input.entrySet()) {
            LocationByTreeDto locationByTreeDto = new LocationByTreeDto();
            locationByTreeDto.setLocationId(outEntry.getKey().getId());
            locationByTreeDto.setLocationName(outEntry.getKey().getName());
            Set<String> departmentsByLocation = outEntry.getKey().getPlacesSet().stream().map(e -> e.getDepartment()).collect(Collectors.toSet());
            List<DepartmentTreeDto> listDepartments = new ArrayList<>();
            for (String department : departmentsByLocation) {
                List<D> dtoes = new ArrayList<>();
                DepartmentTreeDto departmentsTree = new DepartmentTreeDto();
                departmentsTree.setDepartment(department);
                for (E el : outEntry.getValue()) {
                    if (departmentsTree.getDepartment().equals(el.getPlace().getDepartment())) {
                        D dto = (D) mapper.getDto(el);
                        dtoes.add(dto);
                    }
                }
                departmentsTree.setDtoes(dtoes);
                listDepartments.add(departmentsTree);
                locationByTreeDto.setDepartments(listDepartments);
            }
            out.add(locationByTreeDto);
        }
        return out;
    }
    
    public List<LocationByTreeDto> getTreeSvtDtoByPlaceType(Map<Location, List<E>> input) {
        List<LocationByTreeDto> out = new ArrayList<>();
        for (Map.Entry<Location, List<E>> outEntry : input.entrySet()) {
            LocationByTreeDto locationByTreeDto = new LocationByTreeDto();
            locationByTreeDto.setLocationId(outEntry.getKey().getId());
            locationByTreeDto.setLocationName(outEntry.getKey().getName());
            Set<String> tempDepartmentsByLocation = outEntry.getKey().getPlacesSet().stream().map(e -> e.getDepartment()).collect(Collectors.toSet());
            TreeSet<String> departmentsByLocation = new TreeSet<>(tempDepartmentsByLocation);
            List<DepartmentTreeDto> svtObjectsByDepartmentsList = new ArrayList<>();
            for (String department : departmentsByLocation) {
                List<D> dtoList = new ArrayList<>();
                DepartmentTreeDto departmentTreeDto = new DepartmentTreeDto();
                departmentTreeDto.setDepartment(department);
                for (E svtObject : outEntry.getValue()) {
                    if (departmentTreeDto.getDepartment().equals(svtObject.getPlace().getDepartment())) {
                        D dto = (D) mapper.getDto(svtObject);
                        dtoList.add(dto);
                    }
                }
                departmentTreeDto.setDtoes(dtoList);
                if(dtoList.size() > 0) {
                    svtObjectsByDepartmentsList.add(departmentTreeDto);
                }
                
                locationByTreeDto.setDepartments(svtObjectsByDepartmentsList);
            }
            out.add(locationByTreeDto);
        }
        return out;
    }
}
