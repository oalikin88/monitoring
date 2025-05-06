package ru.gov.sfr.aos.monitoring.models;

/**
 *
 * @author Alikin Oleg
 */

public class TerminalDisplayDto extends TerminalComponentDto {
    
    public String screenDiagonal;

    public TerminalDisplayDto() {
    }

    public String getScreenDiagonal() {
        return screenDiagonal;
    }

    public void setScreenDiagonal(String screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
    }
    
    

}
