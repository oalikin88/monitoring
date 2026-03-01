package ru.gov.sfr.aos.monitoring.printer;

import java.util.HashSet;
import java.util.Set;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;

/**
 *
 * @author Alikin Oleg
 */

public class PrinterModelDto extends SvtModelDto {
    
    public String printColorType;
    public String printFormat;
    public int printSpeed;
    public String printColorTypeRus;
    public Set<Long> modelCartridges = new HashSet<>();

    public PrinterModelDto() {
    }

    public String getPrintColorType() {
        return printColorType;
    }

    public void setPrintColorType(String printColorType) {
        this.printColorType = printColorType;
    }

    public String getPrintFormat() {
        return printFormat;
    }

    public void setPrintFormat(String printFormat) {
        this.printFormat = printFormat;
    }

    public int getPrintSpeed() {
        return printSpeed;
    }

    public void setPrintSpeed(int printSpeed) {
        this.printSpeed = printSpeed;
    }

    public Set<Long> getModelCartridges() {
        return modelCartridges;
    }

    public void setModelCartridges(Set<Long> modelCartridges) {
        this.modelCartridges = modelCartridges;
    }

    public String getPrintColorTypeRus() {
        return printColorTypeRus;
    }

    public void setPrintColorTypeRus(String printColorTypeRus) {
        this.printColorTypeRus = printColorTypeRus;
    }
    
    
    
}
