package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author Alikin Oleg
 */

public class SvtHubDto extends SvtDTO {
    
    public int portAmount;

    public SvtHubDto() {
    }

    public int getPortAmount() {
        return portAmount;
    }

    public void setPortAmount(int portAmount) {
        this.portAmount = portAmount;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.portAmount;
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
        final SvtHubDto other = (SvtHubDto) obj;
        return this.portAmount == other.portAmount;
    }

    @Override
    public String toString() {
        return "SvtHubDto{" + "portAmount=" + portAmount + '}';
    }
    
    

}
