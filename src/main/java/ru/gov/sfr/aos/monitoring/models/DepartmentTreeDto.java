/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 041AlikinOS
 */

public class DepartmentTreeDto <D extends SvtDTO> {
    private String department;
    private String departmentCode;
    private List<D> dtoes = new ArrayList<>();

    public DepartmentTreeDto() {
    }

    public DepartmentTreeDto(String department, String departmentCode) {
        this.department = department;
        this.departmentCode = departmentCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public List<D> getDtoes() {
        return dtoes;
    }

    public void setDtoes(List<D> dtoes) {
        this.dtoes = dtoes;
    }

    @Override
    public String toString() {
        return "DepartmentTreeDto{" + "department=" + department + ", departmentCode=" + departmentCode + ", dtoes=" + dtoes + '}';
    }
    
    
}
