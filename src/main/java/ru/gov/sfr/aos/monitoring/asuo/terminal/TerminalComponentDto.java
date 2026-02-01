package ru.gov.sfr.aos.monitoring.asuo.terminal;

import java.util.Objects;

import ru.gov.sfr.aos.monitoring.svtobject.MainSvtDto;

/**
 *
 * @author Alikin Oleg
 */

public class TerminalComponentDto extends MainSvtDto {
    
    public Long id;
    public String serialNumber;
    public String status;
    public Long modelId;
    public String model;
    public Long manufacturerId;
    public String manufacturer;
    public Long placeId;
    public String placeName;
    public String placeType;
    public String departmentCode;
    public String locationId;
    public boolean hasInstalled;
    public Long terminalId;

    public TerminalComponentDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    
    
    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public Long getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isHasInstalled() {
        return hasInstalled;
    }

    public void setHasInstalled(boolean hasInstalled) {
        this.hasInstalled = hasInstalled;
    }

    public Long getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Long terminalId) {
        this.terminalId = terminalId;
    }

   
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.serialNumber);
        hash = 97 * hash + Objects.hashCode(this.status);
        hash = 97 * hash + Objects.hashCode(this.modelId);
        hash = 97 * hash + Objects.hashCode(this.model);
        hash = 97 * hash + Objects.hashCode(this.manufacturerId);
        hash = 97 * hash + Objects.hashCode(this.manufacturer);
        hash = 97 * hash + Objects.hashCode(this.placeId);
        hash = 97 * hash + Objects.hashCode(this.placeName);
        hash = 97 * hash + Objects.hashCode(this.placeType);
        hash = 97 * hash + Objects.hashCode(this.departmentCode);
        hash = 97 * hash + Objects.hashCode(this.locationId);
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
        final TerminalComponentDto other = (TerminalComponentDto) obj;
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.manufacturer, other.manufacturer)) {
            return false;
        }
        if (!Objects.equals(this.placeName, other.placeName)) {
            return false;
        }
        if (!Objects.equals(this.placeType, other.placeType)) {
            return false;
        }
        if (!Objects.equals(this.departmentCode, other.departmentCode)) {
            return false;
        }
        if (!Objects.equals(this.locationId, other.locationId)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.modelId, other.modelId)) {
            return false;
        }
        if (!Objects.equals(this.manufacturerId, other.manufacturerId)) {
            return false;
        }
        return Objects.equals(this.placeId, other.placeId);
    }

    @Override
    public String toString() {
        return "TerminalComponentDto{" + "id=" + id + ", serialNumber=" + serialNumber + ", status=" + status + ", modelId=" + modelId + ", model=" + model + ", manufacturerId=" + manufacturerId + ", manufacturer=" + manufacturer + ", placeId=" + placeId + ", placeName=" + placeName + ", placeType=" + placeType + ", departmentCode=" + departmentCode + ", locationId=" + locationId + '}';
    }
    
    
    

}
