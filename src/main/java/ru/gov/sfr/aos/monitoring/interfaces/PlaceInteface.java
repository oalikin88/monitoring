/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.interfaces;

import ru.gov.sfr.aos.monitoring.entities.ObjectBuing;

/**
 *
 * @author 041AlikinOS
 */
public interface PlaceInteface {
    
   void backFromStorage(ObjectBuing objectBuing, Long placeId);
   void sendToStorage(ObjectBuing objectBuing);
}
