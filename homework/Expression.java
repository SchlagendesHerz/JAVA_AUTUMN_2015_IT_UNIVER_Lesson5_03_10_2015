package Lesson5_03_10_2015.homework;

import java.util.Map;

public abstract class Expression {

    public abstract Object calculate(Map<String, Double> values);

    public static class BinaryOperator extends Expression {

        private Expression leftOperand;
        private Expression rightOperand;
        private String value;

        public BinaryOperator(String value, Expression leftOperand, Expression rightOperand) {
            this.value = value;
            this.leftOperand = leftOperand;
            this.rightOperand = rightOperand;
        }

        @Override
        public Object calculate(Map<String, Double> values) {
            Object leftOper = leftOperand.calculate(values);
            Object rightOper = rightOperand.calculate(values);
            Class resultClass = (leftOper.getClass() == String.class || rightOper.getClass() == String.class)
                    ? String.class : Double.class;
            Object toReturn = null;
            switch (value) {
                case "+":
                    toReturn = (resultClass == String.class)
                            ? String.valueOf(leftOper) + String.valueOf(rightOper) + "+"
                            : (Double) leftOper + (Double) rightOper;
                    break;
                case "-":
                    toReturn = (resultClass == String.class)
                            ? String.valueOf(leftOper) + String.valueOf(rightOper) + "-"
                            : (Double) leftOper - (Double) rightOper;
                    break;
                case "*":
                    toReturn = (resultClass == String.class)
                            ? String.valueOf(leftOper) + String.valueOf(rightOper) + "*"
                            : (Double) leftOper * (Double) rightOper;
                    break;
                case "/":
                    toReturn = (resultClass == String.class)
                            ? String.valueOf(leftOper) + String.valueOf(rightOper) + "/"
                            : (Double) leftOper / (Double) rightOper;
                    break;

            }
            return toReturn;
        }
    }

    public static class Operand extends Expression {

        String value;

        public Operand(String value) {
            this.value = value;
        }

        @Override
        public Object calculate(Map<String, Double> values) {
            return (values == null || values.get(value) == null) ? value : values.get(value);
        }
    }
}