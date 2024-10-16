/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author 041AlikinOS
 */
public class SvtScannerDTO extends SvtDTO {
    
    public String ipAdress;
    public String numberRoom;

    public SvtScannerDTO(String ipAdress, String numberRoom) {
        this.ipAdress = ipAdress;
        this.numberRoom = numberRoom;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    @Override
    public String toString() {
        return "SvtScannerDTO{" + "id=" + this.getId() + ", model=" + this.model + ", ipAdress=" + ipAdress + ", numberRoom=" + numberRoom + '}';
    }
    
    
}
