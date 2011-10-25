package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Position}s.
 * 
 * @author Tobias Schlecht
 */
public final class PositionBuilder implements Builder<Position>
{
	private final PositionID positionID;

	private final VoteManager voteManager;

	private double latitude = 0.0d;
	private double longitude = 0.0d;
	private int variance = 0;
	private DateTime creationTime = null;
	private UserID creator = null;
	private Votes votes = null;

	/**
	 * Constructs a {@link PositionBuilder} object.
	 * 
	 * @param positionID The {@code PositionID}
	 * @param voteManager The {@code VoteManager}
	 */
	public PositionBuilder(PositionID positionID, VoteManager voteManager)
	{
		this.positionID = positionID;
		this.voteManager = voteManager;
	}

	/**
	 * Sets the latitude in degrees.
	 * 
	 * @param latitude The latitude in degrees
	 * @return The {@code PositionBuilder} object
	 */
	public PositionBuilder latitude(double latitude)
	{
		this.latitude = latitude;
		return this;
	}

	/**
	 * Sets the longitude in degrees.
	 * 
	 * @param longitude The longitude in degrees
	 * @return The {@code PositionBuilder} object
	 */
	public PositionBuilder longitude(double longitude)
	{
		this.longitude = longitude;
		return this;
	}

	/**
	 * Sets the variance in meters. <br/><br/>
	 * 
	 * <i>Annotation:</i> If the {@link Position} is not inaccurate and
	 * therefore has no variance, {@code 0} has to be set.
	 * 
	 * @param variance The variance in meters
	 * @return The {@code PositionBuilder} object
	 */
	public PositionBuilder variance(int variance)
	{
		this.variance = variance;
		return this;
	}

	/**
	 * Sets the creation {@link DateTime}.
	 * 
	 * @param creationTime The creation {@code DateTime}
	 * @return The {@code PositionBuilder} object
	 */
	public PositionBuilder creationTime(DateTime creationTime)
	{
		this.creationTime = creationTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the creator.
	 * 
	 * @param creator The {@code UserID} of the creator
	 * @return The {@code PositionBuilder} object
	 */
	public PositionBuilder creator(UserID creator)
	{
		this.creator = creator;
		return this;
	}

	/**
	 * Sets the {@link Votes} of all {@link User}s.
	 * 
	 * @param votes The {@code Votes} of all {@code User}s
	 * @return The {@code PositionBuilder} object
	 */
	public PositionBuilder votes(Votes votes)
	{
		this.votes = votes;
		return this;
	}

	/**
	 * Returns the {@link PositionID}.
	 * 
	 * @return The {@code PositionID}
	 */
	public PositionID getPositionID()
	{
		return positionID;
	}

	/**
	 * Returns the {@link VoteManager}.
	 * 
	 * @return The {@code VoteManager}
	 */
	public VoteManager getVoteManager()
	{
		return voteManager;
	}

	/**
	 * Returns the latitude in degrees.
	 * 
	 * @return The latitude in degrees
	 */
	public double getLatitude()
	{
		return latitude;
	}

	/**
	 * Returns the longitude in degrees.
	 * 
	 * @return The longitude in degrees
	 */
	public double getLongitude()
	{
		return longitude;
	}

	/**
	 * Returns the variance in meters.
	 * 
	 * @return The variance in meters
	 */
	public int getVariance()
	{
		return variance;
	}

	/**
	 * Returns the creation {@link DateTime}.
	 * 
	 * @return The creation {@code DateTime}
	 */
	public DateTime getCreationTime()
	{
		return creationTime;
	}

	/**
	 * Returns the {@link UserID} of the creator.
	 * 
	 * @return The {@code UserID} of the creator
	 */
	public UserID getCreator()
	{
		return creator;
	}

	/**
	 * Returns the {@link Votes} of all {@link User}s.
	 * 
	 * @return The {@code Votes} of all {@code User}s
	 */
	public Votes getVotes()
	{
		return votes;
	}

	@Override
	public Position build()
	{
		return new PositionImpl(this);
	}
}
