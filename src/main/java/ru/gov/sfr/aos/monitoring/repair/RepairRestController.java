package ru.gov.sfr.aos.monitoring.repair;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.services.ClientDAO;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class RepairRestController {

    @Autowired
    private RepairInfoService repairInfoService;
    @Autowired
    private ClientDAO clientDao;

    @GetMapping("/repairs")
    public List<RepairDto> getRepairs(Long id) {
        List<RepairDto> repairs = repairInfoService.getRepairs(id);
        return repairs;
    }
    //   @Log

    @PostMapping("/repairs")
    public ResponseEntity<String> sendRepair(@RequestBody RepairDto dto) throws IOException {
        clientDao.addRepair(dto);
        return ResponseEntity.ok("Запись сохранена");
    }
    //   @Log

    @DeleteMapping("/repairs")
    public ResponseEntity<String> deleteRepair(Long id) throws IOException {
        clientDao.deleteRepair(id);
        return ResponseEntity.ok("Запись удалена");
    }
    //   @Log

    @PutMapping("/repairs")
    public ResponseEntity<String> updateRepair(@RequestBody RepairDto dto) throws IOException {
        clientDao.editRepair(dto);
        return ResponseEntity.ok("Запись обновлена");
    }

}
