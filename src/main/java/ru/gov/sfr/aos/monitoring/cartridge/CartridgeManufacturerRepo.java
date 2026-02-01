package ru.gov.sfr.aos.monitoring.cartridge;

import org.springframework.stereotype.Repository;

import ru.gov.sfr.aos.monitoring.manufacturer.ManufacturerModelRepo;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface CartridgeManufacturerRepo extends ManufacturerModelRepo<CartridgeManufacturer> {

}
