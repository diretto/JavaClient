package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.entities.AbstractSubEntity;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class is the implementation class of the {@link Time} interface.
 * 
 * @author Tobias Schlecht
 */
final class TimeImpl extends AbstractSubEntity<TimeID> implements Time
{
	private final VoteManager voteManager;

	private final TimeRange timeRange;
	private final DateTime averageTime;
	private final boolean isInaccurate;
	private final DateTime creationTime;
	private final UserID creator;
	private final Votes votes;

	/**
	 * Constructs an object of the {@link Time} interface.
	 * 
	 * @param builder The {@code TimeBuilder} object
	 */
	TimeImpl(TimeBuilder builder)
	{
		super(builder.getTimeID());

		voteManager = builder.getVoteManager();

		timeRange = builder.getTimeRange();

		averageTime = timeRange.getAverageDateTime();
		isInaccurate = !timeRange.isAccuratePointInTime();

		creationTime = builder.getCreationTime();
		creator = builder.getCreator();
		votes = builder.getVotes();

		if(voteManager == null)
		{
			throw new NullPointerException();
		}
	}

	@Override
	public TimeRange getTimeRange()
	{
		return timeRange;
	}

	@Override
	public DateTime getAverageTime()
	{
		return averageTime;
	}

	@Override
	public boolean isInaccurate()
	{
		return isInaccurate;
	}

	@Override
	public DateTime getCreationTime()
	{
		return creationTime;
	}

	@Override
	public UserID getCreator()
	{
		return creator;
	}

	@Override
	public Votes getVotes()
	{
		return votes;
	}

	@Override
	public boolean setUserVote(UserSession userSession, VoteType voteType)
	{
		return voteManager.setUserVote(userSession, voteType, getID());
	}

	@Override
	public boolean removeUserVote(UserSession userSession)
	{
		return voteManager.removeUserVote(userSession, getID());
	}

	@Override
	public VoteType getUserVote(UserSession userSession)
	{
		return voteManager.getUserVote(userSession, getID());
	}
}
