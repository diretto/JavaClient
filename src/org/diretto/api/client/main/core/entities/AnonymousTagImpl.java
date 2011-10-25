package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.entities.AbstractSubEntity;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class is an implementation class of the {@link Tag} interface and
 * represents an <i>anonymous</i> {@code Tag}.
 * 
 * @author Tobias Schlecht
 */
final class AnonymousTagImpl extends AbstractSubEntity<TagID> implements Tag
{
	private final String value;
	private final DateTime creationTime;
	private final UserID creator;

	/**
	 * Constructs an <i>anonymous</i> object of the {@link Tag} interface.
	 * 
	 * @param builder The {@code TagBuilder} object
	 */
	AnonymousTagImpl(AnonymousTagBuilder builder)
	{
		super(builder.getTagID());

		value = builder.getValue();
		creationTime = builder.getCreationTime();
		creator = builder.getCreator();
	}

	@Override
	public String getValue()
	{
		return value;
	}

	@Override
	public boolean setUserVote(UserSession userSession, VoteType voteType)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeUserVote(UserSession userSession)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public VoteType getUserVote(UserSession userSession)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Votes getVotes()
	{
		throw new UnsupportedOperationException();
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
	public String toString()
	{
		return value;
	}
}
