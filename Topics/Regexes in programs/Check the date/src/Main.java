import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Date {

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String date = scn.nextLine();
        String dayRegex = "([0-2]\\d|3[0-1])";
        String monthRegex = "(0[1-9]|1[0-2])";
        String yearRegex = "(19\\d{2}|20\\d{2})";
        String dateDelimiter = "[./-]";
        Pattern dMY = Pattern.compile(dayRegex + dateDelimiter + monthRegex + dateDelimiter + yearRegex);
        Pattern yMD = Pattern.compile(yearRegex + dateDelimiter + monthRegex + dateDelimiter + dayRegex);
        Matcher dateFormat1 = dMY.matcher(date);
        Matcher dateFormat2 = yMD.matcher(date);

        System.out.println(dateFormat1.matches() || dateFormat2.matches() ? "Yes" : "No");
    }
}