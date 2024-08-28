/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gov.sfr.aos.monitoring.entities.SvtModel;

/**
 *
 * @author 041AlikinOS
 */
@NoRepositoryBean
public interface SvtModelsRepo <E extends SvtModel> extends JpaRepository <E, Long> {
    List<E> findByModelIgnoreCase(String model);
    List<E> findByArchivedFalse();
}
