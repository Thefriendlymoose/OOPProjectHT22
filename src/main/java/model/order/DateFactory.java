package model.order;
import java.time.LocalDateTime;


public class DateFactory {

    private final LocalDateTime orderDate = LocalDateTime.now();

    public boolean isValidDeadline(LocalDateTime orderDate, LocalDateTime deadline){
        return 1 >= deadline.compareTo(orderDate);
    }

    public boolean isValidDeadline(LocalDateTime deadline){
        LocalDateTime orderDate = LocalDateTime.now();
        return 1 >= deadline.compareTo(orderDate);
    }

    public LocalDateTime createDeadline(int day, int month, int year) {
        day -= LocalDateTime.now().getDayOfMonth();
        month -= LocalDateTime.now().getMonthValue();
        year -= LocalDateTime.now().getYear();

        return LocalDateTime.now().plusDays(day).plusMonths(month).plusYears(year);
    }

    public LocalDateTime createOrderDate() {
        return LocalDateTime.now();
    }

    public LocalDateTime getOrderDate(){
        return orderDate;
    }

}
