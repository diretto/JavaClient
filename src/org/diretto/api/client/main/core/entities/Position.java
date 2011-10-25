package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.Creatable;
import org.diretto.api.client.base.characteristic.Votable;
import org.diretto.api.client.base.entities.SubEntity;

/**
 * This interface represents a {@code Position}. <br/><br/>
 * 
 * A {@code Position} is a point specified by geographical coordinates. The
 * latitude coordinate is always written first, followed by the longitude.
 * <br/><br/>
 * 
 * <i>Annotation:</i> The <u>WGS 84 standard</u> is used.
 * 
 * @author Tobias Schlecht
 */
public interface Position extends SubEntity<PositionID>, Votable, Creatable
{
	/**
	 * Returns the latitude in degrees.
	 * 
	 * @return The latitude in degrees
	 */
	public double getLatitude();

	/**
	 * Returns the longitude in degrees.
	 * 
	 * @return The longitude in degrees
	 */
	public double getLongitude();

	/**
	 * Determines whether this {@link Position} is inaccurate.
	 * 
	 * @return {@code true} if the {@code Position} is inaccurate and therefore
	 *         has a variance; otherwise {@code false}
	 */
	public boolean isInaccurate();

	/**
	 * Returns the variance in meters. <br/><br/>
	 * 
	 * <i>Annotation:</i> If the {@link Position} is not inaccurate and
	 * therefore has no variance, {@code 0} will be returned.
	 * 
	 * @return The variance in meters
	 */
	public int getVariance();
}
