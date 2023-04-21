package calculator;

import java.math.BigInteger;
import java.util.*;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Map<String, BigInteger> assignmentMap = new HashMap<>();
    static boolean invalidBrackets = false;

    public static void main(String[] args) {
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("/exit")) {
                System.out.println("Bye!");
                break;
            } else if (input.equals("/help")) {
                System.out.println("""
                        The math is MATHING:
                        This calculator can do several operations, these include:
                        +   Addition
                        -   Subtraction
                        *   Multiplication
                        /   Division
                        ^   Exponents
                        ()  Parentheses for enclosing relevant operands""");
            } else if (input.matches("/.*")) {
                System.out.println("Unknown command");
            } else if (input.matches(".*\\s*=\\s*.*")) {
                assignment(input);
            } else if (assignmentMap.containsKey(input)) {
                System.out.println(assignmentMap.get(input));
            } else if (input.length() > 0) {
                try {
                    if (input.matches("[A-Za-z]+") && !assignmentMap.containsKey(input)) {
                        System.out.println("Unknown variable");
                    } else {
                        List<String> expression = postFix(input);
                        if (!invalidBrackets) {
                            calculate(expression);
                        } else System.out.println("Invalid expression");
                    }
                } catch (Exception e) {
                    System.out.println("Invalid expression");
                }
            }
        }
    }

    public static void calculate(List<String> expression) {
        Deque<BigInteger> valueStack = new ArrayDeque<>();
        for (var element : expression) {
            if (element.matches("\\d+")) {
                BigInteger elementNum = new BigInteger(element);
                valueStack.push(elementNum);
            } else if (element.matches("[A-Za-z]+")) {
                if (assignmentMap.containsKey(element)) {
                    valueStack.push(assignmentMap.get(element));
                }
            } else if (element.matches("[*/^+-]")) {
                BigInteger a = valueStack.pop();
                BigInteger b = !valueStack.isEmpty() ? valueStack.pop() : BigInteger.ZERO;
                switch (element) {
                    case "*" -> valueStack.push(a.multiply(b));
                    case "/" -> valueStack.push(b.divide(a));
                    case "^" -> valueStack.push(b.pow(Integer.parseInt(String.valueOf(a))));
                    case "+" -> valueStack.push(a.add(b));
                    case "-" -> valueStack.push(b.subtract(a));
                }
            }
        }
        System.out.println(valueStack.pop());
    }

    public static List<String> postFix(String input) {
        Deque<String> stack = new ArrayDeque<>();
        List<String> result = new ArrayList<>();
        if (input.matches("(.*\\^{2,}.*)|(.*\\*{2,}.*)|(.*/{2,}.*)")) {
            return result;
        }

        String newString = input.replaceAll("[+-]$", "###")
                .replaceAll("\\++|(--)+", "+").replaceAll("\\+-|-\\s+", "-")
                .replaceAll("\\s+", "");
        String regex = String.format("(?<=%1$s)|(?=%1$s)", "[ \\-\\+\\*/\\(\\)\\^]");
        String[] inputArray = newString.split(regex);
        for (var element : inputArray) {
            invalidBrackets = false;
            if (element.matches("\\w+")) {
                result.add(element);
            } else if ((stack.isEmpty() || stack.peek().equals("("))) {
                stack.push(element);
            } else if (element.matches("\\^")) {
                if (stack.peek().matches("[*/+-]")) {
                    stack.push(element);
                } else if (stack.peek().matches("\\^")) {
                    while (!stack.isEmpty() && !stack.peek().matches("[(+-]")) {
                        result.add(stack.pop());
                    }
                    stack.push(element);
                }
            } else if (element.matches("[*/]")) {
                if (stack.peek().matches("[+-]")) {
                    stack.push(element);
                } else if (stack.peek().matches("[*/]")) {
                    while (!stack.isEmpty() && !stack.peek().matches("[(+-]")) {
                        result.add(stack.pop());
                    }
                    stack.push(element);
                }
            } else if (element.matches("[+-]")) {
                while (!stack.isEmpty() && !stack.peek().matches("[(]")) {
                    result.add(stack.pop());
                }
                stack.push(element);
            } else if (element.matches("[(]")) {
                stack.push(element);
            } else if (element.matches("[)]")) {
                if (stack.contains("(")) {
                    while (!stack.isEmpty()) {
                        if (stack.peek().matches("[(]")) {
                            stack.pop();
                            break;
                        } else {
                            result.add(stack.pop());
                        }
                    }
                } else {
                    invalidBrackets = true;
                    break;
                }
            }
        }
        if (!stack.contains("(")) {
            while (!stack.isEmpty()) result.add(stack.pop());
        } else {
            invalidBrackets = true;
        }
        return result;
    }

    public static void assignment(String input) {
        String modifiedString = input.replaceAll("\\s+", "");
        String[] inputArray = modifiedString.split("=");
        if (input.matches("^\\s*[A-Za-z]+\\s*=\\s*[+-]*\\d+\\s*$")) {
            BigInteger num = new BigInteger(inputArray[1]);
            assignmentMap.put(inputArray[0], num);
        } else if (input.matches("[A-Za-z]+\\d+\\s*=\\s*.+|[A-Za-z]+")) {
            System.out.println("Invalid identifier");
        } else if (input.matches("[A-Za-z]+\\s*=\\s*[A-Za-z]+.*|[A-Za-z]+\\s*=\\s*\\d+.*")) {
            if (input.matches("^[A-Za-z]+\\s*=\\s*[A-Za-z]+\\s*$")) {
                if (assignmentMap.containsKey(inputArray[1])) {
                    assignmentMap.put(inputArray[0], assignmentMap.get(inputArray[1]));
                } else {
                    System.out.println("Unknown variable");
                }
            }
            else System.out.println("Invalid assignment");
        } else {
            System.out.println("Invalid identifier");
        }
    }
}