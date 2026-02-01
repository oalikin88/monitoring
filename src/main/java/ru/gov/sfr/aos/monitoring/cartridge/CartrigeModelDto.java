package ru.gov.sfr.aos.monitoring.cartridge;

import java.util.HashSet;
import java.util.Set;

import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */

public class CartrigeModelDto extends SvtModelDto {
    
    public String type;
    public Set<Long> modelPrinters = new HashSet<>();
    public Long defaultNumberPrintPage;
    public boolean archived;

    public CartrigeModelDto() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<Long> getModelPrinters() {
        return modelPrinters;
    }

    public void setModelPrinters(Set<Long> modelPrinters) {
        this.modelPrinters = modelPrinters;
    }

    public Long getDefaultNumberPrintPage() {
        return defaultNumberPrintPage;
    }

    public void setDefaultNumberPrintPage(Long defaultNumberPrintPage) {
        this.defaultNumberPrintPage = defaultNumberPrintPage;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    
    
}
