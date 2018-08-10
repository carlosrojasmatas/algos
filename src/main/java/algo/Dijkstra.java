package algo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;

public class Dijkstra {

    private Stack<String> opsStack;
    private Stack<Double> valStacks;
    private Set<String> operators = new HashSet<>(Arrays.asList("+", "-", "/", "*"));
    private static final String OPENING = "(";
    private static final String CLOSING = ")";
    private static final String NUMBER_PATTERN = "^[0-9]*$";

    public Dijkstra() {
        this.opsStack = new Stack<>();
        this.valStacks = new Stack<>();
    }

    public Double interpret(String expression) {
        String[] exps = expression.split(" ");

        for (String el : exps) {
            if (operators.contains(el)) {
                opsStack.push(el);
            }else if (Pattern.matches(NUMBER_PATTERN,el)){
                valStacks.push(Double.parseDouble(el));
            }else if (el.equals(")")){
                String oper = opsStack.pop();
                double r = valStacks.pop();
                double l = valStacks.pop();
                valStacks.push(eval(l,r,oper));
            }

        }
        return valStacks.pop();
    }

    private double eval(double l, double r, String op) {
        double result = 0;
        switch (op) {
            case "*":
                result = l * r;
                break;
            case "/":
                result = l / r;
                break;
            case "+":
                result = l + r;
                break;
            case "-":
                result = l - r;
                break;
            default:
                throw new IllegalArgumentException("operator not supported");
        }
        return result;
    }
}

class Tester {
    public static void main(String[] args) {
        Dijkstra d = new Dijkstra();
        System.out.println(d.interpret("( ( ( 2 + 2 ) * 3 ) / 12 )"));
    }
}
