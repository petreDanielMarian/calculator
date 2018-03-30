package homework.coreClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import homework.brackets.BracketsFactory;
import homework.interfaces.IServer;
import homework.interfaces.IToken;
import homework.interfaces.brackets.IBracket;
import homework.interfaces.operands.IOperand;
import homework.interfaces.operators.IOperator;
import homework.interfaces.operators.binaryOperators.IBinaryOperator;
import homework.interfaces.operators.unaryOperators.IUnaryOperator;
import homework.operands.ArabOperand;
import homework.operands.OperandsFactory;
import homework.operators.Operator;
import homework.operators.OperatorsFactory;

public final class Server implements IServer {

    private List<String> results = new ArrayList<String>();
    private List<String> operatorsList = new ArrayList<String>();

    private Stack<IToken> operatorsStackIToken = new Stack<>();

    private static Server instance = null;

    private Server() {

    }

    public static Server getInstance() {
        if (instance == null) {
            instance = new Server();
        }
        return instance;
    }

    @Override
    public boolean canPublish(final String[] tokens) {
        List<String> myOperators = new ArrayList<>();
        boolean ok = false;

        for (String token : tokens) {
            IToken tok = new Token();
            tok.setSymbol(token);

            if (OperatorsFactory.getInstance().isOperator(tok)) {
                myOperators.add(token);
            }
        }

        for (String operatorsElement : myOperators) {
            ok = false;
            for (String subsctribeElement : operatorsList) {

                if (operatorsElement.equals(subsctribeElement)) {
                    ok = true;
                    continue;
                }
            }
            if (!ok) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void publish(final String command) {
        String[] tokens;
        tokens = command.split(" ");

        if (canPublish(tokens)) {
            results.add(postfixCalculate(postfixExpression(tokens)));
        } else {
            results.add("IMPOSSIBRU");
        }

    }

    @Override
    public void subscribe(final String operator) {
        operatorsList.add(operator);

    }

    @Override
    public List<String> getResults() {
        return results;

    }

    public List<String> postfixExpression(final String[] tokens) {
        List<String> postfix = new ArrayList<String>();

        IBracket openBracket = null;
        @SuppressWarnings("unused")
        boolean ok = false;

        for (String s : tokens) {
            IToken token = new Token();
            token.setSymbol(s);

            if (OperandsFactory.getInstance()
                    .convertToArabNumber(token.getSymbol())
                    .getSymbolValue() != 0.0) {
                IOperand<Double> operand = new ArabOperand();
                operand = OperandsFactory.getInstance().createOperand(s);
                operand.setSymbol(s);
                postfix.add(operand.getSymbol());

            } else if (BracketsFactory.getInstance().isBracket(token)) {
                IBracket bracket = BracketsFactory.getInstance()
                        .createBracket(s);
                bracket.setSymbol(token.getSymbol());

                if (BracketsFactory.getInstance().isOpenedBracket(bracket)) {
                    operatorsStackIToken.push(token);

                    openBracket = BracketsFactory.getInstance()
                            .createBracket(bracket.getSymbol());
                    openBracket.setSymbol(bracket.getSymbol());

                } else if (BracketsFactory.getInstance()
                        .isClosedBracket(bracket)) {
                    IToken firstElem = new Token();

                    firstElem
                            .setSymbol(operatorsStackIToken.peek().getSymbol());

                    IBracket closeBracket = BracketsFactory.getInstance()
                            .createBracket(bracket.getSymbol());
                    closeBracket.setSymbol(bracket.getSymbol());

                    if (BracketsFactory.getInstance().isBracketPair(openBracket,
                            closeBracket)) {
                        ok = true;
                    }

                    while (OperatorsFactory.getInstance()
                            .isOperator(firstElem)) {
                        postfix.add(firstElem.getSymbol());
                        operatorsStackIToken.pop();

                        firstElem.setSymbol(
                                operatorsStackIToken.peek().getSymbol());
                    }

                    operatorsStackIToken.pop();
                }
            } else if (OperatorsFactory.getInstance().isOperator(token)) {
                IOperator operator = OperatorsFactory.getInstance()
                        .createOperator(token.getSymbol());
                operator.setSymbol(token.getSymbol());

                if (!operatorsStackIToken.isEmpty()) {
                    IToken firstElem = new Token();
                    firstElem
                            .setSymbol(operatorsStackIToken.peek().getSymbol());

                    IOperator tempOperator = null;

                    while (OperatorsFactory.getInstance()
                            .isOperator(firstElem)) {
                        tempOperator = OperatorsFactory.getInstance()
                                .createOperator(firstElem.getSymbol());
                        tempOperator.setSymbol(firstElem.getSymbol());

                        if (operator.getPriority() <= tempOperator
                                .getPriority()) {
                            postfix.add(
                                    operatorsStackIToken.peek().getSymbol());
                            operatorsStackIToken.pop();

                            if (operatorsStackIToken.empty()) {
                                break;
                            } else {
                                firstElem.setSymbol(operatorsStackIToken.peek()
                                        .getSymbol());
                            }

                        } else {
                            break;

                        }

                    }

                }
                operatorsStackIToken.push(token);
            }

        }

        while (!operatorsStackIToken.empty()) {
            postfix.add(operatorsStackIToken.peek().getSymbol());
            operatorsStackIToken.pop();

        }
        return postfix;
    }

    public String postfixCalculate(final List<String> postfix) {
        Stack<Double> numbersStack = new Stack<Double>();

        for (String s : postfix) {
            IToken token = new Token();
            token.setSymbol(s);

            if (OperandsFactory.getInstance()
                    .convertToArabNumber(token.getSymbol())
                    .getSymbolValue() != 0.0) {
                numbersStack.push(OperandsFactory.getInstance()
                        .convertToArabNumber(token.getSymbol())
                        .getSymbolValue());

            } else {
                IOperator currentOperator = new Operator();
                currentOperator.setSymbol(token.getSymbol());

                if (OperatorsFactory.getInstance()
                        .isBinaryOperator(currentOperator)) {
                    Double rightOperand = numbersStack.pop();
                    Double leftOperand = numbersStack.pop();

                    if (currentOperator.getSymbol().equals("/")
                            && rightOperand == 0) {
                        operatorsStackIToken.clear();
                        numbersStack.clear();
                        return "IMPOSSIBRU";

                    }

                    @SuppressWarnings("unchecked")
                    IBinaryOperator<Double> operator = (IBinaryOperator<Double>) 
                            OperatorsFactory
                            .getInstance()
                            .createOperator(currentOperator.getSymbol());

                    numbersStack
                            .push(operator.calculate(leftOperand, rightOperand)
                                    .getSymbolValue());

                } else if (OperatorsFactory.getInstance()
                        .isUnaryOperator(currentOperator)) {
                    Double operand = numbersStack.pop();

                    @SuppressWarnings("unchecked")
                    IUnaryOperator<Double> operator = (IUnaryOperator<Double>) 
                            OperatorsFactory
                            .getInstance()
                            .createOperator(currentOperator.getSymbol());

                    numbersStack
                            .push(operator.calculate(operand).getSymbolValue());
                }
            }
        }
        Double lastElem = numbersStack.pop();

        lastElem = Math.floor(lastElem);
        return OperandsFactory.getInstance().convertToRomanNumber(lastElem)
                .getSymbol();
    }
    
    public static void main(String[] args) {
        Server server = new Server();
        
        server.subscribe("-");
        server.subscribe("+");
        
        String testfmm = "MMMCMXCIX + MM - MMMM";
        
        server.publish(testfmm);
        
        System.out.println(server.getResults());
    }
}
