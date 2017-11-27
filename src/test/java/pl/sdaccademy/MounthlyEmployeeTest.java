package pl.sdaccademy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MounthlyEmployeeTest {

    private static final BigDecimal SALARY = new BigDecimal(100);

    private MounthlyEmployee monthlyEmployee;

    @BeforeEach
    void init () {
        monthlyEmployee = new MounthlyEmployee("name", "adress", "bankaccount", SALARY);
    }

    @Test
    void isPaymentDay() {
        assertFalse(monthlyEmployee.isPaymentDay(LocalDate.of(2017,11,27)));
        assertTrue(monthlyEmployee.isPaymentDay(LocalDate.of(2017,11,30)));
    }

    @Test
    void calculatePayment() {
        assertTrue(monthlyEmployee.calculatePayment(LocalDate.of(2017,11,27)).compareTo(BigDecimal.ZERO)==0);
        assertTrue(monthlyEmployee.calculatePayment(LocalDate.of(2017,11,30)).compareTo(SALARY)==0);
    }

    @Test
    void findFirstDayOfWorkingPeriod() {
        assertEquals(monthlyEmployee.findFirstDayOfWorkingPeriod(LocalDate.of(2017,11,27)), LocalDate.of(2017,11,1));
    }

}