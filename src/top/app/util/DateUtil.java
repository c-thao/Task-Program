package top.app.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Helper functions to handle dates.
 * 
 * @author chou
 *
 */
public class DateUtil {
	/** The pattern of a date as day of the week, the day of
	 * the month, the abbreviated name of the month, the year.
	 */
	private final static String PATTERN = "E dd MMM, yyyy";
	
	/** The formatter of a date. */
	private final static DateTimeFormatter FORMATTER =
			DateTimeFormatter.ofPattern(PATTERN);
	
	/**
	 * Formats the date into a String.
	 * 
	 * @param date the LocalDate to format
	 * @return the date as a String
	 */
	public static String formatDate(LocalDate date) {
		return FORMATTER.format(date);
	}
	
	/**
	 * Parses a String input date and converts it into
	 * a LocalDate.
	 * 
	 * @param date the String input to parse
	 * @return     a LocalDate of the String input date
	 */
	public static LocalDate parse(String date) {
		if (date != null && !date.isEmpty()) {
			return LocalDate.parse(date, FORMATTER);
		}
		return null;
	}
	
	/**
	 * Compares two LocalDate inputs, date1 and date2. The method
	 * returns four different integers from -2 to 0. An integer of
	 * 1 if date1 comes before date 2. An integer -1 if date1 comes
	 * after date 2. An integer of 0 if date1 and date2 are equal.
	 * An integer of -2 indicating an inability to compare the two
	 * LocalDate inputs.
	 * 
	 * @param date1 a LocalDate to compare for
	 * @param date2 a LocalDate to compare to
	 * @return      an int indicating the result of the comparison
	 */
	public static int compare(LocalDate date1, LocalDate date2) {
		if (date1.isBefore(date2)) return -1; // comes before
		if (date1.isAfter(date2)) return 1; // comes after
		if (date1.equals(date2)) return 0;
		return -2;
	}
	
}
