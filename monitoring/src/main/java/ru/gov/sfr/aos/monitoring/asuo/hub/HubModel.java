package ru.gov.sfr.aos.monitoring.asuo.hub;

import ru.gov.sfr.aos.monitoring.asuo.hub.HubManufacturer;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModel;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class HubModel extends SvtModel  {
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HubManufacturer manufacturer;
    @OneToMany(targetEntity = Hub.class, mappedBy = "hubModel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Hub> hubs = new HashSet<>();

    public HubModel() {
    }

    public HubManufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(HubManufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Set<Hub> getHubs() {
        return hubs;
    }

    public void setHubs(Set<Hub> hubs) {
        this.hubs = hubs;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.manufacturer);
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
        final HubModel other = (HubModel) obj;
        return Objects.equals(this.manufacturer, other.manufacturer);
    }

    @Override
    public String toString() {
        return "HubModel{" + "manufacturer=" + manufacturer + '}';
    }

        
    
}
