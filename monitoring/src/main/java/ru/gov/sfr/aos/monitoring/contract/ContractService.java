package ru.gov.sfr.aos.monitoring.contract;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.cartridge.Cartridge;
import ru.gov.sfr.aos.monitoring.cartridge.CartridgeDto;
import ru.gov.sfr.aos.monitoring.cartridge.CartridgeModel;
import ru.gov.sfr.aos.monitoring.cartridge.CartridgeModelRepo;
import ru.gov.sfr.aos.monitoring.cartridge.CartridgeRepo;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.svtobject.ObjectBuing;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class ContractService {

    @Autowired
    private ContractRepo contractRepo;
    @Autowired
    private CartridgeRepo cartridgeRepo;
    @Autowired
    private CartridgeModelRepo cartridgeModelRepo;
    
    public void create(ContractDto dto) throws ObjectAlreadyExists {
        Contract contract = new Contract();
        List <ObjectBuing> objectBuingList = new ArrayList<>();
        if(contractRepo.existsByContractNumberIgnoreCase(dto.getContractNumber())) {
            throw new ObjectAlreadyExists("Такой контракт уже есть в базе данных");
        } else {
            contract.setContractNumber(dto.getContractNumber());
        }
        
        contract.setDateEndContract(dto.getDateEndContract());
        contract.setDateStartContract(dto.getDateStartContract());
        
        if(!dto.getCartidges().isEmpty()) {
            List<CartridgeModel> allModelCartridgeList = cartridgeModelRepo.findAll();
            for(CartridgeDto cartDto : dto.getCartidges()) {
                Cartridge cartridge = new Cartridge();
                cartridge.setContract(contract);
                cartridge.setCount(cartDto.getCount());
                cartridge.setModel(allModelCartridgeList
                        .stream()
                        .filter(e -> e.getId() == cartDto.getId())
                        .findFirst().orElseThrow(() -> new NoSuchElementException("Отустствует модель картриджа")));
                cartridge.setNameMaterial(cartDto.getNameMaterial());
                cartridge.setItemCode(cartDto.getItemCode());
                objectBuingList.add(cartridge);
                
            }
        }
        contract.setObjectBuing(objectBuingList);
        contractRepo.save(contract);
    }
}
