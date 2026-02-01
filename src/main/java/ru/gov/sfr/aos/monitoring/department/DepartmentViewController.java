package ru.gov.sfr.aos.monitoring.department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class DepartmentViewController {

    @Autowired
    private DepartmentService departmentService;

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/dep")
    public String getDepartments(Model model) {

//        List<DepartmentDTO> departments = departmentService.getDepartments();
//        model.addAttribute("dtoes", departments);

        return "departments";
    }
}
