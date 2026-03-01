/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.asuo.tv;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;
import ru.gov.sfr.aos.monitoring.asuo.Asuo;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventary;

/**
 *
 * @author 041AlikinOS
 */
@Entity
@PrimaryKeyJoinColumn(name = "DISPLAY_ID")
public class Display extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private DisplayModel displayModel;
    @ManyToMany(mappedBy = "display")
    private Set<Asuo> asuo = new HashSet<>();
    
    public Display() {
        
    }

    public DisplayModel getDisplayModel() {
        return displayModel;
    }

    public void setDisplayModel(DisplayModel displayModel) {
        this.displayModel = displayModel;
    }

    public Set<Asuo> getAsuo() {
        return asuo;
    }

    public void setAsuo(Set<Asuo> asuo) {
        this.asuo = asuo;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.displayModel);
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
        final Display other = (Display) obj;
        return Objects.equals(this.displayModel, other.displayModel);
    }

    
    
    
    @Override
    public String toString() {
        return "Display{" + "id=" + this.id + ", serial=" + this.serialNumber + ", inventary=" + this.inventaryNumber + ", displayModel=" + displayModel + '}';
    }
    
  
}
