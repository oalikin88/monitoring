/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.entities.Scanner;
import ru.gov.sfr.aos.monitoring.mappers.ScannerMapper;
import ru.gov.sfr.aos.monitoring.models.SvtScannerDTO;

/**
 *
 * @author 041AlikinOS
 */
@Service
public class ScannerOutDtoTreeService extends SvtOutDtoTreeService<Scanner, ScannerMapper, SvtScannerDTO>  {
    
}
