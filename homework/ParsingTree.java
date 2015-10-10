package Lesson5_03_10_2015.homework;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ParsingTree {

    private Tokenizer tkZer;

    private enum TokenType {
        PLUS("\\+"), MINUS("-"), MULT("\\*"), DIV("/"), OPEN("[\\(\\[\\{]"), CLOSE("[\\)\\]\\}]"), STRING("\\p{Alpha}+"), NUMBER("[0-9]*\\.[0-9]+|[0-9]+\\.*[0-9]*");

        String pattern;

        TokenType(String str) {
            this.pattern = str;
        }
    }

    public ParsingTree(String input) {
        setInput(input);
    }

    public Object calculate(Map<String, Double> values) {
        tkZer.nextToken();
        Expression expr = expr();
        if (expr == null) {
            return null;
        }
        return expr.calculate(values);
    }

    public void setInput(String input) {
        if (input == null) {
            return;
        }
        if (tkZer == null) {
            tkZer = new Tokenizer(input, createPatterns());
        } else
            this.tkZer.setInput(input);
    }

    private Pattern[] createPatterns() {
        TokenType[] types = TokenType.values();
        Pattern[] toReturn = new Pattern[types.length];
        int count = 0;
        for (TokenType t : types) {
            toReturn[count++] = Pattern.compile(t.pattern);
        }
        return toReturn;
    }

//    Grammar:
//    expr :: item {+|- item}*;
//    item :: mult {*|/ mult}*
//    mult :: (expr)|double|string

    private Expression expr() {
        Expression left = item();
        if (left == null) {
            return null;
        }
        Tokenizer.Token curToken = tkZer.currentToken();
        if(curToken == null){
            return left;
        }
        TokenType[] types = TokenType.values();
        while (curToken != null && (types[curToken.getType()] == TokenType.PLUS ||
                types[curToken.getType()] == TokenType.MINUS)) {
            tkZer.nextToken();
            Expression right = item();
            if (right == null) {
                return null;
            }
            left = (types[curToken.getType()] == TokenType.PLUS)
                    ? new Expression.BinaryOperator("+", left, right)
                    : new Expression.BinaryOperator("-", left, right);
            curToken = tkZer.currentToken();
        }
        return left;
    }

    private Expression item() {
        Expression left = mult();
        if (left == null) {
            return null;
        }
        Tokenizer.Token curToken = tkZer.currentToken();
        if (curToken == null){
            return left;
        }
        TokenType[] types = TokenType.values();
        while (curToken != null && (types[curToken.getType()] == TokenType.MULT ||
                types[curToken.getType()] == TokenType.DIV)) {
            tkZer.nextToken();
            Expression right = mult();
            if (right == null) {
                return null;
            }
            left = (types[curToken.getType()] == TokenType.MULT)
                    ? new Expression.BinaryOperator("*", left, right)
                    : new Expression.BinaryOperator("/", left, right);
            curToken = tkZer.currentToken();
        }
        return left;
    }

    private Expression mult() {
        Tokenizer.Token curToken = tkZer.currentToken();
        if (curToken == null) {
            return null;
        }
        TokenType[] types = TokenType.values();
        switch (types[curToken.getType()]) {
            case OPEN:
                tkZer.nextToken();
                Expression expr = expr();
                if (expr == null) {
                    return null;
                }
                curToken = tkZer.currentToken();
                if (curToken == null || types[curToken.getType()] != TokenType.CLOSE) {
                    return null;
                }
                tkZer.nextToken();
                return expr;
            case NUMBER:
                tkZer.nextToken();
                return new Expression.Operand(curToken.getValue());
            case STRING:
                tkZer.nextToken();
                return new Expression.Operand(curToken.getValue());
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        ParsingTree tree = new ParsingTree("(8 + 2*5)/(1+3*2-4)");
//        Tokenizer.Token curToken;
//        while ((curToken = tree.tkZer.nextToken()) != null){
//            System.out.println(curToken.getType() + " " + curToken.getValue());
//        }
        System.out.println(tree.calculate(new HashMap<>()));
//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            Matcher m = Pattern.compile("$").matcher(sc.nextLine());
//            if (m.find()) {
//                System.out.println(m.end());
//            }
//        }

    }
}