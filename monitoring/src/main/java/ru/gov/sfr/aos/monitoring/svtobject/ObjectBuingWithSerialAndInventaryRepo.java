/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.svtobject;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.gov.sfr.aos.monitoring.place.PlaceType;

/**
 *
 * @author 041AlikinOS
 */
@NoRepositoryBean
public interface ObjectBuingWithSerialAndInventaryRepo <E extends ObjectBuingWithSerialAndInventary> extends JpaRepository<E, Long> {
    boolean existsBySerialNumberIgnoreCaseAndArchivedFalse(String serialNumber);
    boolean existsByInventaryNumberIgnoreCaseAndArchivedFalse(String inventaryNumber);
    List<E> findBySerialNumberIgnoreCaseAndArchivedFalse(String serialNumber);
    List<E> findByInventaryNumberIgnoreCaseAndArchivedFalse(String inventaryNumber);
    List<E> findByPlacePlaceTypeLikeAndArchivedFalse(PlaceType placetype);
    List<E> findByPlaceUsernameContainingAndPlacePlaceTypeLikeAndArchivedFalse(String username, PlaceType placetype);
    List<E> findByInventaryNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String inventaryNumber, PlaceType placetype);
    List<E> findBySerialNumberContainingAndPlacePlaceTypeLikeAndArchivedFalse(String serialNumber, PlaceType placetype);
    List<E> findByPlaceArchivedFalse();
    List<E> findByArchivedFalse();
    Optional<E> findById(Long id);
    List<E> findByPlaceIdAndArchivedFalse(Long id);
}
