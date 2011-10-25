package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.Creatable;
import org.diretto.api.client.base.characteristic.Votable;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.entities.SubEntity;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * This interface represents a {@code Time}. <br/><br/>
 * 
 * <i>Annotation:</i> The <u>Joda-Time</u> library is used.
 * 
 * @author Tobias Schlecht
 * 
 * @see org.joda.time.DateTime
 */
public interface Time extends SubEntity<TimeID>, Votable, Creatable
{
	public static final String ISO_UTC_DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String STANDARD_DATE_TIME_PATTERN = "dd.MM.yyyy' - 'hh:mm:ss' 'aa";
	public static final String BIG_DISTANCE_DATE_TIME_PATTERN = "dd.MM.yyyy'  -  'hh:mm:ss' 'aa";
	public static final String BIG_DISTANCE_HTML_DATE_TIME_PATTERN = "dd.MM.yyyy'&nbsp;&nbsp;-&nbsp;&nbsp;'hh:mm:ss'&nbsp;'aa";

	public static final DateTimeFormatter ISO_UTC_DATE_TIME_FORMATTER = DateTimeFormat.forPattern(ISO_UTC_DATE_TIME_PATTERN);
	public static final DateTimeFormatter STANDARD_DATE_TIME_FORMATTER = DateTimeFormat.forPattern(STANDARD_DATE_TIME_PATTERN);
	public static final DateTimeFormatter BIG_DISTANCE_DATE_TIME_FORMATTER = DateTimeFormat.forPattern(BIG_DISTANCE_DATE_TIME_PATTERN);
	public static final DateTimeFormatter BIG_DISTANCE_HTML_DATE_TIME_FORMATTER = DateTimeFormat.forPattern(BIG_DISTANCE_HTML_DATE_TIME_PATTERN);

	/**
	 * Returns the average {@link DateTime}. <br/><br/>
	 * 
	 * <i>Annotation:</i> If the {@link Time} is inaccurate and therefore the
	 * {@link TimeRange} consists of two different values, the returned
	 * {@code DateTime} represents the arithmetical mean of the two values.
	 * 
	 * @return The average {@code DateTime}
	 */
	public DateTime getAverageTime();

	/**
	 * Determines if the {@link Time} is inaccurate.
	 * 
	 * @return {@code true} if the {@code Time} is inaccurate and therefore the
	 *         {@code TimeRange} consists of two different values; otherwise
	 *         {@code false}
	 */
	public boolean isInaccurate();

	/**
	 * Returns the {@link TimeRange}. <br/><br/>
	 * 
	 * <i>Annotation:</i> If the {@link Time} is not inaccurate, the returned
	 * {@code TimeRange} will consist of two identical values.
	 * 
	 * @return The {@code TimeRange}
	 */
	public TimeRange getTimeRange();
}
