package ordertests;

import model.order.DateFunctions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Random;
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
    public void testValidDeadlineSameDay(){

        LocalDateTime deadline = LocalDateTime.now().plusDays(0);
        assertTrue(DateFunctions.isValidDeadline(deadline));
    }


    @Test
    public void testValidDeadlineFutureDay(){

        LocalDateTime deadline = LocalDateTime.now().plusDays(5);
        assertTrue(DateFunctions.isValidDeadline(deadline));
    }

    @Test
    public void testCreateOrderDate(){
        DateFunctions df = new DateFunctions();

        LocalDateTime orderDate1 = df.createOrderDate();
        LocalDateTime orderDate2 = LocalDateTime.now();

        assertTrue(orderDate1.toLocalDate().isEqual(orderDate2.toLocalDate()));

    }


}
