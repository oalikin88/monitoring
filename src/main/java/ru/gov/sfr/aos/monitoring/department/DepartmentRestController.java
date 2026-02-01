package ru.gov.sfr.aos.monitoring.department;

import java.util.ArrayList;
import java.util.List;

//import org.opfr.springBootStarterDictionary.fallback.FallbackOrganizationClient;
//import org.opfr.springBootStarterDictionary.models.DictionaryOrganization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class DepartmentRestController {

//    @Autowired
//    private FallbackOrganizationClient organizationClient;
//
//    @GetMapping("/departments")
//    public List<DepartmentDTO> getDepartments() {
//        List<DictionaryOrganization> list = organizationClient.getList();
//        List<DepartmentDTO> out = new ArrayList<>();
//        for (DictionaryOrganization el : list) {
//            DepartmentDTO dto = new DepartmentDTO(el.getFullName(), el.getCode());
//            out.add(dto);
//        }
//        return out;
//    }
}
