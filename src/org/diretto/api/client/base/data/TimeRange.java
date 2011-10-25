package org.diretto.api.client.base.data;

import java.util.Date;

import org.joda.time.DateTime;

/**
 * This class represents a {@code TimeRange}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public class TimeRange
{
	private final DateTime startDateTime;
	private final DateTime endDateTime;

	private final boolean isAccuratePointInTime;

	private DateTime averageDateTime = null;

	/**
	 * Constructs a {@link TimeRange}.
	 * 
	 * @param startDateTime The start {@code DateTime}
	 * @param endDateTime The end {@code DateTime}
	 */
	public TimeRange(DateTime startDateTime, DateTime endDateTime)
	{
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;

		if(startDateTime == null || endDateTime == null)
		{
			throw new NullPointerException();
		}
		else if(startDateTime.isAfter(endDateTime))
		{
			throw new IllegalArgumentException();
		}

		isAccuratePointInTime = startDateTime.equals(endDateTime);
	}

	/**
	 * Constructs a {@link TimeRange}.
	 * 
	 * @param startDate The start {@code Date}
	 * @param endDate The end {@code Date}
	 */
	public TimeRange(Date startDate, Date endDate)
	{
		this(new DateTime(startDate), new DateTime(endDate));
	}

	/**
	 * Returns the start {@link DateTime}.
	 * 
	 * @return The start {@code DateTime}
	 */
	public DateTime getStartDateTime()
	{
		return startDateTime;
	}

	/**
	 * Returns the end {@link DateTime}.
	 * 
	 * @return The end {@code DateTime}
	 */
	public DateTime getEndDateTime()
	{
		return endDateTime;
	}

	/**
	 * Returns the average {@link DateTime}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The returned {@code DateTime} represents the
	 * arithmetical mean of the start {@code DateTime} and the end
	 * {@code DateTime}.
	 * 
	 * @return The average {@code DateTime}
	 */
	public DateTime getAverageDateTime()
	{
		if(averageDateTime == null)
		{
			averageDateTime = new DateTime((startDateTime.getMillis() + endDateTime.getMillis()) / 2L);
		}

		return averageDateTime;
	}

	/**
	 * Returns the average {@link DateTime}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The returned {@code DateTime} represents the
	 * arithmetical mean of the start {@code DateTime} and the end
	 * {@code DateTime}.
	 * 
	 * @param startDateTime The start {@code DateTime}
	 * @param endDateTime The end {@code DateTime}
	 * @return The average {@code DateTime}
	 */
	public static DateTime getAverageDateTime(DateTime startDateTime, DateTime endDateTime)
	{
		return new DateTime((startDateTime.getMillis() + endDateTime.getMillis()) / 2L);
	}

	/**
	 * Determines if the start {@link DateTime} and the end {@link DateTime} of
	 * this {@link TimeRange} represent the same instant in time and if
	 * therefore this {@code TimeRange} is not really a range but rather a
	 * accurate point in time.
	 * 
	 * @return {@code true} if the start {@code DateTime} and the end
	 *         {@code DateTime} represent the same instant in time; otherwise
	 *         {@code false}
	 */
	public boolean isAccuratePointInTime()
	{
		return isAccuratePointInTime;
	}

	/**
	 * Determines whether the given {@link DateTime} is located within this
	 * {@link TimeRange}.
	 * 
	 * @param time A {@code DateTime}
	 * @return {@code true} if the {@code DateTime} is located within the
	 *         {@code TimeRange}; otherwise {@code false}
	 */
	public boolean surrounds(DateTime time)
	{
		if(time.isAfter(startDateTime) && time.isBefore(endDateTime))
		{
			return true;
		}

		return false;
	}

	/**
	 * Determines whether the given {@link TimeRange} is located within this
	 * {@code TimeRange}.
	 * 
	 * @param timeRange A {@code TimeRange}
	 * @return {@code true} if the given {@code TimeRange} is located within
	 *         this {@code TimeRange}; otherwise {@code false}
	 */
	public boolean surrounds(TimeRange timeRange)
	{
		if(surrounds(timeRange.getStartDateTime()) && surrounds(timeRange.getEndDateTime()))
		{
			return true;
		}

		return false;
	}
}
