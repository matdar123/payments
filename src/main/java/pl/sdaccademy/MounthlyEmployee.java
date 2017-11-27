package pl.sdaccademy;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class MounthlyEmployee extends Employee {

    private BigDecimal salary;


    public MounthlyEmployee(String name, String adress, String bankaccountNumber, BigDecimal salary) {
        super(name, adress, bankaccountNumber);
        this.salary = salary;
    }

    @Override
    public boolean isPaymentDay(LocalDate day) {
        return DateUtils.isLastWorkingDayofMonth(day);
    }

    @Override
    public BigDecimal calculatePayment(LocalDate day) {
        return isPaymentDay(day) ? salary : BigDecimal.ZERO;
    }

    @Override
    public LocalDate findFirstDayOfWorkingPeriod(LocalDate date) {
        return LocalDate.of(date.getYear(), date.getMonth(), 1);
    }
}

