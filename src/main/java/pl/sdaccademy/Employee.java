package pl.sdaccademy;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public abstract class Employee {

    private String name;
    private String adress;
    private String bankAccountNumber;


    public Employee(String name, String adress, String bankaccountNumber) {

    }

    public abstract boolean isPaymentDay (LocalDate day);

    public abstract BigDecimal calculatePayment (LocalDate day);
}
