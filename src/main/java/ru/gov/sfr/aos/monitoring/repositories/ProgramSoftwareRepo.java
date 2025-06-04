package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gov.sfr.aos.monitoring.asuo.ProgramSoftware;

/**
 *
 * @author Alikin Oleg
 */
@Repository
public interface ProgramSoftwareRepo extends JpaRepository<ProgramSoftware, Long>{
    boolean existsByNameIgnoreCase(String programSoftwareName);
    List<ProgramSoftware> findByNameIgnoreCase(String programSoftwareName);
    List<ProgramSoftware> findByArchivedFalse();
}
