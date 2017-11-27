package pl.sdaccademy;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CommisionalEmployee extends Employee {

    private BigDecimal salary;
    BigDecimal commisionRate;  // percent;
    private List<Invoice> invoices;

    public CommisionalEmployee(String name, String adress, String bankaccountNumber, BigDecimal salary, BigDecimal commisionRate, Invoice invoces) {
        super(name, adress, bankaccountNumber);
        this.salary = salary;
        this.commisionRate = commisionRate;
        this.invoices = new ArrayList();
    }

    public void addInvoice (Invoice invoice){
        invoices.add(invoice);
    }

    //TODO sprawdzić czy day to co drugi piatek miesiaca
    @Override
    public boolean isPaymentDay(LocalDate date) {
        return isEverySecondFriday(date) || isLastWorkingDayOfYear(date);


    }

    private boolean isLastWorkingDayOfYear(LocalDate date) {

        return date.getMonth() == Month.DECEMBER ? DateUtils.isLastWorkingDayofMonth(LocalDate.of(date.getYear(), 12, 31)) : false;

    }

    private boolean isEverySecondFriday(LocalDate day){
        LocalDate secondFriday = findSecondFridayOfTheYear(day);
        int secondFridayDayOfYear = secondFriday.getDayOfYear();
        int dayDayofYear = day.getDayOfYear();
        return (dayDayofYear-secondFridayDayOfYear)%14==0;
    }

    private LocalDate findSecondFridayOfTheYear(LocalDate day) {
        LocalDate date = LocalDate.of(day.getYear(),1,1);
        while(date.getDayOfWeek() !=DayOfWeek.FRIDAY){
        date = date.plusDays(1);
        }
        return date.plusDays(7);
    }

    @Override
    public BigDecimal calculatePayment(LocalDate day) {
        if(isPaymentDay(day)){
            LocalDate firstDayOfWorkingPeriod = findFirstDayOfWorkingPeriod(day);
            List<Invoice> invoices = findInvoicesWithinPeriod(firstDayOfWorkingPeriod, day);
            return calculatePayment(invoices);
        }else{
            return BigDecimal.ZERO;
        }
    }
//TODO obliczyc wyplate na podstawie salary i sumy invoice.value pomnozonej przez commisonRate
    private BigDecimal calculatePayment(List<Invoice> invoices) {
        BigDecimal paymentFromInvoices = BigDecimal.ZERO;
        for(Invoice invoice : invoices){
            paymentFromInvoices = paymentFromInvoices.add(invoice.getValue());
        }
        paymentFromInvoices = paymentFromInvoices.multiply(commisionRate);
        return paymentFromInvoices.add(salary);
    }

//TODO znalezc wszystkie invoice wystawione powiedzy firstday i lastday
    private List<Invoice> findInvoicesWithinPeriod(LocalDate firstDay, LocalDate lastDay) {
        return invoices.stream()
                .filter(invoice -> invoice.betweenDays(firstDay, lastDay))
                .collect(Collectors.toList());
    }

//TODO znalezc poniedzialek spprzed dwoch tygodni dla podanego dnia (piatek)
    @Override
    public LocalDate findFirstDayOfWorkingPeriod(LocalDate paymentDate) {
        if(isEverySecondFriday(paymentDate)){
            LocalDate firstDay = paymentDate.minusDays(11);
            // uwaga na zmiane roku;
            if(firstDay.getYear() < paymentDate.getYear()){
                firstDay = LocalDate.of(paymentDate.getYear(), 1,1);
            }

            return firstDay;
        } else  {
            return LocalDate.now();

        }

       
    }
}
