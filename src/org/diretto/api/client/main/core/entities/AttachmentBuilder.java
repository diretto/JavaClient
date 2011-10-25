package org.diretto.api.client.main.core.entities;

import java.net.URL;
import java.util.List;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.base.data.Contributor;
import org.diretto.api.client.base.data.MediaType;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Attachment}s.
 * 
 * @author Tobias Schlecht
 */
public final class AttachmentBuilder implements Builder<Attachment>
{
	private final AttachmentID attachmentID;

	private final VoteManager voteManager;

	private final boolean isExternal;

	private String title = null;
	private String description = null;
	private DateTime publishingTime = null;
	private UserID publisher = null;
	private MediaType mediaType = null;
	private long fileSize = 0;
	private URL fileURL = null;
	private Votes votes = null;
	private String license = null;
	private List<Contributor> contributors = null;
	private List<UserID> creators = null;

	/**
	 * Constructs a {@link AttachmentBuilder} object.
	 * 
	 * @param attachmentID The {@code AttachmentID}
	 * @param voteManager The {@code VoteManager}
	 * @param isExternal {@code true} if the file of the {@code Attachment} is
	 *        an external resource; otherwise {@code false}
	 */
	public AttachmentBuilder(AttachmentID attachmentID, VoteManager voteManager, boolean isExternal)
	{
		this.attachmentID = attachmentID;
		this.voteManager = voteManager;
		this.isExternal = isExternal;
	}

	/**
	 * Sets the title of the {@link Attachment}.
	 * 
	 * @param title The title of the {@code Attachment}
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder title(String title)
	{
		this.title = title;
		return this;
	}

	/**
	 * Sets the description of the {@link Attachment}.
	 * 
	 * @param description The description of the {@code Attachment}
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder description(String description)
	{
		this.description = description;
		return this;
	}

	/**
	 * Sets the publishing {@link DateTime}.
	 * 
	 * @param publishingTime The publishing {@code DateTime}
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder publishingTime(DateTime publishingTime)
	{
		this.publishingTime = publishingTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the publisher.
	 * 
	 * @param publisher The {@code UserID} of the publisher
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder publisher(UserID publisher)
	{
		this.publisher = publisher;
		return this;
	}

	/**
	 * Sets the {@link MediaType} of the resource.
	 * 
	 * @param mediaType the {@link MediaType} of the resource
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder mediaType(MediaType mediaType)
	{
		this.mediaType = mediaType;
		return this;
	}

	/**
	 * Sets the size of the file in {@code Bytes}. <br/><br/>
	 * 
	 * <i>Annotation:</i> If the file is an external resource, the file size has
	 * to be set to {@code 0}.
	 * 
	 * @param fileSize The size of the file in {@code Bytes}
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder fileSize(long fileSize)
	{
		this.fileSize = fileSize;
		return this;
	}

	/**
	 * Sets the {@link URL} of the file.
	 * 
	 * @param fileURL The {@code URL} of the file
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder fileURL(URL fileURL)
	{
		this.fileURL = fileURL;
		return this;
	}

	/**
	 * Sets the {@link Votes} of all {@link User}s.
	 * 
	 * @param votes The {@code Votes} of all {@code User}s
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder votes(Votes votes)
	{
		this.votes = votes;
		return this;
	}

	/**
	 * Sets the license of the {@link Attachment}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The given {@code String} can represent either the
	 * title of a license or a license text or an {@code URL} to a license.
	 * 
	 * @param license The license of the {@code Attachment}
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder license(String license)
	{
		this.license = license;
		return this;
	}

	/**
	 * Sets the {@link List} with all {@link Contributor}s.
	 * 
	 * @param contributors A {@code List} with all {@code Contributor}s
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder contributors(List<Contributor> contributors)
	{
		this.contributors = contributors;
		return this;
	}

	/**
	 * Sets the {@link List} with the {@link UserID}s of the creators.
	 * 
	 * @param creators A {@code List} with the {@code UserID}s of the creators
	 * @return The {@code AttachmentBuilder} object
	 */
	public AttachmentBuilder creators(List<UserID> creators)
	{
		this.creators = creators;
		return this;
	}

	/**
	 * Returns the {@link AttachmentID}.
	 * 
	 * @return The {@code AttachmentID}
	 */
	public AttachmentID getAttachmentID()
	{
		return attachmentID;
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
	 * Returns whether the file of the {@link Attachment} is an external
	 * resource.
	 * 
	 * @return {@code true} if the file of the {@code Attachment} is an external
	 *         resource; otherwise {@code false}
	 */
	public boolean isExternal()
	{
		return isExternal;
	}

	/**
	 * Returns the title.
	 * 
	 * @return The title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Returns the description.
	 * 
	 * @return The description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Returns the publishing {@link DateTime}.
	 * 
	 * @return The publishing {@code DateTime}
	 */
	public DateTime getPublishingTime()
	{
		return publishingTime;
	}

	/**
	 * Returns the {@link UserID} of the publisher.
	 * 
	 * @return The {@code UserID} of the publisher
	 */
	public UserID getPublisher()
	{
		return publisher;
	}

	/**
	 * Returns the {@link MediaType} of the resource.
	 * 
	 * @return The {@code MediaType} of the resource
	 */
	public MediaType getMediaType()
	{
		return mediaType;
	}

	/**
	 * Returns the size of the file in {@code Bytes}.
	 * 
	 * @return The size of the file in {@code Bytes}
	 */
	public long getFileSize()
	{
		return fileSize;
	}

	/**
	 * Returns the {@link URL} of the file.
	 * 
	 * @return The {@code URL} of the file
	 */
	public URL getFileURL()
	{
		return fileURL;
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

	/**
	 * Returns the license
	 * 
	 * @return The license
	 */
	public String getLicense()
	{
		return license;
	}

	/**
	 * Returns a {@link List} of all {@link Contributor}s.
	 * 
	 * @return A {@code List} of all {@code Contributor}s
	 */
	public List<Contributor> getContributors()
	{
		return contributors;
	}

	/**
	 * Returns a {@link List} with the {@link UserID}s of the creators.
	 * 
	 * @return A {@code List} with the {@code UserID}s of the creators
	 */
	public List<UserID> getCreators()
	{
		return creators;
	}

	@Override
	public Attachment build()
	{
		return new AttachmentImpl(this);
	}
}
