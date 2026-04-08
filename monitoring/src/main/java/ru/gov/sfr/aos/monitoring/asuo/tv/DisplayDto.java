package ru.gov.sfr.aos.monitoring.asuo.tv;

import java.util.Objects;
import ru.gov.sfr.aos.monitoring.svtobject.MainSvtDto;

/**
 *
 * @author Alikin Oleg
 */

public class DisplayDto extends MainSvtDto {
    
    public Long id;
    public String serialNumber;
    public String inventaryNumber;
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
    public Long asuoId;

    public DisplayDto() {
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

    public boolean isHasInstalled() {
        return hasInstalled;
    }

    public void setHasInstalled(boolean hasInstalled) {
        this.hasInstalled = hasInstalled;
    }

    public Long getAsuoId() {
        return asuoId;
    }

    public void setAsuoId(Long asuoId) {
        this.asuoId = asuoId;
    }

    public String getInventaryNumber() {
        return inventaryNumber;
    }

    public void setInventaryNumber(String inventaryNumber) {
        this.inventaryNumber = inventaryNumber;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.serialNumber);
        hash = 41 * hash + Objects.hashCode(this.inventaryNumber);
        hash = 41 * hash + Objects.hashCode(this.status);
        hash = 41 * hash + Objects.hashCode(this.modelId);
        hash = 41 * hash + Objects.hashCode(this.model);
        hash = 41 * hash + Objects.hashCode(this.manufacturerId);
        hash = 41 * hash + Objects.hashCode(this.manufacturer);
        hash = 41 * hash + Objects.hashCode(this.placeId);
        hash = 41 * hash + Objects.hashCode(this.placeName);
        hash = 41 * hash + Objects.hashCode(this.placeType);
        hash = 41 * hash + Objects.hashCode(this.departmentCode);
        hash = 41 * hash + Objects.hashCode(this.locationId);
        hash = 41 * hash + (this.hasInstalled ? 1 : 0);
        hash = 41 * hash + Objects.hashCode(this.asuoId);
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
        final DisplayDto other = (DisplayDto) obj;
        if (this.hasInstalled != other.hasInstalled) {
            return false;
        }
        if (!Objects.equals(this.serialNumber, other.serialNumber)) {
            return false;
        }
        if (!Objects.equals(this.inventaryNumber, other.inventaryNumber)) {
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
        if (!Objects.equals(this.placeId, other.placeId)) {
            return false;
        }
        return Objects.equals(this.asuoId, other.asuoId);
    }

    
    
    
    
}
