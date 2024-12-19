/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

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
    
}
