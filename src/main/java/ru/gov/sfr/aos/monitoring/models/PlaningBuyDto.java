/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.models;

import java.time.LocalDate;

/**
 *
 * @author 041AlikinOS
 */
public class PlaningBuyDto {
    
    public LocalDate dateBegin;
    public LocalDate dateEnd;

    public PlaningBuyDto() {
    }

    public PlaningBuyDto(LocalDate dateBegin, LocalDate dateEnd, Long location) {
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
    }

    public LocalDate getDateBegin() {
        return dateBegin;
    }

    public void setDateBegin(LocalDate dateBegin) {
        this.dateBegin = dateBegin;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }


    
    
}
