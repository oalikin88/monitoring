package ru.gov.sfr.aos.monitoring.printer;

import java.io.Serializable;
import javax.persistence.Entity;
import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerModel;

/**
 *
 * @author Alikin Oleg
 */
@Entity
public class PrinterManufacturer extends ManufacturerModel<PrinterModel> implements Serializable {

}
