/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.transfer;

import org.springframework.stereotype.Component;
import ru.gov.sfr.aos.monitoring.transfer.TransferDto;

/**
 *
 * @author Alikin Oleg
 */
@Component
public class TransferMapper {
    public TransferDto toTransferDto(Transfer transfer) {
        TransferDto dto = new TransferDto();
        dto.setId(transfer.getId());
        dto.setDateTransfer(transfer.getDateTransfer());
        dto.setDocumentNumber(transfer.getDocumentNumber());
        dto.setInventaryNumberNew(transfer.getInventaryNumberNew());
        dto.setInventaryNumberOld(transfer.getInventaryNumberOld());
        dto.setTransferFrom(transfer.getTransferFrom());
        dto.setTransferTo(transfer.getTransferTo());
        return dto;
    }
}
