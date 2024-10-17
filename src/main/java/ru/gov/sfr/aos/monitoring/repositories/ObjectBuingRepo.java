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
import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;

/**
 *
 * @author 041AlikinOS
 */
@NoRepositoryBean
public interface ObjectBuingRepo <E extends ObjectBuing> extends JpaRepository<E, Long> {
    
    List<E> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    List<E> findByPlaceUsernameContainingAndPlacePlaceTypeLikeAndArchivedFalse(String username, PlaceType placetype);
    List<E> findByPlaceArchivedFalse();
    Optional<E> findById(Long id);
}