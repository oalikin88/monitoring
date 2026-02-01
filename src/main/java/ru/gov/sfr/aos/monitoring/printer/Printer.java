package ru.gov.sfr.aos.monitoring.printer;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.NotNull;

import ru.gov.sfr.aos.monitoring.cartridge.Cartridge;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuingWithSerialAndInventary;

/**
 *
 * @author Alikin Oleg
 */
@Entity
@PrimaryKeyJoinColumn(name = "PRINTER_ID")
public class Printer extends ObjectBuingWithSerialAndInventary implements Serializable {

    @NotNull(message = "Поле \"Модель\" не должно быть пустым")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PrinterModel printerModel;
    private int yearCreated;
    private Date dateExploitationBegin;
    private String nameFromeOneC;
    private String numberRoom;
    @OneToMany(mappedBy = "printer", fetch = FetchType.LAZY)
    private Set<Cartridge> cartridge;

    public Printer() {
    }

    public PrinterModel getPrinterModel() {
        return printerModel;
    }

    public void setPrinterModel(PrinterModel printerModel) {
        this.printerModel = printerModel;
    }

    public int getYearCreated() {
        return yearCreated;
    }

    public void setYearCreated(int yearCreated) {
        this.yearCreated = yearCreated;
    }

    public Date getDateExploitationBegin() {
        return dateExploitationBegin;
    }

    public void setDateExploitationBegin(Date dateExploitationBegin) {
        this.dateExploitationBegin = dateExploitationBegin;
    }

    public String getNameFromeOneC() {
        return nameFromeOneC;
    }

    public void setNameFromeOneC(String nameFromeOneC) {
        this.nameFromeOneC = nameFromeOneC;
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
    }

    public Set<Cartridge> getCartridge() {
        return cartridge;
    }

    public void setCartridge(Set<Cartridge> cartridge) {
        this.cartridge = cartridge;
    }
    
    
    
    
    
}
