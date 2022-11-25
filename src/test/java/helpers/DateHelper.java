package helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateHelper {

    public static String formatDate(String date, String formatFrom, String formatTo) {

        SimpleDateFormat formatOld = new SimpleDateFormat(formatFrom);

        try {
            Date tempDate = formatOld.parse(date);
            SimpleDateFormat formatNew = new SimpleDateFormat(formatTo, new Locale("NL"));
            return formatNew.format(tempDate);
        } catch (ParseException pe) {
            System.out.println("Error occurred when parsing date: " + pe.getMessage());
        }
        return "";
    }

    public static String getDate() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getCurrentTime() {
        Calendar time = Calendar.getInstance();

        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return formatter.format(time.getTime());
    }

    public static String getRandomDateInAgeRange(int ageYearLowerBound, int ageYearUpperBound) {

        GregorianCalendar gcLower = new GregorianCalendar();
        gcLower.add(Calendar.YEAR, -ageYearLowerBound);
        LocalDate startDate = LocalDate.ofInstant(gcLower.toInstant(), gcLower.getTimeZone().toZoneId());
        long dayHigh = startDate.toEpochDay();

        GregorianCalendar gcUpper = new GregorianCalendar();
        gcUpper.add(Calendar.YEAR, -ageYearUpperBound - 1);
        gcUpper.add(Calendar.DAY_OF_MONTH, 1);
        LocalDate endDate = LocalDate.ofInstant(gcUpper.toInstant(), gcUpper.getTimeZone().toZoneId());
        long dayLow = endDate.toEpochDay();

        long randomEpochDay = dayLow + (long) (Math.random() * (dayHigh - dayLow));
        return LocalDate.ofEpochDay(randomEpochDay).toString();
    }
}