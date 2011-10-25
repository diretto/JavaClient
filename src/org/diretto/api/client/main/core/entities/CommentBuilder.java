package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Comment}s.
 * 
 * @author Tobias Schlecht
 */
public final class CommentBuilder implements Builder<Comment>
{
	private final CommentID commentID;

	private final VoteManager voteManager;

	private String content = null;
	private DateTime creationTime = null;
	private UserID creator = null;
	private Votes votes = null;

	/**
	 * Constructs a {@link CommentBuilder} object.
	 * 
	 * @param commentID The {@code CommentID}
	 * @param voteManager The {@code VoteManager}
	 */
	public CommentBuilder(CommentID commentID, VoteManager voteManager)
	{
		this.commentID = commentID;
		this.voteManager = voteManager;
	}

	/**
	 * Sets the content.
	 * 
	 * @param content The content
	 * @return The {@code CommentBuilder} object
	 */
	public CommentBuilder content(String content)
	{
		this.content = content;
		return this;
	}

	/**
	 * Sets the creation {@link DateTime}.
	 * 
	 * @param creationTime The creation {@code DateTime}
	 * @return The {@code CommentBuilder} object
	 */
	public CommentBuilder creationTime(DateTime creationTime)
	{
		this.creationTime = creationTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the creator.
	 * 
	 * @param creator The {@code UserID} of the creator
	 * @return The {@code CommentBuilder} object
	 */
	public CommentBuilder creator(UserID creator)
	{
		this.creator = creator;
		return this;
	}

	/**
	 * Sets the {@link Votes} of all {@link User}s.
	 * 
	 * @param votes The {@code Votes} of all {@code User}s
	 * @return The {@code SubmissionBuilder} object
	 */
	public CommentBuilder votes(Votes votes)
	{
		this.votes = votes;
		return this;
	}

	/**
	 * Returns the {@link CommentID}.
	 * 
	 * @return The {@code CommentID}
	 */
	public CommentID getCommentID()
	{
		return commentID;
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
	 * Returns the content.
	 * 
	 * @return The content
	 */
	public String getContent()
	{
		return content;
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
	public Comment build()
	{
		return new CommentImpl(this);
	}
}
