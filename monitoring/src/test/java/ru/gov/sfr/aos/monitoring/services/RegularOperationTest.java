/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.gov.sfr.aos.monitoring.dictionaries.Status;

/**
 *
 * @author Alikin Oleg
 */
public class RegularOperationTest {
    

    @Test
    public void testGetStatus() {
        assertEquals(Status.MONITORING, RegularOperation.getStatus("MONITORING"));
        assertEquals(Status.REPAIR, RegularOperation.getStatus("REPAIR"));
        assertEquals(Status.UTILIZATION, RegularOperation.getStatus("UTILIZATION"));
        assertEquals(Status.OK, RegularOperation.getStatus("OK"));
        assertEquals(Status.DEFECTIVE, RegularOperation.getStatus("DEFECTIVE"));
    }


    @Test
    public void mayDoUpperCaseStringTestGetStatus() {
        assertEquals(Status.MONITORING, RegularOperation.getStatus("monitoring"));
        assertEquals(Status.REPAIR, RegularOperation.getStatus("repair"));
        assertEquals(Status.UTILIZATION, RegularOperation.getStatus("utilization"));
        assertEquals(Status.OK, RegularOperation.getStatus("ok"));
        assertEquals(Status.DEFECTIVE, RegularOperation.getStatus("defective"));
    }
    
}
