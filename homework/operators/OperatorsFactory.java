package homework.operators;

import homework.binaryOperators.Add;
import homework.binaryOperators.Divide;
import homework.binaryOperators.Multiply;
import homework.binaryOperators.Power;
import homework.binaryOperators.Substract;
import homework.interfaces.IToken;
import homework.interfaces.operators.IOperator;
import homework.interfaces.operators.IOperatorsFactory;
import homework.unaryOperators.Logarithm;
import homework.unaryOperators.SquareRoot;

public final class OperatorsFactory implements IOperatorsFactory {

    private static OperatorsFactory instance = new OperatorsFactory();

    private OperatorsFactory() {
    }

    public static OperatorsFactory getInstance() {
        return instance;
    }

    @Override
    public IOperator createOperator(final String token) {
        if (token == null) {
            return null;
        }

        if (token.equals("+")) {
            return new Add();
        } else if (token.equals("-")) {
            return new Substract();
        } else if (token.equals("*")) {
            return new Multiply();
        } else if (token.equals("/")) {
            return new Divide();
        } else if (token.equals("log")) {
            return new Logarithm();
        } else if (token.equals("sqrt")) {
            return new SquareRoot();
        } else if (token.equals("^")) {
            return new Power();
        }

        return null;
    }

    @Override
    public boolean isOperator(final IToken token) {
        if (token.getSymbol().equals("+") || token.getSymbol().equals("-")
                || token.getSymbol().equals("*")
                || token.getSymbol().equals("/")
                || token.getSymbol().equals("log")
                || token.getSymbol().equals("sqrt")
                || token.getSymbol().equals("^")) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isUnaryOperator(final IOperator operator) {
        if (operator.getSymbol().equals("log")
                || operator.getSymbol().equals("sqrt")) {
            return true;
        }

        return false;
    }

    @Override
    public boolean isBinaryOperator(final IOperator operator) {
        if (operator.getSymbol().equals("+") || operator.getSymbol().equals("-")
                || operator.getSymbol().equals("*")
                || operator.getSymbol().equals("/")
                || operator.getSymbol().equals("^")) {
            return true;
        }

        return false;
    }

}
