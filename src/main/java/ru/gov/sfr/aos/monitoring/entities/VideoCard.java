/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import javax.persistence.Entity;


/**
 *
 * @author 041AlikinOS
 */
@Entity
public class VideoCard extends SvtModel {


    public VideoCard() {
    }

    
    @Override
    public String toString() {
        return "VideoCardModel{" + "id=" + this.id + ", model=" + this.model + "}";
    }
    
}