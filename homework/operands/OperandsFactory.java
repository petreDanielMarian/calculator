package homework.operands;

import homework.interfaces.operands.IOperand;
import homework.interfaces.operands.IOperandsFactory;

public class OperandsFactory implements IOperandsFactory<Double> {

  private static OperandsFactory instance = new OperandsFactory();
  private final int mValue = 1000;
  private final int cmValue = 900;
  private final int dValue = 500;
  private final int cdValue = 400;
  private final int cValue = 100;
  private final int xcValue = 90;
  private final int lValue = 50;
  private final int xlValue = 40;
  private final int xValue = 10;
  private final int ixValue = 9;
  private final int vValue = 5;
  private final int ivValue = 4;
  private final int iValue = 1;

  private OperandsFactory() {
  }

  public static OperandsFactory getInstance() {
    return instance;
  }

  @Override
  public IOperand<Double> createOperand(final String token) {  //
      // implementare factory pattern pentru crearea de elemente
    if (token == null) {                                     //  de tip
        // operand
      return null;
    }

    if (Character.isDigit(token.charAt(0))
                || token.length() > 1
                           && Character.isDigit(token.charAt(1))) {
      return new ArabOperand();
    } else if (Character.isAlphabetic(token.charAt(0))
                       || Character.isAlphabetic(token.charAt(1))) {
      return new RomanOperand();
    }

    return null;
  }

  @Override
  public IOperand<Double> convertToRomanNumber(Double arabNumber) {
    RomanOperand romanNumber = new RomanOperand();

    romanNumber.setSymbol("");

    boolean negative = false;

    if (arabNumber < 0) {
      negative = true;
      arabNumber = 0 - arabNumber;

    }
    while (arabNumber >= mValue) {
      arabNumber = arabNumber - mValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "M");
    }
    while (arabNumber >= cmValue) {
      arabNumber = arabNumber - cmValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "CM");
    }
    while (arabNumber >= dValue) {
      arabNumber = arabNumber - dValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "D");
    }
    while (arabNumber >= cdValue) {
      arabNumber = arabNumber - cdValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "CD");
    }
    while (arabNumber >= cValue) {
      arabNumber = arabNumber - cValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "C");
    }
    while (arabNumber >= xcValue) {
      arabNumber = arabNumber - xcValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "XC");
    }
    while (arabNumber >= lValue) {
      arabNumber = arabNumber - lValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "L");
    }
    while (arabNumber >= xlValue) {
      arabNumber = arabNumber - xlValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "XL");
    }
    while (arabNumber >= xValue) {
      arabNumber = arabNumber - xValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "X");
    }
    while (arabNumber >= ixValue) {
      arabNumber = arabNumber - ixValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "IX");
    }
    while (arabNumber >= vValue) {
      arabNumber = arabNumber - vValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "V");
    }
    while (arabNumber >= ivValue) {
      arabNumber = arabNumber - ivValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "IV");
    }
    while (arabNumber >= iValue) {
      arabNumber = arabNumber - iValue;
      romanNumber.setSymbol(romanNumber.getSymbol() + "I");
    }
    if (negative) {
      romanNumber.setSymbol("- " + romanNumber.getSymbol());
    }

    return romanNumber;
  }

  public double valueRoman(final char letter) {
    switch (letter) {
      case 'M':
        return mValue;
      case 'D':
        return dValue;
      case 'C':
        return cValue;
      case 'L':
        return lValue;
      case 'X':
        return xValue;
      case 'V':
        return vValue;
      case 'I':
        return iValue;
      default:
        return 0;
    }
  }

  @Override
  public IOperand<Double> convertToArabNumber(final String romanNumber) {
    ArabOperand arabNumber = new ArabOperand();

    arabNumber.setSymbolValue(0.0);

    if (romanNumber.charAt(0) == '-') {
      arabNumber.setSymbolValue(
              valueRoman(romanNumber.charAt(romanNumber.length() - 1)));

      for (int i = romanNumber.length() - 2; i > 0; i--) {
        if (valueRoman(romanNumber.charAt(i)) < valueRoman(
                romanNumber.charAt(i + 1))) {
          arabNumber.setSymbolValue(arabNumber.getSymbolValue()
                                            - valueRoman(romanNumber.charAt(i)));

        } else {
          arabNumber.setSymbolValue(arabNumber.getSymbolValue()
                                            + valueRoman(romanNumber.charAt(i)));

        }
      }
      arabNumber.setSymbolValue(0 - arabNumber.getSymbolValue());

    } else {
      arabNumber.setSymbolValue(
              valueRoman(romanNumber.charAt(romanNumber.length() - 1)));

      for (int i = romanNumber.length() - 2; i > -1; i--) {
        if (valueRoman(romanNumber.charAt(i)) < valueRoman(
                romanNumber.charAt(i + 1))) {
          arabNumber.setSymbolValue(arabNumber.getSymbolValue()
                                            - valueRoman(romanNumber.charAt(i)));

        } else {
          arabNumber.setSymbolValue(arabNumber.getSymbolValue()
                                            + valueRoman(romanNumber.charAt(i)));

        }
      }
    }
    return arabNumber;
  }
}
