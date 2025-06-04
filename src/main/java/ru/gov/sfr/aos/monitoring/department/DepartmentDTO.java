/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.department;

import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author 041AlikinOS
 */
public class DepartmentDTO {
    private String name;
    private String code;
    private List<SvtDTO> phones = new ArrayList<>();

    public DepartmentDTO() {
    }

    public DepartmentDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<SvtDTO> getPhones() {
        return phones;
    }

    public void setPhones(List<SvtDTO> phones) {
        this.phones = phones;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.code);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DepartmentDTO other = (DepartmentDTO) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.code, other.code);
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" + "name=" + name + ", code=" + code + ", phones=" + phones + '}';
    }
    
    
}
