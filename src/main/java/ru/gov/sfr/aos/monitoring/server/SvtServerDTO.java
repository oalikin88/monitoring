/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.server;

import java.util.Date;
import java.util.List;

import ru.gov.sfr.aos.monitoring.svtobject.SvtDTO;

/**
 *
 * @author 041AlikinOS
 */
public class SvtServerDTO extends SvtDTO {
    public String numberRoom;
    public Date dateUpgrade;
    public Long cpuId;
    public String cpuModel;
    public int cpuCore;
    public int cpuFreq;
    public int cpuAmount;
    public Long ramId;
    public String ramModel;
    public int ramCapacity;
    public List <Long> hddIdList;
    public String operationSystemName;
    public List<Long> operationSystemId;
    public String ipAdress;

    public SvtServerDTO(String numberRoom, Date dateUpgrade, Long cpuId, String cpuModel, int cpuCore, int cpuFreq,
            int cpuAmount, Long ramId, String ramModel, int ramCapacity, List<Long> hddIdList,
            String operationSystemName, List<Long> operationSystemId, String ipAdress) {
        this.numberRoom = numberRoom;
        this.dateUpgrade = dateUpgrade;
        this.cpuId = cpuId;
        this.cpuModel = cpuModel;
        this.cpuCore = cpuCore;
        this.cpuFreq = cpuFreq;
        this.cpuAmount = cpuAmount;
        this.ramId = ramId;
        this.ramModel = ramModel;
        this.ramCapacity = ramCapacity;
        this.hddIdList = hddIdList;
        this.operationSystemName = operationSystemName;
        this.operationSystemId = operationSystemId;
        this.ipAdress = ipAdress;
    }

    public String getNumberRoom() {
         String result = "";
        if(null == numberRoom) {
            result = "не указан";
        } else {
            result = numberRoom;
        }
        return result;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public Date getDateUpgrade() {
        return dateUpgrade;
    }

    public void setDateUpgrade(Date dateUpgrade) {
        this.dateUpgrade = dateUpgrade;
    }

    public Long getCpuId() {
        return cpuId;
    }

    public void setCpuId(Long cpuId) {
        this.cpuId = cpuId;
    }

    public String getCpuModel() {
        String result = "";
        if(null == cpuModel) {
            result = "нет";
        } else {
            result = cpuModel;
        }
        return result;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public int getCpuCore() {
        return cpuCore;
    }

    public void setCpuCore(int cpuCore) {
        this.cpuCore = cpuCore;
    }

    public int getCpuFreq() {
        return cpuFreq;
    }

    public void setCpuFreq(int cpuFreq) {
        this.cpuFreq = cpuFreq;
    }

    public int getCpuAmount() {
        return cpuAmount;
    }

    public void setCpuAmount(int cpuAmount) {
        this.cpuAmount = cpuAmount;
    }

    public Long getRamId() {
        return ramId;
    }

    public void setRamId(Long ramId) {
        this.ramId = ramId;
    }

    public String getRamModel() {
        String result = "";
        if(null == ramModel) {
            result = "нет";
        } else {
            result = ramModel;
        }
        return result;
    }

    public void setRamModel(String ramModel) {
        this.ramModel = ramModel;
    }

    public int getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public List<Long> getHddIdList() {
        return hddIdList;
    }

    public void setHddIdList(List<Long> hddIdList) {
        this.hddIdList = hddIdList;
    }

    public String getOperationSystemName() {
        return operationSystemName;
    }

    public void setOperationSystemName(String operationSystemName) {
        this.operationSystemName = operationSystemName;
    }

    public List<Long> getOperationSystemId() {
        return operationSystemId;
    }

    public void setOperationSystemId(List<Long> operationSystemId) {
        this.operationSystemId = operationSystemId;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    @Override
    public String toString() {
        return "SvtServerDTO{" + "id=" + this.id + "model=" + this.model +  ", numberRoom=" + numberRoom + ", dateUpgrade=" + dateUpgrade + ", cpuId=" + cpuId + ", cpuModel=" + cpuModel + ", cpuCore=" + cpuCore + ", cpuFreq=" + cpuFreq + ", cpuAmount=" + cpuAmount + ", ramId=" + ramId + ", ramModel=" + ramModel + ", ramCapacity=" + ramCapacity + ", hddIdList=" + hddIdList + ", operationSystemName=" + operationSystemName + ", operationSystemId=" + operationSystemId + ", ipAdress=" + ipAdress + '}';
    }
    
    
}
