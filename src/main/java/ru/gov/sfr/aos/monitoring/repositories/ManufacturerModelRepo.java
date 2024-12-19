/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gov.sfr.aos.monitoring.entities.ManufacturerModel;

/**
 *
 * @author Alikin Oleg
 */
@NoRepositoryBean
public interface ManufacturerModelRepo<E extends ManufacturerModel> extends JpaRepository <E, Long> {
    List<E> findByNameIgnoreCase(String name);
    List<E> findByArchivedFalse();
    boolean existsByNameIgnoreCase(String name);
}
