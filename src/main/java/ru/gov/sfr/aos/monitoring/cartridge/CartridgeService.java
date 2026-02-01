package ru.gov.sfr.aos.monitoring.cartridge;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.place.Place;
import ru.gov.sfr.aos.monitoring.place.PlaceRepo;
import ru.gov.sfr.aos.monitoring.svtobject.SvtObjectBuingService;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class CartridgeService extends SvtObjectBuingService<Cartridge, CartridgeRepo, CartridgeDto>  {
    
    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private PlaceRepo placeRepo;

    @Override
    public void createSvtObj(CartridgeDto dto) throws ObjectAlreadyExists {
        Cartridge cartridge = new Cartridge();
        Place place = placeRepo.findById(dto.getPlaceId()).orElseThrow(() -> new NoSuchElementException("Рабочее место не найдено"));
        cartridge.setPlace(place);
        cartridge.setItemCode(dto.getItemCode());
        cartridge.setNameMaterial(dto.getNameMaterial());
        cartridge.setCount(dto.getCount());
        
        
        cartridgeRepo.save(cartridge);
    }

    @Override
    public void updateSvtObj(CartridgeDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
