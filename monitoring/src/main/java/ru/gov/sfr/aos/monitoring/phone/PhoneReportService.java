package ru.gov.sfr.aos.monitoring.phone;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gov.sfr.aos.monitoring.interfaces.ReportServiceInterface;
import ru.gov.sfr.aos.monitoring.location.LocationByTreeDto;
import ru.gov.sfr.aos.monitoring.models.FilterRequest;
import ru.gov.sfr.aos.monitoring.place.PlaceType;

/**
 *
 * @author oalikin88
 */
@Service
public class PhoneReportService implements ReportServiceInterface {

    @Autowired
    private ExcelPhoneService excelPhoneService;
    @Autowired
    private PhoneTreeService phoneTreeService;
    
    
    @Override
    public byte[] generateReport(FilterRequest request) throws IOException {
        List<LocationByTreeDto> employeeTree
                = phoneTreeService.getTree(request, PlaceType.EMPLOYEE);

        List<LocationByTreeDto> storageTree
                = phoneTreeService.getTree(request, PlaceType.STORAGE);

        return excelPhoneService.buildReport(employeeTree, storageTree, "Телефоны");
    }

}
