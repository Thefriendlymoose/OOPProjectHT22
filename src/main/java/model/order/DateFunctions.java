package model.order;
import java.time.LocalDateTime;

/**
 * This class act as a help function when creating dates or verifying that they are valid.
 *
 * @author James Pålsson
 */
public class DateFunctions {

    /**
     * Checks if deadline date is set before today or earlier.
     *
     * @param deadline the date which compares to today's date.
     * @return true if deadline is tomorrow or later, else false.
     */
    public static boolean isValidDeadline(LocalDateTime deadline){
        return 0 <= deadline.toLocalDate().compareTo(LocalDateTime.now().toLocalDate());
    }

    /**
     * Returns a date which is set by providing day, month and year.
     * Positive values of day, month and year result in future date while negative values yield past.
     *
     * @param day is the added day of the date.
     * @param month is the added month of the date.
     * @param year is the added year of the date.
     * @return a date.
     */
    public LocalDateTime createDeadline(int day, int month, int year) {
        day -= LocalDateTime.now().getDayOfMonth();
        month -= LocalDateTime.now().getMonthValue();
        year -= LocalDateTime.now().getYear();

        return LocalDateTime.now().plusDays(day).plusMonths(month).plusYears(year);
    }

    /**
     * Return today's date.
     * @return today's date.
     */
    public LocalDateTime createOrderDate() {
        return LocalDateTime.now();
    }


}
