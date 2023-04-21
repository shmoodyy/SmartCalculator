import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {

    static Scanner scanner = new Scanner(System.in);
    static Deque<Character> muhBrackets = new ArrayDeque<>();

    public static void main(String[] args) {
        System.out.println(balanceChecker() && muhBrackets.isEmpty());
    }

    public static boolean balanceChecker() {
        char[] input = scanner.nextLine().toCharArray();
        boolean isBalanced = true;
        for (char bracket : input) {
            if (bracket == '(' || bracket == '{' || bracket == '[') {
                muhBrackets.push(bracket);
            } else {
                char poppedBracket = !muhBrackets.isEmpty() ? muhBrackets.pop() : ' ';
                switch (bracket) {
                    case ')' -> isBalanced = poppedBracket == '(';
                    case '}' -> isBalanced = poppedBracket == '{';
                    case ']' -> isBalanced = poppedBracket == '[';
                    default -> { } // because the dumb code style system made me put it, the system needs fixing!
                }
            }
        }
        return isBalanced;
    }
}