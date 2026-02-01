package ru.gov.sfr.aos.monitoring.contract;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ru.gov.sfr.aos.monitoring.cartridge.CartridgeDto;

/**
 *
 * @author Alikin Oleg
 */

public class ContractDto {

    public Long id;
    public String contractNumber;
    public Date dateStartContract;
    public Date dateEndContract;
    public List<CartridgeDto> cartidges = new ArrayList<>();

    public ContractDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getDateStartContract() {
        return dateStartContract;
    }

    public void setDateStartContract(Date dateStartContract) {
        this.dateStartContract = dateStartContract;
    }

    public Date getDateEndContract() {
        return dateEndContract;
    }

    public void setDateEndContract(Date dateEndContract) {
        this.dateEndContract = dateEndContract;
    }

    public List<CartridgeDto> getCartidges() {
        return cartidges;
    }

    public void setCartidges(List<CartridgeDto> cartidges) {
        this.cartidges = cartidges;
    }
    
    
    
    
}
