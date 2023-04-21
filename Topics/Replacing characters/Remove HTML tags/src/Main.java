import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String stringWithHtmlTags = scanner.nextLine();
        String htmlTagRegex = "<.*?>";
        Pattern pattern = Pattern.compile(htmlTagRegex);
        Matcher matcher = pattern.matcher(stringWithHtmlTags);
        System.out.println(matcher.replaceAll(""));
    }
}