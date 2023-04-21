import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<String> elementStack = new ArrayDeque<>();
        int numberOfElements = scanner.nextInt();
        for (int i = 0; i <= numberOfElements; i++) {
            String element = scanner.nextLine();
            elementStack.push(element);
        }

        for (int i = 0; i <= numberOfElements; i++) {
            System.out.println(elementStack.pop());
        }
    }
}