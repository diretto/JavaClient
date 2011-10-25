package org.diretto.api.client.base.data;

/**
 * This class represents a {@code TopographicPoint}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public class TopographicPoint
{
	private final double latitude;
	private final double longitude;
	private final int variance;

	/**
	 * Construct an accurate {@link TopographicPoint}.
	 * 
	 * @param latitude The latitude of the {@code TopographicPoint} in degrees
	 * @param longitude The longitude of the {@code TopographicPoint} in degrees
	 */
	public TopographicPoint(double latitude, double longitude)
	{
		this(latitude, longitude, 0);
	}

	/**
	 * Construct an inaccurate {@link TopographicPoint}.
	 * 
	 * @param latitude The latitude of the {@code TopographicPoint} in degrees
	 * @param longitude The longitude of the {@code TopographicPoint} in degrees
	 * @param variance The variance of the {@code TopographicPoint} in meters
	 */
	public TopographicPoint(double latitude, double longitude, int variance)
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.variance = variance;

		if(this.variance < 0)
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the latitude of the {@link TopographicPoint} in degrees.
	 * 
	 * @return The latitude of the {@code TopographicPoint} in degrees
	 */
	public double getLatitude()
	{
		return latitude;
	}

	/**
	 * Returns the longitude of the {@link TopographicPoint} in degrees.
	 * 
	 * @return The longitude of the {@code TopographicPoint} in degrees
	 */
	public double getLongitude()
	{
		return longitude;
	}

	/**
	 * Returns the variance of the {@link TopographicPoint} in meters.
	 * 
	 * @return The variance of the {@code TopographicPoint} in meters or
	 *         {@code 0} if the {@code TopographicPoint} is not inaccurate and
	 *         therefore no variance exists
	 */
	public int getVariance()
	{
		return variance;
	}

	/**
	 * Determines whether this {@link TopographicPoint} is inaccurate.
	 * 
	 * @return {@code true} if the {@code TopographicPoint} is inaccurate and
	 *         therefore the variance is not {@code 0}; otherwise {@code false}
	 */
	public boolean isInaccurate()
	{
		if(variance == 0)
		{
			return false;
		}

		return true;
	}
}
