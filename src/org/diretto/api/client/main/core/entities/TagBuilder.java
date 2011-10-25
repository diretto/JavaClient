package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Tag}s.
 * 
 * @author Tobias Schlecht
 */
public final class TagBuilder implements Builder<Tag>
{
	private final TagID tagID;

	private final VoteManager voteManager;

	private String value = null;
	private DateTime creationTime = null;
	private UserID creator = null;
	private Votes votes = null;

	/**
	 * Constructs a {@link TagBuilder} object.
	 * 
	 * @param tagID The {@code TagID}
	 * @param voteManager The {@code VoteManager}
	 */
	public TagBuilder(TagID tagID, VoteManager voteManager)
	{
		this.tagID = tagID;
		this.voteManager = voteManager;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value The value
	 * @return The {@code TagBuilder} object
	 */
	public TagBuilder value(String value)
	{
		this.value = value;
		return this;
	}

	/**
	 * Sets the creation {@link DateTime}.
	 * 
	 * @param creationTime The creation {@code DateTime}
	 * @return The {@code TagBuilder} object
	 */
	public TagBuilder creationTime(DateTime creationTime)
	{
		this.creationTime = creationTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the creator.
	 * 
	 * @param creator The {@code UserID} of the creator
	 * @return The {@code TagBuilder} object
	 */
	public TagBuilder creator(UserID creator)
	{
		this.creator = creator;
		return this;
	}

	/**
	 * Sets the {@link Votes} of all {@link User}s.
	 * 
	 * @param votes The {@code Votes} of all {@code User}s
	 * @return The {@code TagBuilder} object
	 */
	public TagBuilder votes(Votes votes)
	{
		this.votes = votes;
		return this;
	}

	/**
	 * Returns the {@link TagID}.
	 * 
	 * @return The {@code TagID}
	 */
	public TagID getTagID()
	{
		return tagID;
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
	 * Returns the value.
	 * 
	 * @return The value
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * Returns the creation {@link DateTime}
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
	public Tag build()
	{
		return new TagImpl(this);
	}
}
