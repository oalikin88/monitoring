package ru.gov.sfr.aos.monitoring.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.services.OperationSystemService;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@Controller
public class ComponentViewController {

    @Autowired
    private MotherboardModelService motherboardModelService;
    @Autowired
    private CpuModelService cpuModelService;
    @Autowired
    private RamModelService ramModelService;
    @Autowired
    private HddModelService hddModelService;
    @Autowired
    private VideoCardModelService videoCardModelService;
    @Autowired
    private CdDriveModelService cdDriveModelService;
    @Autowired
    private SoundCardModelService soundCardModelService;
    @Autowired
    private LanCardModelService lanCardModelService;
    @Autowired
    private KeyboardModelService keyboardModelService;
    @Autowired
    private MouseModelService mouseModelService;
    @Autowired
    private SpeakersModelService speakersModelService;
    @Autowired
    private OperationSystemService operationSystemService;
    @Autowired
    private OperationSystemMapper operationSystemMapper;
    @Autowired
    private SvtModelMapper svtModelMapper;

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmotherboard")
    public String getModelMotherboard(Model model) {

        List<Motherboard> motherboardModels = motherboardModelService.getAllActualModels();
        List<SvtModelDto> getMotherboardModelsDtoes = svtModelMapper.getModelMotherboardModelsDtoes(motherboardModels);
        model.addAttribute("dtoes", getMotherboardModelsDtoes);
        model.addAttribute("namePage", "Модели материнской платы");
        model.addAttribute("attribute", "mmotherboard");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/mmotherboard")
    public ResponseEntity<String> saveModelMotherboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Motherboard modelMotherboard = svtModelMapper.getModelMotherboard(dto);
        try {
            motherboardModelService.saveModel(modelMotherboard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    @PutMapping("/mmotherboardupd")
    public ResponseEntity<String> updateModelMotherboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Motherboard modelMotherboard = svtModelMapper.getModelMotherboard(dto);
        try {
            motherboardModelService.update(modelMotherboard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @SendArchive
    @DeleteMapping("/mmotherboardarchived")
    public ResponseEntity<String> sendModelMotherboardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        motherboardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mcpu")
    public String getModelCpu(Model model) {

        List<Cpu> cpuModels = cpuModelService.getAllActualModels();
        List<CpuModelDto> getCpuDtoes = svtModelMapper.getCpuModelDtoes(cpuModels);
        model.addAttribute("dtoes", getCpuDtoes);
        model.addAttribute("namePage", "Модели процессоров");
        model.addAttribute("attribute", "mcpu");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mcpu")
    public ResponseEntity<String> saveModelCpu(@RequestBody CpuModelDto dto) throws ObjectAlreadyExists {
        Cpu cpu = svtModelMapper.getCpu(dto);
        try {
            cpuModelService.saveModel(cpu);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @SendArchive
    @DeleteMapping("/mcpuarchived")
    public ResponseEntity<String> sendModelCpuToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        cpuModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @UpdLog
    @PutMapping("/mcpuupd")
    public ResponseEntity<String> updateModelCpu(@RequestBody CpuModelDto dto) throws ObjectAlreadyExists {
        Cpu cpu = svtModelMapper.getCpu(dto);
        try {
            cpuModelService.update(cpu);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mram")
    public String getModelRam(Model model) {

        List<Ram> ramModels = ramModelService.getAllActualModels();
        List<RamDto> getRamDtoes = svtModelMapper.getRamDtoes(ramModels);
        model.addAttribute("dtoes", getRamDtoes);
        model.addAttribute("namePage", "Модели ОЗУ");
        model.addAttribute("attribute", "mram");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mram")
    public ResponseEntity<String> saveModelRam(@RequestBody RamDto dto) throws ObjectAlreadyExists {
        Ram ram = svtModelMapper.getRam(dto);
        try {
            ramModelService.saveModel(ram);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @SendArchive
    @DeleteMapping("/mramarchived")
    public ResponseEntity<String> sendModelRamToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        ramModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //     @UpdLog
    @PutMapping("/mramupd")
    public ResponseEntity<String> updateModelRam(@RequestBody RamDto dto) throws ObjectAlreadyExists {
        Ram ram = svtModelMapper.getRam(dto);
        try {
            ramModelService.update(ram);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mhdd")
    public String getModelHdd(Model model) {

        List<Hdd> hddModels = hddModelService.getAllActualModels();
        List<HddDto> getHddDtoes = svtModelMapper.getHddDtoes(hddModels);
        model.addAttribute("dtoes", getHddDtoes);
        model.addAttribute("namePage", "Модели НЖМД");
        model.addAttribute("attribute", "mhdd");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/mhdd")
    public ResponseEntity<String> saveModelHdd(@RequestBody HddDto dto) throws ObjectAlreadyExists {
        Hdd hdd = svtModelMapper.getHdd(dto);
        try {
            hddModelService.saveModel(hdd);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

//    @SendArchive
    @DeleteMapping("/mhddarchived")
    public ResponseEntity<String> sendModelHddToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        hddModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    @PutMapping("/mhddupd")
    public ResponseEntity<String> updateModelHdd(@RequestBody HddDto dto) throws ObjectAlreadyExists {
        Hdd hdd = svtModelMapper.getHdd(dto);
        try {
            hddModelService.update(hdd);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mvideo")
    public String getModelVideoCard(Model model) {

        List<VideoCard> videoCardModels = videoCardModelService.getAllActualModels();
        List<SvtModelDto> getVideoCardDtoes = svtModelMapper.getVideoCardDtoes(videoCardModels);
        model.addAttribute("dtoes", getVideoCardDtoes);
        model.addAttribute("namePage", "Модели видеоадаптеров");
        model.addAttribute("attribute", "mvideo");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mvideo")
    public ResponseEntity<String> saveModelVideoCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        VideoCard videocard = svtModelMapper.getVideoCard(dto);
        try {
            videoCardModelService.saveModel(videocard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //     @SendArchive
    @DeleteMapping("/mvideoarchived")
    public ResponseEntity<String> sendModelVideoCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        videoCardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //   @UpdLog
    @PutMapping("/mvideoupd")
    public ResponseEntity<String> updateModelVideoCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        VideoCard videocard = svtModelMapper.getVideoCard(dto);
        try {
            videoCardModelService.update(videocard);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mcddrive")
    public String getModelCdDrive(Model model) {

        List<CdDrive> cdDriveModels = cdDriveModelService.getAllActualModels();
        List<SvtModelDto> getCdDriveDtoes = svtModelMapper.getCdDriveDtoes(cdDriveModels);
        model.addAttribute("dtoes", getCdDriveDtoes);
        model.addAttribute("namePage", "Модели приводов");
        model.addAttribute("attribute", "mcddrive");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //  @Log
    @PostMapping("/mcddrive")
    public ResponseEntity<String> saveModelCdDrive(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        CdDrive cdDrive = svtModelMapper.getCdDrive(dto);
        try {
            cdDriveModelService.saveModel(cdDrive);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //  @SendArchive
    @DeleteMapping("/mcddrivearchived")
    public ResponseEntity<String> sendModelCdDriveToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        cdDriveModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @UpdLog
    @PutMapping("/mcddriveupd")
    public ResponseEntity<String> updateModelCdDrive(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        CdDrive cdDrive = svtModelMapper.getCdDrive(dto);
        try {
            cdDriveModelService.update(cdDrive);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mscard")
    public String getModelSCard(Model model) {

        List<SoundCard> soundCardModels = soundCardModelService.getAllActualModels();
        List<SvtModelDto> getSoundCardDtoes = svtModelMapper.getSoundCardDtoes(soundCardModels);
        model.addAttribute("dtoes", getSoundCardDtoes);
        model.addAttribute("namePage", "Модели звуковых карт");
        model.addAttribute("attribute", "mscard");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mscard")
    public ResponseEntity<String> saveModelSCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SoundCard soundCard = svtModelMapper.getSoundCard(dto);
        try {
            soundCardModelService.saveModel(soundCard);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //    @SendArchive
    @DeleteMapping("/mscardarchived")
    public ResponseEntity<String> sendModelSCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        soundCardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @UpdLog
    @PutMapping("/mscardupd")
    public ResponseEntity<String> updateModelSCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        SoundCard soundCard = svtModelMapper.getSoundCard(dto);
        try {
            soundCardModelService.update(soundCard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mlcard")
    public String getLansCard(Model model) {

        List<LanCard> lanCardModels = lanCardModelService.getAllActualModels();
        List<SvtModelDto> getLanCardDtoes = svtModelMapper.getLanCardDtoes(lanCardModels);
        model.addAttribute("dtoes", getLanCardDtoes);
        model.addAttribute("namePage", "Модели сетевых карт");
        model.addAttribute("attribute", "mlcard");

        return "models";
    }

    //@PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mlcard")
    public ResponseEntity<String> saveModelLanCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        LanCard lanCard = svtModelMapper.getLanCard(dto);
        try {
            lanCardModelService.saveModel(lanCard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.ACCEPTED);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //     @SendArchive
    @DeleteMapping("/mlcardarchived")
    public ResponseEntity<String> sendModelLanCardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        lanCardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //      @Log
    @PutMapping("/mlcardupd")
    public ResponseEntity<String> updateModelLanCard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        LanCard lanCard = svtModelMapper.getLanCard(dto);
        try {
            lanCardModelService.update(lanCard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //     @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mkeyboard")
    public String getKeyboard(Model model) {

        List<Keyboard> keyboardModels = keyboardModelService.getAllActualModels();
        List<SvtModelDto> getKeyboardDtoes = svtModelMapper.getKeyboardDtoes(keyboardModels);
        model.addAttribute("dtoes", getKeyboardDtoes);
        model.addAttribute("namePage", "Модели клавиатур");
        model.addAttribute("attribute", "mkeyboard");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/mkeyboard")
    public ResponseEntity<String> saveModelKeyboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Keyboard keyboard = svtModelMapper.getKeyboard(dto);
        try {
            keyboardModelService.saveModel(keyboard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //    @SendArchive
    @DeleteMapping("/mkeyboardarchived")
    public ResponseEntity<String> sendModelKeyboardToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        keyboardModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //       @UpdLog
    @PutMapping("/mkeyboardupd")
    public ResponseEntity<String> updateModelKeyboard(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Keyboard keyboard = svtModelMapper.getKeyboard(dto);
        try {
            keyboardModelService.update(keyboard);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mmouse")
    public String getMouse(Model model) {

        List<Mouse> mouseModels = mouseModelService.getAllActualModels();
        List<SvtModelDto> getMouseModelsDtoes = svtModelMapper.getMouseDtoes(mouseModels);
        model.addAttribute("dtoes", getMouseModelsDtoes);
        model.addAttribute("namePage", "Модели мышей");
        model.addAttribute("attribute", "mmouse");

        return "models";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mmouse")
    public ResponseEntity<String> saveModelMouse(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Mouse mouse = svtModelMapper.getMouse(dto);
        try {
            mouseModelService.saveModel(mouse);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

//    @SendArchive
    @DeleteMapping("/mmousearchived")
    public ResponseEntity<String> sendModelMouseToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        mouseModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //   @UpdLog
    @PutMapping("/mmouseupd")
    public ResponseEntity<String> updateModelMouse(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Mouse mouse = svtModelMapper.getMouse(dto);
        try {
            mouseModelService.update(mouse);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/mspeakers")
    public String getSpeakers(Model model) {

        List<Speakers> speakersModels = speakersModelService.getAllActualModels();
        List<SvtModelDto> getSpeakersModelsDtoes = svtModelMapper.getSpeakersDtoes(speakersModels);
        model.addAttribute("dtoes", getSpeakersModelsDtoes);
        model.addAttribute("namePage", "Модели колонок");
        model.addAttribute("attribute", "mspeakers");

        return "models";
    }

    //   @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @Log
    @PostMapping("/mspeakers")
    public ResponseEntity<String> saveModelSpeakers(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Speakers speakers = svtModelMapper.getSpeakers(dto);
        try {
            speakersModelService.saveModel(speakers);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //    @SendArchive
    @DeleteMapping("/mspeakersarchived")
    public ResponseEntity<String> sendModelSpeakersToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        speakersModelService.sendModelToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //    @UpdLog
    @PutMapping("/mspeakersupd")
    public ResponseEntity<String> updateModelSpeakers(@RequestBody SvtModelDto dto) throws ObjectAlreadyExists {
        Speakers speakers = svtModelMapper.getSpeakers(dto);
        try {
            speakersModelService.update(speakers);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    //      @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    @GetMapping("/os")
    public String getOperationSystems(Model model) {

        List<OperationSystem> allOperationSystem = operationSystemService.getAllOperationSystem();
        List<OperationSystemDto> operationSystemDtoesList = operationSystemMapper.getOperationSystemDtoesList(allOperationSystem);
        model.addAttribute("dtoes", operationSystemDtoesList);
        model.addAttribute("namePage", "Операционные системы");
        model.addAttribute("attribute", "os");
        return "models";
    }

//    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
//    @Log
    @PostMapping("/os")
    public ResponseEntity<String> saveOperationSystem(@RequestBody OperationSystemDto dto) throws ObjectAlreadyExists {
        OperationSystem operationSystem = operationSystemMapper.getOperationSystem(dto);
        operationSystemService.saveOperationSystem(operationSystem);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    //    @PreAuthorize("hasAuthority('ROLE_READ') || hasAuthority('ROLE_ADMIN')")
    //   @UpdLog
    @PutMapping("/osupd")
    public ResponseEntity<String> updateOperationSystem(@RequestBody OperationSystemDto dto) throws ObjectAlreadyExists {
        OperationSystem operationSystem = operationSystemMapper.getOperationSystem(dto);
        operationSystemService.updateOperationSystem(operationSystem);
        return new ResponseEntity(HttpStatus.ACCEPTED);

    }

    @DeleteMapping("/osarchived")
    public ResponseEntity<String> osToArchive(@RequestBody ArchivedDto dto) throws ObjectAlreadyExists {
        operationSystemService.sendOsToArchive(dto.getId());
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

}
