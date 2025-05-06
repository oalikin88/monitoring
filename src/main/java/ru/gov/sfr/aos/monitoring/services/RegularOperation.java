/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import ru.gov.sfr.aos.monitoring.dictionaries.Status;

/**
 *
 * @author Alikin Oleg
 */
public class RegularOperation {

    public static String getForCompareValue(String input) {
        String result;
        result = input.replaceAll("[^a-zA-ZА-Яа-я0-9]", "");
        return result;
    }

    public static Status getStatus(String status) {
        Status result = null;
        switch (status) {
            case "REPAIR":
                result = Status.REPAIR;
                break;
            case "MONITORING":
                result = Status.MONITORING;
                break;
            case "UTILIZATION":
                result = Status.UTILIZATION;
                break;
            case "OK":
                result = Status.OK;
                break;
            case "DEFECTIVE":
                result = Status.DEFECTIVE;
                break;
        }
        return result;
    }

}
