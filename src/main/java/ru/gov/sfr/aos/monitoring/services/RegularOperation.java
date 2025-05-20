/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.services;

import ru.gov.sfr.aos.monitoring.dictionaries.Status;
import ru.gov.sfr.aos.monitoring.enums.ObjectBuingType;

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
    
    public static String getDeviceTypeRus(ObjectBuingType objectBuingType) {
        String result = null;

        switch (objectBuingType) {
        case ATS:
            result = "АТС";
            break;
        case ASUO:
            result = "Электронные очереди";
            break;
        case CONDITIONER:
            result = "Кондиционеры";
            break;
        case DISPLAY:
            result = "Телевизоры";
            break;
        case FAX:
            result = "Факсы";
            break;
        case INFOMAT:
            result = "Инфоматы";
            break;
        case MONITOR:
            result = "Мониторы";
            break;
        case PHONE:
            result = "Телефоны";
            break;
        case ROUTER:
            result = "Маршрутизаторы";
            break;
        case SCANNER:
            result = "Сканеры";
            break;
        case SERVER:
            result = "Серверы";
            break;
        case SWITCHHUB:
            result = "Коммутаторы/Концентарторы";
            break;
        case SYSTEMBLOCK:
            result = "Системные блоки";
            break;
        case TERMINAL:
            result = "Терминалы";
            break;
        case UPS:
            result = "ИБП";
            break;
    }
    return result;
    }

}
