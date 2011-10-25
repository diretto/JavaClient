package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.entities.AbstractSubEntity;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class is the implementation class of the {@link Position} interface.
 * 
 * @author Tobias Schlecht
 */
final class PositionImpl extends AbstractSubEntity<PositionID> implements Position
{
	private final VoteManager voteManager;

	private final double latitude;
	private final double longitude;
	private final int variance;
	private final boolean isInaccurate;
	private final DateTime creationTime;
	private final UserID creator;
	private final Votes votes;

	/**
	 * Constructs an object of the {@link Position} interface.
	 * 
	 * @param builder The {@code PositionBuilder} object
	 */
	PositionImpl(PositionBuilder builder)
	{
		super(builder.getPositionID());

		voteManager = builder.getVoteManager();

		latitude = builder.getLatitude();
		longitude = builder.getLongitude();
		variance = builder.getVariance();

		if(variance == 0)
		{
			isInaccurate = false;
		}
		else
		{
			isInaccurate = true;
		}

		creationTime = builder.getCreationTime();
		creator = builder.getCreator();
		votes = builder.getVotes();

		if(voteManager == null)
		{
			throw new NullPointerException();
		}
	}

	@Override
	public double getLatitude()
	{
		return latitude;
	}

	@Override
	public double getLongitude()
	{
		return longitude;
	}

	@Override
	public int getVariance()
	{
		return variance;
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
