package ru.gov.sfr.aos.monitoring.interfaces;

import java.io.IOException;
import ru.gov.sfr.aos.monitoring.models.FilterRequest;

/**
 *
 * @author oalikin88
 */
public interface ReportServiceInterface {
    
    public byte[] generateReport(FilterRequest filterRequest) throws IOException;
    
}
