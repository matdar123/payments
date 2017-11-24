package pl.sdaccademy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WorkingDayTest {

    @Test
    void shouldReturnTrueWhenDayIsBeetween() {
        LocalDate startDate = LocalDate.of(2017,11,23);
        LocalDate beetweenDate = LocalDate.of(2017,11,24);
        LocalDate endDate = LocalDate.of(2017,11,25);
        WorkingDay workingDay = new WorkingDay(beetweenDate,10);
        assertTrue(workingDay.betweenDays(startDate, endDate));
    }

    @Test
    void shouldReturnFalseWhenDayIsOutOfRange (){
        LocalDate startDate = LocalDate.of(2017,11,23);
        LocalDate beetweenDate = LocalDate.of(2018,11,24);
        LocalDate endDate = LocalDate.of(2017,11,25);
        WorkingDay workingDay = new WorkingDay(beetweenDate,10);
        assertFalse(workingDay.betweenDays(startDate, endDate));
    }

}