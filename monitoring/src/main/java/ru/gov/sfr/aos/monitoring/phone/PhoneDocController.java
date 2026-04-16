package ru.gov.sfr.aos.monitoring.phone;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gov.sfr.aos.monitoring.models.FilterRequest;

/**
 *
 * @author Alikin Oleg
 */
@RestController
@RequestMapping("/get-doc")
public class PhoneDocController {

    @Autowired
    private PhoneReportService phoneReportService;

    @GetMapping("/phones")
    public ResponseEntity<Resource> getPhones(FilterRequest filterRequest) throws FileNotFoundException, IOException {
        
        byte[] report = phoneReportService.generateReport(filterRequest);
        
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=phones.xlsx")
            .contentType(MediaType.APPLICATION_OCTET_STREAM)
            .body(new ByteArrayResource(report));

    }

}
