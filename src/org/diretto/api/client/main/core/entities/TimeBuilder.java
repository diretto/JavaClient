package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Time}s.
 * 
 * @author Tobias Schlecht
 */
public final class TimeBuilder implements Builder<Time>
{
	private final TimeID timeID;

	private final VoteManager voteManager;

	private TimeRange timeRange = null;
	private DateTime creationTime = null;
	private UserID creator = null;
	private Votes votes = null;

	/**
	 * Constructs a {@link TimeBuilder} object.
	 * 
	 * @param timeID The {@code TimeID}
	 * @param voteManager The {@code VoteManager}
	 */
	public TimeBuilder(TimeID timeID, VoteManager voteManager)
	{
		this.timeID = timeID;
		this.voteManager = voteManager;
	}

	/**
	 * Sets the {@link TimeRange}. <br/><br/>
	 * 
	 * <i>Annotation:</i> If the {@link Time} is not inaccurate, the given
	 * {@code TimeRange} has to consist of two identical values.
	 * 
	 * @param timeRange The {@code TimeRange}
	 * @return The {@code TimeBuilder} object
	 */
	public TimeBuilder timeRange(TimeRange timeRange)
	{
		this.timeRange = timeRange;
		return this;
	}

	/**
	 * Sets the creation {@link DateTime}.
	 * 
	 * @param creationTime The creation {@code DateTime}
	 * @return The {@code TimeBuilder} object
	 */
	public TimeBuilder creationTime(DateTime creationTime)
	{
		this.creationTime = creationTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the creator.
	 * 
	 * @param creator The {@code UserID} of the creator
	 * @return The {@code TimeBuilder} object
	 */
	public TimeBuilder creator(UserID creator)
	{
		this.creator = creator;
		return this;
	}

	/**
	 * Sets the {@link Votes} of all {@link User}s.
	 * 
	 * @param votes The {@code Votes} of all {@code User}s
	 * @return The {@code TimeBuilder} object
	 */
	public TimeBuilder votes(Votes votes)
	{
		this.votes = votes;
		return this;
	}

	/**
	 * Returns the {@link TimeID}.
	 * 
	 * @return The {@code TimeID}
	 */
	public TimeID getTimeID()
	{
		return timeID;
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
	 * Returns the {@link TimeRange}.
	 * 
	 * @return The {@code TimeRange}
	 */
	public TimeRange getTimeRange()
	{
		return timeRange;
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
	public Time build()
	{
		return new TimeImpl(this);
	}
}
