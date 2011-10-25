package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for <i>anonymous</i> {@link Tag}s.
 * 
 * @author Tobias Schlecht
 */
public final class AnonymousTagBuilder implements Builder<Tag>
{
	private final TagID tagID;

	private String value = null;
	private DateTime creationTime = null;
	private UserID creator = null;

	/**
	 * Constructs an {@link AnonymousTagBuilder} object.
	 * 
	 * @param tagID The {@code TagID}
	 */
	public AnonymousTagBuilder(TagID tagID)
	{
		this.tagID = tagID;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value The value
	 * @return The {@code AnonymousTagBuilder} object
	 */
	public AnonymousTagBuilder value(String value)
	{
		this.value = value;
		return this;
	}

	/**
	 * Sets the creation {@link DateTime}.
	 * 
	 * @param creationTime The creation {@code DateTime}
	 * @return The {@code AnonymousTagBuilder} object
	 */
	public AnonymousTagBuilder creationTime(DateTime creationTime)
	{
		this.creationTime = creationTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the creator.
	 * 
	 * @param creator The {@code UserID} of the creator
	 * @return The {@code AnonymousTagBuilder} object
	 */
	public AnonymousTagBuilder creator(UserID creator)
	{
		this.creator = creator;
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

	@Override
	public Tag build()
	{
		return new AnonymousTagImpl(this);
	}
}
