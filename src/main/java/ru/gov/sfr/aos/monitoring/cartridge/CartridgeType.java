package ru.gov.sfr.aos.monitoring.cartridge;

/**
 *
 * @author Alikin Oleg
 */
public enum CartridgeType {

    ORIGINAL("Оригинальный"),
    ANALOG("Аналог"),
    START("Стартовый");

    private String name;

    private CartridgeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
