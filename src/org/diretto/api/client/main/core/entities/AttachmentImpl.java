package org.diretto.api.client.main.core.entities;

import java.net.URL;
import java.util.List;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Contributor;
import org.diretto.api.client.base.data.MediaType;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.entities.AbstractSubEntity;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class is the implementation class of the {@link Attachment} interface.
 * 
 * @author Tobias Schlecht
 */
final class AttachmentImpl extends AbstractSubEntity<AttachmentID> implements Attachment
{
	private final VoteManager voteManager;

	private final boolean isExternal;

	private final String title;
	private final String description;
	private final DateTime publishingTime;
	private final UserID publisher;
	private final MediaType mediaType;
	private final long fileSize;
	private final URL fileURL;
	private final Votes votes;
	private final String license;
	private final List<Contributor> contributors;
	private final List<UserID> creators;

	/**
	 * Constructs an object of the {@link Attachment} interface.
	 * 
	 * @param builder The {@code AttachmentBuilder} object
	 */
	AttachmentImpl(AttachmentBuilder builder)
	{
		super(builder.getAttachmentID());

		voteManager = builder.getVoteManager();

		isExternal = builder.isExternal();

		title = builder.getTitle();
		description = builder.getDescription();
		publishingTime = builder.getPublishingTime();
		publisher = builder.getPublisher();
		mediaType = builder.getMediaType();
		fileSize = builder.getFileSize();
		fileURL = builder.getFileURL();
		votes = builder.getVotes();
		license = builder.getLicense();
		contributors = builder.getContributors();
		creators = builder.getCreators();

		if(voteManager == null)
		{
			throw new NullPointerException();
		}
	}

	@Override
	public boolean isExternal()
	{
		return isExternal;
	}

	@Override
	public String getTitle()
	{
		return title;
	}
	
	@Override
	public String getDescription()
	{
		return description;
	}

	@Override
	public DateTime getPublishingTime()
	{
		return publishingTime;
	}

	@Override
	public UserID getPublisher()
	{
		return publisher;
	}

	@Override
	public MediaType getMediaType()
	{
		return mediaType;
	}

	@Override
	public long getFileSize()
	{
		return fileSize;
	}

	@Override
	public URL getFileURL()
	{
		return fileURL;
	}

	@Override
	public String getLicense()
	{
		return license;
	}

	@Override
	public List<Contributor> getContributors()
	{
		return contributors;
	}

	@Override
	public List<UserID> getCreators()
	{
		return creators;
	}

	@Override
	public Votes getVotes()
	{
		return votes;
	}

	@Override
	public VoteType getUserVote(UserSession userSession)
	{
		return voteManager.getUserVote(userSession, getID());
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
}
