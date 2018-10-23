package top.app.util;

import java.time.format.DateTimeFormatter;

/**
 * Helper functions to handle dates.
 * 
 * @author chou
 *
 */
public class DateUtil {
	/** The pattern of a date. */
	private final static String PATTERN = "dd-MM-yyyy";
	
	/** The formatter of a date. */
	private final static DateTimeFormatter FORMATTER =
			DateTimeFormatter.ofPattern(PATTERN);
	
	
}
