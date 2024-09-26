package ua.lisovolik.util;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Alexandr Lisovolik on  26.09.2024
 */

public class Utils {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");
    private Utils(){

    }


    public static LocalDate parseDate(String date){
        return LocalDate.parse(date, formatter);
    }

    public static void logError(String error){
        System.out.println(error);
    }

    public static String formatNumber(Double number){
        DecimalFormat decimalFormat = new DecimalFormat("0.##");
        return decimalFormat.format(number);
    }

    public static String[] getTestData(){
        return new String[]{
                "C 1.1 8.15.1 P 15.10.2012 83",
                "C 1 10.1 P 01.12.2012 65",
                "C 1.1 5.5.1 P 01.11.2012 117",
                "D 1.1 8 P 01.01.2012-01.12.2012",
                "C 3 10.2 N 02.10.2012 100",
                "D 1 * P 8.10.2012-20.11.2012",
                "D 3 10 P 01.12.2012",
                /*"C 7.2 5.5.1 P 01.01.2013 1",
                "C 7 6.5.1 P 01.11.2013 1",
                "C 7.2 5.5.1 P 01.11.2013 3",
                "C 7.3 5.5.1 P 01.12.2014 1",
                "D * 5 P 01.01.2013-01.12.2013",*/
        };
    }
}
