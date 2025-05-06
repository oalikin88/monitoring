package ru.gov.sfr.aos.monitoring.models;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author Alikin Oleg
 */

public class ProgramSoftwareDto {

    public Long id;
    public String name;
    public String version;
    public boolean archived;
    public Set<Long> asuos = new HashSet<>();

    public ProgramSoftwareDto() {
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public Set<Long> getAsuos() {
        return asuos;
    }

    public void setAsuos(Set<Long> asuos) {
        this.asuos = asuos;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.version);
        hash = 61 * hash + (this.archived ? 1 : 0);
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
        final ProgramSoftwareDto other = (ProgramSoftwareDto) obj;
        if (this.archived != other.archived) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ProgramSoftwareDto{" + "id=" + id + ", name=" + name + ", version=" + version + ", archived=" + archived + '}';
    }
    
    
}
