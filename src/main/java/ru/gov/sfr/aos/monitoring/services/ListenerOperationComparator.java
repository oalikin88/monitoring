/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import java.util.Comparator;
import ru.gov.sfr.aos.monitoring.entities.ListenerOperation;

/**
 *
 * @author 041AlikinOS
 */
public class ListenerOperationComparator implements Comparator<ListenerOperation>{

    @Override
    public int compare(ListenerOperation o1, ListenerOperation o2) {
        return o1.getDateOperation().compareTo(o2.getDateOperation());
    }
    
}
