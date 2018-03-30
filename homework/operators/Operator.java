package homework.operators;

import homework.interfaces.operators.IOperator;

public class Operator implements IOperator {

    private final int priority = 0;
    private String symbol;

    @Override
    public final String getSymbol() {
        return symbol;
    }

    @Override
    public final void setSymbol(final String symbolP) {
        this.symbol = symbolP;

    }

    @Override
    public final int getPriority() {

        return priority;
    }

}
