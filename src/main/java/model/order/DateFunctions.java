package model.order;
import java.time.LocalDateTime;


public class DateFunctions {

    private final LocalDateTime orderDate = LocalDateTime.now();

    public boolean isValidDeadline(LocalDateTime orderDate, LocalDateTime deadline){
        System.out.println(deadline.compareTo(orderDate));
        return 1 >= deadline.compareTo(orderDate);
    }

    public boolean isValidDeadline(LocalDateTime deadline){
//        System.out.println("today: " + LocalDateTime.now().getDayOfMonth() + "-"+ LocalDateTime.now().getMonthValue() + "-" + LocalDateTime.now().getYear());
//        System.out.println("deadline: " + deadline.getDayOfMonth() + "-"+ deadline.getMonthValue() + "-" + deadline.getYear());
//        System.out.println("deadline.compareTo(orderDate): " + deadline.compareTo(orderDate));
//        System.out.println(1 <= deadline.compareTo(LocalDateTime.now()));
        return 1 <= deadline.compareTo(LocalDateTime.now());
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
