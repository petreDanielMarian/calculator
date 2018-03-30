package homework.coreClasses;

import homework.interfaces.IToken;

public class Token implements IToken {

    private String symbol;

    @Override
    public final String getSymbol() {
        return symbol;
    }

    @Override
    public final void setSymbol(final String symbolP) {
        this.symbol = symbolP;

    }

}
