/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author 041AlikinOS
 */
public class SvtSystemBlockDTO extends SvtDTO {
    
    public String numberRoom;
    public Date dateUpgrade;
    public Long motherboardId;
    public String motherboardModel;
    public Long cpuId;
    public String cpuModel;
    public int cpuCore;
    public int cpuFreq;
    public Long ramId;
    public String ramModel;
    public int ramCapacity;
    public List <Long> hddIdList;
    public Long videoCardId;
    public String videoCardModel;
    public Long cdDriveId;
    public String cdDriveModel;
    public Long soundCardId;
    public String soundCardModel;
    public Long lanCardId;
    public String lanCardModel;
    public Long keyboardId;
    public String keyboardModel;
    public Long mouseId;
    public String mouseModel;
    public Long speakersId;
    public String speakersModel;
    public String ipAdress;
    public String operationSystemName;
    public List<Long> operationSystemId;

    public SvtSystemBlockDTO(String numberRoom, Date dateUpgrade, Long motherboardId, String motherboardModel, Long cpuId, String cpuModel, int cpuCore, int cpuFreq, Long ramId, String ramModel, int ramCapacity, Long videoCardId, String videoCardModel, Long cdDriveId, String cdDriveModel, Long soundCardId, String soundCardModel, Long lanCardId, String lanCardModel, Long keyboardId, String keyboardModel, Long mouseId, String mouseModel, Long speakersId, String speakersModel, String ipAdress, String operationSystemName, List<Long> operationSystemId) {
        this.numberRoom = numberRoom;
        this.dateUpgrade = dateUpgrade;
        this.motherboardId = motherboardId;
        this.motherboardModel = motherboardModel;
        this.cpuId = cpuId;
        this.cpuModel = cpuModel;
        this.cpuCore = cpuCore;
        this.cpuFreq = cpuFreq;
        this.ramId = ramId;
        this.ramModel = ramModel;
        this.ramCapacity = ramCapacity;
        this.videoCardId = videoCardId;
        this.videoCardModel = videoCardModel;
        this.cdDriveId = cdDriveId;
        this.cdDriveModel = cdDriveModel;
        this.soundCardId = soundCardId;
        this.soundCardModel = soundCardModel;
        this.lanCardId = lanCardId;
        this.lanCardModel = lanCardModel;
        this.keyboardId = keyboardId;
        this.keyboardModel = keyboardModel;
        this.mouseId = mouseId;
        this.mouseModel = mouseModel;
        this.speakersId = speakersId;
        this.speakersModel = speakersModel;
        this.ipAdress = ipAdress;
        this.operationSystemName = operationSystemName;
        this.operationSystemId = operationSystemId;
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

    public Long getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(Long motherboardId) {
        this.motherboardId = motherboardId;
    }

    public String getMotherboardModel() {
        String result = "";
        if(null == motherboardModel) {
            result = "нет";
        } else {
            result = motherboardModel;
        }
        return result;
    }

    public void setMotherboardModel(String motherboardModel) {
        this.motherboardModel = motherboardModel;
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


    public Long getVideoCardId() {
        return videoCardId;
    }

    public void setVideoCardId(Long videoCardId) {
        this.videoCardId = videoCardId;
    }

    public String getVideoCardModel() {
        return videoCardModel;
    }

    public void setVideoCardModel(String videoCardModel) {
        this.videoCardModel = videoCardModel;
    }

    public Long getCdDriveId() {
        return cdDriveId;
    }

    public void setCdDriveId(Long cdDriveId) {
        this.cdDriveId = cdDriveId;
    }

    public String getCdDriveModel() {
        return cdDriveModel;
    }

    public void setCdDriveModel(String cdDriveModel) {
        this.cdDriveModel = cdDriveModel;
    }

    public Long getSoundCardId() {
        return soundCardId;
    }

    public void setSoundCardId(Long soundCardId) {
        this.soundCardId = soundCardId;
    }

    public String getSoundCardModel() {
        return soundCardModel;
    }

    public void setSoundCardModel(String soundCardModel) {
        this.soundCardModel = soundCardModel;
    }

    public Long getLanCardId() {
        return lanCardId;
    }

    public void setLanCardId(Long lanCardId) {
        this.lanCardId = lanCardId;
    }

    public String getLanCardModel() {
        return lanCardModel;
    }

    public void setLanCardModel(String lanCardModel) {
        this.lanCardModel = lanCardModel;
    }

    public Long getKeyboardId() {
        return keyboardId;
    }

    public void setKeyboardId(Long keyboardId) {
        this.keyboardId = keyboardId;
    }

    public String getKeyboardModel() {
        return keyboardModel;
    }

    public void setKeyboardModel(String keyboardModel) {
        this.keyboardModel = keyboardModel;
    }

    public Long getMouseId() {
        return mouseId;
    }

    public void setMouseId(Long mouseId) {
        this.mouseId = mouseId;
    }

    public String getMouseModel() {
        return mouseModel;
    }

    public void setMouseModel(String mouseModel) {
        this.mouseModel = mouseModel;
    }

    public Long getSpeakersId() {
        return speakersId;
    }

    public void setSpeakersId(Long speakersId) {
        this.speakersId = speakersId;
    }

    public String getSpeakersModel() {
        return speakersModel;
    }

    public void setSpeakersModel(String speakersModel) {
        this.speakersModel = speakersModel;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
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

    public int getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
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

    public List<Long> getHddIdList() {
        return hddIdList;
    }

    public void setHddIdList(List<Long> hddIdList) {
        this.hddIdList = hddIdList;
    }



    
    

    
    
  
    
    
}
