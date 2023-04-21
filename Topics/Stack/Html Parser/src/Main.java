import java.util.*;

class Main {

    static Scanner scanner = new Scanner(System.in);
    static Deque<String> openTags = new ArrayDeque<>();

    public static void main(String[] args) {
        String line = scanner.next();
        String[] lineArray = line.replaceAll("><", "> <").split(" ");
        for (String element : lineArray) {
            if (element.matches("<\\w+>")) {
                openTags.push(element);
            } else if (element.matches("</\\w+>")) {
                String head = openTags.pop();
                System.out.println(line.substring(line.indexOf(head) + head.length(), line.indexOf(element)));
            } else {
                System.out.println(element.replaceAll("<\\w+>|</\\w+>", ""));
            }
        }
    }
}