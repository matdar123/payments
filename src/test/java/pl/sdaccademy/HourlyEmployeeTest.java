package pl.sdaccademy;

import org.junit.jupiter.api.Test;
import sun.java2d.opengl.WGLSurfaceData;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class HourlyEmployeeTest {
    @Test
    void shouldReturnTrueWhenDayIsFriday() {
        HourlyEmployee hourlyEmployee = new HourlyEmployee("name", "adress", "number", BigDecimal.ONE);
        LocalDate day = LocalDate.of(2017,11,24);
        boolean result = hourlyEmployee.isPaymentDay(day);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenDayIsnotFriday() {
        HourlyEmployee hourlyEmployee = new HourlyEmployee("name", "adress", "number", BigDecimal.ONE);
        LocalDate day = LocalDate.of(2017,11,23);
        boolean result = hourlyEmployee.isPaymentDay(day);

        assertFalse(result);
    }

    @Test
    void shouldReturnZeroforNonPaymentDay () {
        HourlyEmployee hourlyEmployee = new HourlyEmployee("name", "adress", "number", BigDecimal.ONE);
        BigDecimal payment = hourlyEmployee.calculatePayment(LocalDate.of(2017,11,24));
        assertEquals(payment, BigDecimal.ZERO);
    }

    @Test
    void calculatePaymentWithoutOvertimeRate() {
        HourlyEmployee hourlyEmployee = new HourlyEmployee("name", "adress", "number", BigDecimal.ONE);
        WorkingDay firstWorkingDay = new WorkingDay(LocalDate.of(2017,11,24),8);
        hourlyEmployee.addWorkingDay(firstWorkingDay);
        BigDecimal payment = hourlyEmployee.calculatePayment(LocalDate.of(2017,11,24));
        assertEquals(payment, BigDecimal.ONE.multiply(new BigDecimal(8)));
    }

    @Test
    void shouldReturnPaymentForTwoWorkingDays () {
        HourlyEmployee hourlyEmployee = new HourlyEmployee("name", "adress", "number", BigDecimal.TEN);
        WorkingDay workingDay1 = new WorkingDay(LocalDate.of(2017,11,23),8);
        WorkingDay workingDay2 = new WorkingDay(LocalDate.of(2017,11,24),10);
        WorkingDay workingDay3 = new WorkingDay(LocalDate.of(2017,11,25),10);
        WorkingDay workingDay4 = new WorkingDay(LocalDate.of(2017,11,19),8);
        hourlyEmployee.addWorkingDay(workingDay1);
        hourlyEmployee.addWorkingDay(workingDay2);
        hourlyEmployee.addWorkingDay(workingDay3);
        hourlyEmployee.addWorkingDay(workingDay4);
        BigDecimal payment = hourlyEmployee.calculatePayment(LocalDate.of(2017,11,24));
        BigDecimal expectedResult = BigDecimal.TEN.multiply(new BigDecimal(19));
        assertTrue(payment.compareTo(expectedResult)==0);
    }

    @Test
    void findFirstDayOfWorkingPeriod() {
        HourlyEmployee hourlyEmployee = new HourlyEmployee("name", "adress", "number", BigDecimal.ONE);
        LocalDate firstDay = hourlyEmployee.findFirstDayOfWorkingPeriod(LocalDate.of(2017,11,24));
        assertTrue(firstDay.equals(LocalDate.of(2017,11,20)));
    }

}