package ru.gov.sfr.aos.monitoring.asuo;

import ru.gov.sfr.aos.monitoring.asuo.Asuo;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class ProgramSoftware implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double version;
    private boolean archived;
    @OneToMany(targetEntity = Asuo.class, mappedBy = "programSoftware", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Asuo> asuos = new HashSet<>();

    public ProgramSoftware() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getVersion() {
        return version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Set<Asuo> getAsuos() {
        return asuos;
    }

    public void setAsuos(Set<Asuo> asuos) {
        this.asuos = asuos;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.version) ^ (Double.doubleToLongBits(this.version) >>> 32));
        hash = 41 * hash + (this.archived ? 1 : 0);
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
        final ProgramSoftware other = (ProgramSoftware) obj;
        if (Double.doubleToLongBits(this.version) != Double.doubleToLongBits(other.version)) {
            return false;
        }
        if (this.archived != other.archived) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ProgramSoftware{" + "id=" + id + ", name=" + name + ", version=" + version + ", archived=" + archived + '}';
    }
    
    
}
