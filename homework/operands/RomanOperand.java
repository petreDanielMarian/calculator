package homework.operands;

import homework.interfaces.operands.IOperand;

public class RomanOperand implements IOperand<Double> {

    private String symbol;
    private double value;

    @Override
    public final String getSymbol() {
        return symbol;

    }

    @Override
    public final void setSymbol(final String symbol) {
        this.symbol = symbol;

    }

    @Override
    public final Double getSymbolValue() {
        return value;

    }

    @Override
    public final void setSymbolValue(final Double value) {
        this.value = value;

    }

}
