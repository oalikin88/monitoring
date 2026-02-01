package ru.gov.sfr.aos.monitoring.components;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.gov.sfr.aos.monitoring.models.OperationSystemDto;
import ru.gov.sfr.aos.monitoring.services.OperationSystemService;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelDto;
import ru.gov.sfr.aos.monitoring.svtobject.SvtModelMapper;

/**
 *
 * @author Alikin Oleg
 */
@RestController
public class ComponentsRestController {

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
    private OperationSystemMapper operationSystemMapper;
    @Autowired
    private OperationSystemService operationSystemService;
    @Autowired
    private SvtModelMapper svtModelMapper;

    @GetMapping("/modmotherboard")
    public List<SvtModelDto> getModelMotherboard() {

        List<Motherboard> allModels = motherboardModelService.getAllActualModels();
        List<SvtModelDto> motherboardModelsDtoes = svtModelMapper.getModelMotherboardModelsDtoes(allModels);

        return motherboardModelsDtoes;
    }

    @GetMapping("/modcpu")
    public List<CpuModelDto> getModelCpu() {

        List<Cpu> allModels = cpuModelService.getAllActualModels();
        List<CpuModelDto> cpuDtoes = svtModelMapper.getCpuModelDtoes(allModels);

        return cpuDtoes;
    }

    @GetMapping("/modram")
    public List<RamDto> getModelRam() {

        List<Ram> allModels = ramModelService.getAllActualModels();
        List<RamDto> ramDtoes = svtModelMapper.getRamDtoes(allModels);

        return ramDtoes;
    }

    @GetMapping("/modhdd")
    public List<HddDto> getModelHdd() {

        List<Hdd> allModels = hddModelService.getAllActualModels();
        List<HddDto> hddDtoes = svtModelMapper.getHddDtoes(allModels);

        return hddDtoes;
    }

    @GetMapping("/modvideo")
    public List<SvtModelDto> getModelVideoCard() {

        List<VideoCard> allModels = videoCardModelService.getAllActualModels();
        List<SvtModelDto> videoCardDtoes = svtModelMapper.getVideoCardDtoes(allModels);

        return videoCardDtoes;
    }

    @GetMapping("/modcddrive")
    public List<SvtModelDto> getModelCdDrive() {

        List<CdDrive> allModels = cdDriveModelService.getAllActualModels();
        List<SvtModelDto> cdDriveDtoes = svtModelMapper.getCdDriveDtoes(allModels);

        return cdDriveDtoes;
    }

    @GetMapping("/modscard")
    public List<SvtModelDto> getModelSoundCard() {

        List<SoundCard> allModels = soundCardModelService.getAllActualModels();
        List<SvtModelDto> soundCardDtoes = svtModelMapper.getSoundCardDtoes(allModels);

        return soundCardDtoes;
    }

    @GetMapping("/modlcard")
    public List<SvtModelDto> getModelLanCard() {

        List<LanCard> allModels = lanCardModelService.getAllActualModels();
        List<SvtModelDto> lanCardDtoes = svtModelMapper.getLanCardDtoes(allModels);

        return lanCardDtoes;
    }

    @GetMapping("/modkeyboard")
    public List<SvtModelDto> getModelKeyboard() {

        List<Keyboard> allModels = keyboardModelService.getAllActualModels();
        List<SvtModelDto> keyboardDtoes = svtModelMapper.getKeyboardDtoes(allModels);

        return keyboardDtoes;
    }

    @GetMapping("/modmouse")
    public List<SvtModelDto> getModelMouse() {

        List<Mouse> allModels = mouseModelService.getAllActualModels();
        List<SvtModelDto> mouseDtoes = svtModelMapper.getMouseDtoes(allModels);

        return mouseDtoes;
    }

    @GetMapping("/modos")
    public List<OperationSystemDto> getOses() {

        List<OperationSystem> allOperationSystem = operationSystemService.getAllOperationSystem();
        List<OperationSystemDto> operationSystemDtoesList = operationSystemMapper.getOperationSystemDtoesList(allOperationSystem);
        return operationSystemDtoesList;
    }

    @GetMapping("/modspeakers")
    public List<SvtModelDto> getModelSpeakers() {

        List<Speakers> allModels = speakersModelService.getAllActualModels();
        List<SvtModelDto> speakersDtoes = svtModelMapper.getSpeakersDtoes(allModels);

        return speakersDtoes;
    }
}
