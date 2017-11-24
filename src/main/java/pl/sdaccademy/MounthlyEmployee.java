package pl.sdaccademy;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MounthlyEmployee extends Employee {

    public MounthlyEmployee(String name, String adress, String bankaccountNumber) {
        super(name, adress, bankaccountNumber);
    }

    @Override
    public boolean isPaymentDay(LocalDate day) {
        
        return false;
    }

    @Override
    public BigDecimal calculatePayment(LocalDate day) {
        return null;
    }

    @Override
    public LocalDate findFirstDayOfWorkingPeriod(LocalDate peymentDate) {
        return null;
    }
}
