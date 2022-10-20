package orderTests;

import model.order.DateFunctions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDate {

//    deadline.compareTo(orderDate) så
//    deadline <  orderDate -> -1
//    deadline >= orderDate -> 1
    public boolean isValidDeadline(LocalDateTime orderDate, LocalDateTime deadline){
        return 1 >= deadline.compareTo(orderDate);
    }

//    måste fixa test
    @Test
    public void testValidDeadline(){

        int randomNum = ThreadLocalRandom.current().nextInt(-5, 5);
        System.out.println(randomNum);
        LocalDateTime orderDate = LocalDateTime.now();

        LocalDateTime deadline = LocalDateTime.now().plusDays(randomNum);
        assertTrue(isValidDeadline(orderDate,deadline));
    }


    @Test
    public void testValidDeadlineDateFactory(){

        DateFunctions df = new DateFunctions();

        int randomNum = ThreadLocalRandom.current().nextInt(-5, 5);
        System.out.println(randomNum);
        LocalDateTime orderDate = df.getOrderDate();

        LocalDateTime deadline = LocalDateTime.now().plusDays(randomNum);
        assertTrue(isValidDeadline(orderDate,deadline));
    }

    @Test
    public void testCreateOrderDate(){
        DateFunctions df = new DateFunctions();

        LocalDateTime orderDate1 = df.createOrderDate();
        LocalDateTime orderDate2 = LocalDateTime.now();

        assertTrue(orderDate1.toLocalDate().isEqual(orderDate2.toLocalDate()));

    }


}
