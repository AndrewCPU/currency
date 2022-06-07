package net.andrewcpu;

public enum Currency {
    USD(1.0, "$"),
    EURO(0.94, "€"),
    YEN(132.58, "¥");


    private final double conversionToUSD;
    private final String symbol;

    Currency(double conversionToUSD, String symbol) {
        this.conversionToUSD = conversionToUSD;
        this.symbol = symbol;
    }

    public double getConversionToUSD() {
        return conversionToUSD;
    }

    public String getSymbol() {
        return symbol;
    }
}
