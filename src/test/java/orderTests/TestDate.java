package orderTests;

import model.order.DateFactory;
import org.junit.jupiter.api.Test;
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

        DateFactory df = new DateFactory();

        int randomNum = ThreadLocalRandom.current().nextInt(-5, 5);
        System.out.println(randomNum);
        LocalDateTime orderDate = df.getOrderDate();

        LocalDateTime deadline = LocalDateTime.now().plusDays(randomNum);
        assertTrue(isValidDeadline(orderDate,deadline));
    }

}
