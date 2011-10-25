package org.diretto.api.client.base.data;

import java.util.ArrayList;

/**
 * This class represents a {@code BoundingBox}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public class BoundingBox
{
	private final double lowerLeftLatitude;
	private final double lowerLeftLongitude;
	private final double upperRightLatitude;
	private final double upperRightLongitude;

	private final double centralLatitude;
	private final double centralLongitude;

	/**
	 * Constructs a {@link BoundingBox}.
	 * 
	 * @param lowerLeftLatitude The lower left latitude in degrees
	 * @param lowerLeftLongitude The lower left longitude in degrees
	 * @param upperRightLatitude The upper right latitude in degrees
	 * @param upperRightLongitude The upper right longitude in degrees
	 */
	public BoundingBox(double lowerLeftLatitude, double lowerLeftLongitude, double upperRightLatitude, double upperRightLongitude)
	{
		this.lowerLeftLatitude = lowerLeftLatitude;
		this.lowerLeftLongitude = lowerLeftLongitude;
		this.upperRightLatitude = upperRightLatitude;
		this.upperRightLongitude = upperRightLongitude;

		centralLatitude = (lowerLeftLatitude + upperRightLatitude) / 2.0d;
		centralLongitude = (lowerLeftLongitude + upperRightLongitude) / 2.0d;
	}

	/**
	 * Returns the lower left latitude in degrees.
	 * 
	 * @return The lower left latitude in degrees
	 */
	public double getLowerLeftLatitude()
	{
		return lowerLeftLatitude;
	}

	/**
	 * Returns the lower left longitude in degrees.
	 * 
	 * @return The lower left longitude in degrees
	 */
	public double getLowerLeftLongitude()
	{
		return lowerLeftLongitude;
	}

	/**
	 * Returns the upper right latitude in degrees.
	 * 
	 * @return The upper right latitude in degrees
	 */
	public double getUpperRightLatitude()
	{
		return upperRightLatitude;
	}

	/**
	 * Returns the upper right longitude in degrees.
	 * 
	 * @return The upper right longitude in degrees
	 */
	public double getUpperRightLongitude()
	{
		return upperRightLongitude;
	}

	/**
	 * Returns the center of the lower latitude value and the upper latitude
	 * value in degrees.
	 * 
	 * @return The central latitude of this {@code BoundingBox}
	 */
	public double getCentralLatitude()
	{
		return centralLatitude;
	}

	/**
	 * Returns the center of the left longitude value and the right longitude
	 * value in degrees.
	 * 
	 * @return The central longitude of this {@code BoundingBox}
	 */
	public double getCentralLongitude()
	{
		return centralLongitude;
	}

	/**
	 * Determines whether the point, represented by the given coordinate values,
	 * is located within this {@link BoundingBox}.
	 * 
	 * @param latitude The latitude of a point in degrees
	 * @param longitude The longitude of a point in degrees
	 * @return {@code true} if the point is located within the
	 *         {@code BoundingBox}; otherwise {@code false}
	 */
	public boolean surrounds(double latitude, double longitude)
	{
		if(lowerLeftLatitude <= latitude && latitude <= upperRightLatitude && lowerLeftLongitude <= longitude && longitude <= upperRightLongitude)
		{
			return true;
		}

		return false;
	}

	/**
	 * Determines whether the given {@link TopographicPoint} is located within
	 * this {@link BoundingBox}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that the variance (if the given
	 * {@code TopographicPoint} has one) will not be taken into account.
	 * 
	 * @param topographicPoint A {@code TopographicPoint}
	 * @return {@code true} if the {@code TopographicPoint} is located within
	 *         the {@code BoundingBox}; otherwise {@code false}
	 */
	public boolean surrounds(TopographicPoint topographicPoint)
	{
		return surrounds(topographicPoint.getLatitude(), topographicPoint.getLongitude());
	}

	/**
	 * Determines whether the given {@link BoundingBox} is located within this
	 * {@code BoundingBox}.
	 * 
	 * @param boundingBox A {@code BoundingBox}
	 * @return {@code true} if the given {@code BoundingBox} is located within
	 *         this {@code BoundingBox}; otherwise {@code false}
	 */
	public boolean surrounds(BoundingBox boundingBox)
	{
		if(surrounds(boundingBox.lowerLeftLatitude, boundingBox.lowerLeftLongitude) && surrounds(boundingBox.upperRightLatitude, boundingBox.upperRightLongitude))
		{
			return true;
		}

		return false;
	}

	/**
	 * Returns an {@link ArrayList} with the coordinate values of the
	 * {@link BoundingBox} in the specified order. <br/><br/>
	 * 
	 * <i>Annotation:</i> The indices have to be in the range
	 * {@code 0 < index < 4}
	 * 
	 * @param lowerLeftLatitudeIndex The {@code ArrayList} index of the lower
	 *        left latitude
	 * @param lowerLeftLongitudeIndex The {@code ArrayList} index of the lower
	 *        left longitude
	 * @param upperRightLatitudeIndex The {@code ArrayList} index of the upper
	 *        right latitude
	 * @param upperRightLongitudeIndex The {@code ArrayList} index of the upper
	 *        right longitude
	 * @return An {@code ArrayList} with the coordinate values
	 */
	public ArrayList<Double> getArrayList(int lowerLeftLatitudeIndex, int lowerLeftLongitudeIndex, int upperRightLatitudeIndex, int upperRightLongitudeIndex)
	{
		if(lowerLeftLatitudeIndex < 0 || lowerLeftLongitudeIndex < 0 || upperRightLatitudeIndex < 0 || upperRightLongitudeIndex < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		else if(lowerLeftLatitudeIndex > 3 || lowerLeftLongitudeIndex > 3 || upperRightLatitudeIndex > 3 || upperRightLongitudeIndex > 3)
		{
			throw new IndexOutOfBoundsException();
		}

		double[] array = new double[4];

		array[lowerLeftLatitudeIndex] = lowerLeftLatitude;
		array[lowerLeftLongitudeIndex] = lowerLeftLongitude;
		array[upperRightLatitudeIndex] = upperRightLatitude;
		array[upperRightLongitudeIndex] = upperRightLongitude;

		ArrayList<Double> arrayList = new ArrayList<Double>(4);

		for(int i = 0; i < 4; i++)
		{
			arrayList.add(array[i]);
		}

		return arrayList;
	}

	@Override
	public String toString()
	{
		return lowerLeftLatitude + "/" + lowerLeftLongitude + ";" + upperRightLatitude + "/" + upperRightLongitude;
	}
}
