package homework.brackets;

import homework.interfaces.IToken;
import homework.interfaces.brackets.IBracket;
import homework.interfaces.brackets.IBracketsFactory;

public class BracketsFactory implements IBracketsFactory {

    private static BracketsFactory instance = new BracketsFactory();

    private BracketsFactory() {
    }

    public static BracketsFactory getInstance() {
        return instance;
    }

    @Override
    public IBracket createBracket(final String token) {
        if (token == null) {
            return null;
        }

        if (token.equals("(") || token.equals("[") || token.equals("{")) {
            return new OpenBracket();
        } else if (token.equals(")") || token.equals("]")
                || token.equals("}")) {
            return new ClosedBracket();
        }

        return null;
    }

    @Override
    public boolean isBracket(final IToken token) {
        return (token.getSymbol().equals("(") || token.getSymbol().equals(")")
                || token.getSymbol().equals("[")
                || token.getSymbol().equals("]")
                || token.getSymbol().equals("{")
                || token.getSymbol().equals("}"));

    }

    @Override
    public boolean isOpenedBracket(final IBracket bracket) {
        if (bracket.getSymbol().equals("(") || bracket.getSymbol().equals("[")
                || bracket.getSymbol().equals("{")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isClosedBracket(final IBracket bracket) {
        if (bracket.getSymbol().equals(")") || bracket.getSymbol().equals("]")
                || bracket.getSymbol().equals("}")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isBracketPair(final IBracket openBracket, final IBracket closeBracket) {
        if ((openBracket.getSymbol().equals("(")
                && closeBracket.getSymbol().equals(")"))
                || (openBracket.getSymbol().equals("[")
                        && closeBracket.getSymbol().equals("]"))
                || (openBracket.getSymbol().equals("{")
                        && closeBracket.getSymbol().equals("}"))) {
            return true;
        }

        return false;
    }

}
