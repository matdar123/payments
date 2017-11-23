package pl.sdaccademy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * Klasa przechowuje interfejs o liczbie godzin pprzepracowanych danego dnia
 */

@Data
@AllArgsConstructor
public class WorkingDay {

    private LocalDate date;
    private int hours;
}
