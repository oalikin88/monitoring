package ru.gov.sfr.aos.monitoring.transfer;

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
public class TransferRestController {

    @Autowired
    private ClientDAO clientDao;
    @Autowired
    private TransferInfoService transferInfoService;

    @GetMapping("/transfers")
    public List<TransferDto> getTransfers(Long id) {
        List<TransferDto> transfers = transferInfoService.getTransfers(id);
        return transfers;
    }
    //     @Log("Save transfer")

    @PostMapping("/transfers")
    public ResponseEntity<String> sendTransfer(@RequestBody TransferDto dto) throws IOException {
        clientDao.addTransfer(dto);
        return ResponseEntity.ok("Запись сохранена");
    }
//        @Log

    @DeleteMapping("/transfers")
    public ResponseEntity<String> deleteTransfer(Long id) throws IOException {
        clientDao.deleteTransfer(id);
        return ResponseEntity.ok("Запись удалена");
    }
    //  @Log

    @PutMapping("/transfers")
    public ResponseEntity<String> updateTransfer(@RequestBody TransferDto dto) throws IOException {
        clientDao.editTransfer(dto);
        return ResponseEntity.ok("Запись обновлена");
    }

}
