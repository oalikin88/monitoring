/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gov.sfr.aos.monitoring.dictionaries.PlaceType;
import ru.gov.sfr.aos.monitoring.entities.ObjectBuingWithSerialAndInventary;

/**
 *
 * @author 041AlikinOS
 */
@NoRepositoryBean
public interface ObjectBuingWithSerialAndInventaryRepo <E extends ObjectBuingWithSerialAndInventary> extends JpaRepository<E, Long> {
    boolean existsBySerialNumberIgnoreCase(String serialNumber);
    List<E> findByPlacePlaceTypeLikeAndPlaceArchivedFalse(PlaceType placetype);
    List<E> findByPlaceUsernameContainingAndPlacePlaceTypeLikeAndPlaceArchivedFalse(String username, PlaceType placetype);
    List<E> findByPlaceArchivedFalse();
    Optional<E> findById(Long id);
}
