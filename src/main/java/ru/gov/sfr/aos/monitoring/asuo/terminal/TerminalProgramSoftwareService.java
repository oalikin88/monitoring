package ru.gov.sfr.aos.monitoring.asuo.terminal;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.gov.sfr.aos.monitoring.asuo.ProgramSoftware;
import ru.gov.sfr.aos.monitoring.exceptions.ObjectAlreadyExists;
import ru.gov.sfr.aos.monitoring.mappers.ProgramSoftwareMapper;
import ru.gov.sfr.aos.monitoring.models.ArchivedDto;
import ru.gov.sfr.aos.monitoring.models.ProgramSoftwareDto;
import ru.gov.sfr.aos.monitoring.repositories.ProgramSoftwareRepo;

/**
 *
 * @author Alikin Oleg
 */
@Service
public class TerminalProgramSoftwareService {

    @Autowired
    private ProgramSoftwareRepo programSoftwareRepo;
    @Autowired
    private ProgramSoftwareMapper mapper;

    public void createProgramSoftware(ProgramSoftwareDto dto) throws ObjectAlreadyExists {
        if (programSoftwareRepo.existsByNameIgnoreCase(dto.getName())) {
            List<ProgramSoftware> programSoftwareList = programSoftwareRepo.findByNameIgnoreCase(dto.getName());
            if (!programSoftwareList.isEmpty()) {
                Optional<ProgramSoftware> findFirst = programSoftwareList.stream().filter(e -> e.getVersion() == Double.parseDouble(dto.getVersion())).findFirst();
                if (findFirst.isPresent()) {
                    throw new ObjectAlreadyExists("Терминал с таким серийным номером уже есть в базе данных");
                }
            }

        } else {
            ProgramSoftware programSoftware = new ProgramSoftware();
            programSoftware.setName(dto.getName().strip());
            programSoftware.setVersion(Double.parseDouble(dto.getVersion()));
            programSoftwareRepo.save(programSoftware); 
        }
    }

    public void updateProgramSoftware(ProgramSoftwareDto dto) {
        ProgramSoftware programSoftware = programSoftwareRepo.findById(dto.getId()).get();
        programSoftware.setName(dto.getName().strip());
        programSoftware.setVersion(Double.parseDouble(dto.getVersion()));
        programSoftwareRepo.save(programSoftware);
    }

    public void deleteProgramSoftware(ArchivedDto dto) {
        ProgramSoftware programSoftware = programSoftwareRepo.findById(dto.getId()).get();
        programSoftware.setArchived(true);
        programSoftwareRepo.save(programSoftware);
    }
    
    public List<ProgramSoftware> getProgramSoftwares() {
        return programSoftwareRepo.findByArchivedFalse();
    } 
    
    public List<ProgramSoftwareDto> getProgramSoftwareDtoesList(List<ProgramSoftware> inputList) {
        return inputList.stream().map(e -> mapper.toDto(e)).collect(Collectors.toList());
    }
    
    
}
