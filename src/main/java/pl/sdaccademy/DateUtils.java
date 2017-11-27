package pl.sdaccademy;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {

    public static boolean isLastWorkingDayofMonth (LocalDate date){
        LocalDate lastWorkingDay = LocalDate.of(date.getYear(), date.getMonth(), date.lengthOfMonth());

        if (lastWorkingDay.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
            lastWorkingDay = lastWorkingDay.minusDays(1);
        } else if (lastWorkingDay.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            lastWorkingDay = lastWorkingDay.minusDays(2);
        }

        return date.equals(lastWorkingDay);
    }
}
