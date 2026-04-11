package ru.gov.sfr.aos.monitoring.cartridge;

import java.io.Serializable;
import javax.persistence.Entity;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerModel;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class CartridgeManufacturer extends ManufacturerModel<CartridgeModel> implements Serializable {
    
}
