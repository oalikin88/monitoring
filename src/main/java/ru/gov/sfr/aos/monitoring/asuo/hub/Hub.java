package ru.gov.sfr.aos.monitoring.asuo.hub;

import java.io.Serializable;
import java.util.Date;
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
 * @author Alikin Oleg
 */
@Entity
@PrimaryKeyJoinColumn(name = "HUB_ID")
public class Hub extends ObjectBuingWithSerialAndInventary implements Serializable {
    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HubModel hubModel;
    private int portAmount;
    private Date dateExploitationBegin;
    private int yearCreated;
    private String nameFromOneC;
    @ManyToMany(mappedBy = "hubSet")
    private Set<Asuo> asuo = new HashSet<>();

    public Hub() {
    }

    public HubModel getHubModel() {
        return hubModel;
    }

    public void setHubModel(HubModel hubModel) {
        this.hubModel = hubModel;
    }

    public int getPortAmount() {
        return portAmount;
    }

    public void setPortAmount(int portAmount) {
        this.portAmount = portAmount;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public String getNameFromOneC() {
        return nameFromOneC;
    }

    public void setNameFromOneC(String nameFromOneC) {
        this.nameFromOneC = nameFromOneC;
    }

    public Set<Asuo> getAsuo() {
        return asuo;
    }

    public void setAsuo(Set<Asuo> asuo) {
        this.asuo = asuo;
    }

    public Date getDateExploitationBegin() {
        return dateExploitationBegin;
    }

    public void setDateExploitationBegin(Date dateExploitationBegin) {
        this.dateExploitationBegin = dateExploitationBegin;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.hubModel);
        hash = 71 * hash + this.portAmount;
        hash = 71 * hash + this.yearCreated;
        hash = 71 * hash + Objects.hashCode(this.nameFromOneC);
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
        final Hub other = (Hub) obj;
        if (this.portAmount != other.portAmount) {
            return false;
        }
        if (this.yearCreated != other.yearCreated) {
            return false;
        }
        if (!Objects.equals(this.nameFromOneC, other.nameFromOneC)) {
            return false;
        }
        return Objects.equals(this.hubModel, other.hubModel);
    }

    @Override
    public String toString() {
        return "Hub{" + "switchHubModel=" + hubModel + ", portAmount=" + portAmount + ", yearCreated=" + yearCreated + ", nameFromOneC=" + nameFromOneC + '}';
    }
    
    
}
