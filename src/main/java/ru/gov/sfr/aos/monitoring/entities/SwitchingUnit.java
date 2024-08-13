/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "SWITCHING_UNIT_ID")
public class SwitchingUnit extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SwitchingUnitModel switchingUnitModel;

    public SwitchingUnit() {
    }

    public SwitchingUnitModel getSwitchingUnitModel() {
        return switchingUnitModel;
    }

    public void setSwitchingUnitModel(SwitchingUnitModel switchingUnitModel) {
        this.switchingUnitModel = switchingUnitModel;
    }
    
    
    
}
