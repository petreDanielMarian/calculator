package homework.brackets;

import homework.interfaces.brackets.IBracket;

public class OpenBracket implements IBracket {

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
