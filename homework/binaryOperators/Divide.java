package homework.binaryOperators;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operators.binaryOperators.IBinaryOperator;
import homework.operands.ArabOperand;
import homework.operands.OperandsFactory;

public class Divide implements IBinaryOperator<Double> {

    private final int priority = 1;
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
    public final IOperand<Double> calculate(final Double leftOperand,
            final Double rightOperand) {
        ArabOperand div = (ArabOperand) OperandsFactory.getInstance()
                .createOperand(leftOperand.toString());

        div.setSymbolValue(leftOperand / rightOperand);

        return div;
    }

}
