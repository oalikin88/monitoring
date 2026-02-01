package ru.gov.sfr.aos.monitoring.printer;

/**
 *
 * @author Alikin Oleg
 */
public class PrinterModelRegularOperations {

    public static PrintType getPrintType(String input) {
        PrintType result = null;
        switch (input) {
            case "COLOR":
                result = PrintType.COLOR;
                break;
            case "BLACK":
                result = PrintType.BLACK;
                break;
            default:
                result = PrintType.BLACK;
        }
        return result;
    }

    public static PrintFormat getPrintFormat(String input) {
        PrintFormat result = null;
        switch (input) {
            case "A3":
                result = PrintFormat.A3;
                break;
            case "A4":
                result = PrintFormat.A4;
                break;
            default:
                result = PrintFormat.A4;
        }
        return result;
    }

    public static String printTypeToString(PrintType printType) {
        String result = "";
        switch (printType) {
            case COLOR:
                result = "COLOR";
                break;
            case BLACK:
                result = "BLACK";
                break;
            default:
                result = "BLACK";
        }
        return result;
    }
    
     public static String printTypeToStringRus(PrintType printType) {
        String result = "";
        switch (printType) {
            case COLOR:
                result = "цветная";
                break;
            case BLACK:
                result = "черно-белая";
                break;
            default:
                result = "черно-белая";
        }
        return result;
    }

    public static String printFormatToString(PrintFormat printFormat) {
        String result = "";
        switch (printFormat) {
            case A3:
                result = "A3";
                break;
            case A4:
                result = "A4";
                break;
            default:
                result = "A4";
        }
        return result;
    }
}
