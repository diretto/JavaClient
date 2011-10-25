package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.entities.AbstractSubEntity;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class is the implementation class of the {@link Tag} interface.
 * 
 * @author Tobias Schlecht
 */
final class TagImpl extends AbstractSubEntity<TagID> implements Tag
{
	private final VoteManager voteManager;

	private final String value;
	private final DateTime creationTime;
	private final UserID creator;
	private final Votes votes;

	/**
	 * Constructs an object of the {@link Tag} interface.
	 * 
	 * @param builder The {@code TagBuilder} object
	 */
	TagImpl(TagBuilder builder)
	{
		super(builder.getTagID());

		voteManager = builder.getVoteManager();

		value = builder.getValue();
		creationTime = builder.getCreationTime();
		creator = builder.getCreator();
		votes = builder.getVotes();

		if(voteManager == null)
		{
			throw new NullPointerException();
		}
	}

	@Override
	public String getValue()
	{
		return value;
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
	
	@Override
	public String toString()
	{
		return value;
	}
}
