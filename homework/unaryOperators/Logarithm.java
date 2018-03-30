package homework.unaryOperators;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operators.unaryOperators.IUnaryOperator;
import homework.operands.ArabOperand;
import homework.operands.OperandsFactory;

public class Logarithm implements IUnaryOperator<Double> {

    private final int priority = 3;
    private String symbol;

    @Override
    public final int getPriority() {
        return priority;
    }

    @Override
    public final String getSymbol() {
        return symbol;
    }

    @Override
    public final void setSymbol(final String symbolP) {
        this.symbol = symbolP;

    }

    @Override
    public final IOperand<Double> calculate(final Double operand) {
        ArabOperand log = (ArabOperand) OperandsFactory.getInstance()
                .createOperand(operand.toString());

        log.setSymbolValue(Math.log(operand));

        return log;
    }

}
