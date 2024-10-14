/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Transfer;
import ru.gov.sfr.aos.monitoring.mappers.TransferMapper;
import ru.gov.sfr.aos.monitoring.models.TransferDto;
import ru.gov.sfr.aos.monitoring.repositories.TransferRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TransferInfoService {
    @Autowired
    private TransferRepo transferRepo;
    @Autowired
    private TransferMapper transferMapper;
    
    public List<TransferDto> getTransfers(Long id) {
        List<Transfer> trans = transferRepo.findByObjectBuingId(id);
        List<TransferDto> dtoes = new ArrayList<>();
        for(Transfer tr : trans) {
            TransferDto dto = transferMapper.toTransferDto(tr);
            dtoes.add(dto);
        }
      return dtoes;  
    }
    
}
