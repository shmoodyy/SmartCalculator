import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String ipAddress = scanner.nextLine();
        String ipNumber = "(\\d{1,2}|[0|1]\\d{2}|2[0-4]\\d|25[0-5])";
        Pattern p = Pattern.compile(ipNumber + "\\." + ipNumber + "\\." + ipNumber + "\\." + ipNumber);
        Matcher m = p.matcher(ipAddress);

        System.out.println(m.matches() ? "YES" : "NO");
    }
}
