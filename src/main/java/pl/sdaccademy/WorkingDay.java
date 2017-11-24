package pl.sdaccademy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * Klasa przechowuje interfejs o liczbie godzin pprzepracowanych danego dnia
 */

@Data
public class WorkingDay {

    private LocalDate date;
    private int hours;

    public WorkingDay(LocalDate date, int hours) {
        this.date = date;
        this.hours = hours;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getHours() {
        return hours;
    }

    public boolean betweenDays(LocalDate firstDay, LocalDate lastDay) {
        return  firstDay.compareTo(date) <=0 && date.compareTo(lastDay) <=0;
    }
}
