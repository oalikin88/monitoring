package ru.gov.sfr.aos.monitoring.models;

import java.util.Objects;

/**
 *
 * @author Alikin Oleg
 */

public class AsuoComponentDto {
    public Long id;
    public boolean hasInstalled;

    public AsuoComponentDto() {
    }

    public AsuoComponentDto(Long id, boolean hasInstalled) {
        this.id = id;
        this.hasInstalled = hasInstalled;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isHasInstalled() {
        return hasInstalled;
    }

    public void setHasInstalled(boolean hasInstalled) {
        this.hasInstalled = hasInstalled;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + (this.hasInstalled ? 1 : 0);
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
        final AsuoComponentDto other = (AsuoComponentDto) obj;
        if (this.hasInstalled != other.hasInstalled) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }
    
    
    
}
