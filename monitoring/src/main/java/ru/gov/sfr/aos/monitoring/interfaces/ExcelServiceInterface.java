package ru.gov.sfr.aos.monitoring.interfaces;

import java.io.IOException;
import java.util.List;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;

/**
 *
 * @author oalikin88
 */
public interface ExcelServiceInterface {
    
    public byte[] buildReport(List<LocationByTreeDto> employeeTree,
           List<LocationByTreeDto> storageTree, String name) throws IOException;
}
