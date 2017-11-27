package pl.sdaccademy;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Invoice {

    private LocalDate date;
    private BigDecimal value;

    public Invoice(LocalDate date, BigDecimal value) {
        this.date = date;
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public boolean betweenDays(LocalDate firstDay, LocalDate lastDay) {
        return  firstDay.compareTo(date) <=0 && date.compareTo(lastDay) <=0;
    }
}
