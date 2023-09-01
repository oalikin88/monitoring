
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;
import ru.gov.sfr.aos.monitoring.models.CartridgeModelDTO;
import ru.gov.sfr.aos.monitoring.models.LocationDTO;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author 041AlikinOS
 */
public class ConsumptionDTO {
    
    public CartridgeModelDTO model;
    public LocationDTO location;
    public int consumption;
    public int period;

    public ConsumptionDTO() {
    }

    public ConsumptionDTO(CartridgeModelDTO model, LocationDTO location, int consumption, int period) {
        this.model = model;
        this.location = location;
        this.consumption = consumption;
        this.period = period;
    }

    public CartridgeModelDTO getModel() {
        return model;
    }

    public void setModel(CartridgeModelDTO model) {
        this.model = model;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
    
    
    
    
}
