package setting;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class setting {
    public static final Scanner scanner = new Scanner(System.in);
    public static  final Calendar calendar = new GregorianCalendar();
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final Date date = new Date();
}
