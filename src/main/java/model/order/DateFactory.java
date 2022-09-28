package model.order;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateFactory {

    public GregorianCalendar createDate(){
        return new GregorianCalendar();
    }

    public GregorianCalendar createDeadline(int y, int m, int d){
        GregorianCalendar dateDeadline = new GregorianCalendar();

        dateDeadline.set(y,m,d);

        return(dateDeadline);
    }


}
