package orderTests;

import javafx.scene.control.DatePicker;
import model.order.DateFactory;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestDate {

    @Test
    public void testCreateDateNow(){

//        DatePicker dp = new DatePicker();
        DateFactory dateFactory = new DateFactory();

        Calendar timeNow = dateFactory.createDate();
        Calendar anotherTimeNow = dateFactory.createDate();
        Calendar timeFuture = dateFactory.createDeadline(1,1,1);
        System.out.println(timeNow.get(Calendar.YEAR) +"-"+ timeNow.get(Calendar.MONTH) +"-"+ timeNow.get(Calendar.DATE));
        System.out.println(timeFuture.get(Calendar.YEAR) +"-"+ timeFuture.get(Calendar.MONTH) +"-"+ timeFuture.get(Calendar.DATE));


//        System.out.println(dp.getValue().toString());
//      incomplete, fix later
        assertTrue(timeNow.equals(anotherTimeNow));
    }


}
