package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.base.data.MediaMainType;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.main.core.DataManager;
import org.diretto.api.client.main.core.DataManagerImpl;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Document}s.
 * 
 * @author Tobias Schlecht
 */
public final class DocumentBuilder implements Builder<Document>
{
	private final DocumentID documentID;

	private final DataManagerImpl dataManager;

	private final LoadType loadType;

	private MediaMainType mediaMainType = null;
	private DateTime publishingTime = null;
	private UserID publisher = null;
	private String title = null;
	private String description = null;

	private ResultSet<CommentID, Comment> comments = null;
	private ResultSet<TagID, Tag> tags = null;
	private ResultSet<AttachmentID, Attachment> attachments = null;
	private ResultSet<PositionID, Position> positions = null;
	private ResultSet<TimeID, Time> times = null;
	private ResultSet<KeyValueID, KeyValue> keyValues = null;
	private ResultSet<LinkID, Link> incomingLinks = null;
	private ResultSet<LinkID, Link> outgoingLinks = null;

	/**
	 * Constructs a {@link DocumentBuilder} object.
	 * 
	 * @param documentID The {@code DocumentID}
	 * @param dataManager The {@code DataManager}
	 * @param loadType The {@code LoadType}
	 */
	public DocumentBuilder(DocumentID documentID, DataManagerImpl dataManager, LoadType loadType)
	{
		this.documentID = documentID;
		this.dataManager = dataManager;
		this.loadType = loadType;
	}

	/**
	 * Sets the {@link MediaMainType}.
	 * 
	 * @param mediaMainType The {@code MediaMainType}
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder mediaMainType(MediaMainType mediaMainType)
	{
		this.mediaMainType = mediaMainType;
		return this;
	}

	/**
	 * Sets the publishing {@link DateTime}.
	 * 
	 * @param publishingTime The publishing {@code DateTime}
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder publishingTime(DateTime publishingTime)
	{
		this.publishingTime = publishingTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the publisher.
	 * 
	 * @param publisher The {@code UserID} of the publisher
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder publisher(UserID publisher)
	{
		this.publisher = publisher;
		return this;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title The title
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder title(String title)
	{
		this.title = title;
		return this;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description The description
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder description(String description)
	{
		this.description = description;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the {@link Comment}s.
	 * 
	 * @param comments The {@code ResultSet} with the {@code Comment}s
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder comments(ResultSet<CommentID, Comment> comments)
	{
		this.comments = comments;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the {@link Tag}s.
	 * 
	 * @param tags The {@code ResultSet} with the {@code Tag}s
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder tags(ResultSet<TagID, Tag> tags)
	{
		this.tags = tags;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the {@link Attachment}s.
	 * 
	 * @param attachments The {@code ResultSet} with the {@code Attachment}s
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder attachments(ResultSet<AttachmentID, Attachment> attachments)
	{
		this.attachments = attachments;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the {@link Position}s.
	 * 
	 * @param positions The {@code ResultSet} with the {@code Position}s
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder positions(ResultSet<PositionID, Position> positions)
	{
		this.positions = positions;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the {@link Time}s.
	 * 
	 * @param times The {@code ResultSet} with the {@code Time}s
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder times(ResultSet<TimeID, Time> times)
	{
		this.times = times;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the {@link KeyValue}s.
	 * 
	 * @param keyValues The {@code ResultSet} with the {@code KeyValue}s
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder keyValues(ResultSet<KeyValueID, KeyValue> keyValues)
	{
		this.keyValues = keyValues;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the incoming {@link Link}s.
	 * 
	 * @param incomingLinks The {@code ResultSet} with the incoming {@code Link}
	 *        s
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder incomingLinks(ResultSet<LinkID, Link> incomingLinks)
	{
		this.incomingLinks = incomingLinks;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the outgoing {@link Link}s.
	 * 
	 * @param outgoingLinks The {@code ResultSet} with the outgoing {@code Link}
	 *        s
	 * @return The {@code DocumentBuilder} object
	 */
	public DocumentBuilder outgoingLinks(ResultSet<LinkID, Link> outgoingLinks)
	{
		this.outgoingLinks = outgoingLinks;
		return this;
	}

	/**
	 * Returns the {@link DocumentID}.
	 * 
	 * @return The {@code DocumentID}
	 */
	public DocumentID getDocumentID()
	{
		return documentID;
	}

	/**
	 * Returns the {@link DataManager}.
	 * 
	 * @return The {@code DataManager}
	 */
	public DataManagerImpl getDataManager()
	{
		return dataManager;
	}

	/**
	 * Returns the {@link LoadType}.
	 * 
	 * @return The {@code LoadType}
	 */
	public LoadType getLoadType()
	{
		return loadType;
	}

	/**
	 * Returns the {@link MediaMainType}.
	 * 
	 * @return The {@code MediaMainType}
	 */
	public MediaMainType getMediaMainType()
	{
		return mediaMainType;
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
	 * Returns the {@link ResultSet} with the {@link Comment}s.
	 * 
	 * @return The {@code ResultSet} with the {@code Comment}s
	 */
	public ResultSet<CommentID, Comment> getComments()
	{
		return comments;
	}

	/**
	 * Returns the {@link ResultSet} with the {@link Tag}s.
	 * 
	 * @return The {@code ResultSet} with the {@code Taga}s
	 */
	public ResultSet<TagID, Tag> getTags()
	{
		return tags;
	}

	/**
	 * Returns the {@link ResultSet} with the {@link Attachment}s.
	 * 
	 * @return The {@code ResultSet} with the {@code Attachment}s
	 */
	public ResultSet<AttachmentID, Attachment> getAttachments()
	{
		return attachments;
	}

	/**
	 * Returns the {@link ResultSet} with the {@link Position}s.
	 * 
	 * @return The {@code ResultSet} with the {@code Position}s
	 */
	public ResultSet<PositionID, Position> getPositions()
	{
		return positions;
	}

	/**
	 * Returns the {@link ResultSet} with the {@link Time}s.
	 * 
	 * @return The {@code ResultSet} with the {@code Time}s
	 */
	public ResultSet<TimeID, Time> getTimes()
	{
		return times;
	}

	/**
	 * Returns the {@link ResultSet} with the {@link KeyValue}s.
	 * 
	 * @return The {@code ResultSet} with the {@code KeyValue}s
	 */
	public ResultSet<KeyValueID, KeyValue> getKeyValues()
	{
		return keyValues;
	}

	/**
	 * Returns the {@link ResultSet} with the incoming {@link Link}s.
	 * 
	 * @return The {@code ResultSet} with the incoming{@code Link}s
	 */
	public ResultSet<LinkID, Link> getIncomingLinks()
	{
		return incomingLinks;
	}

	/**
	 * Returns the {@link ResultSet} with the outgoing {@link Link}s.
	 * 
	 * @return The {@code ResultSet} with the outgoing{@code Link}s
	 */
	public ResultSet<LinkID, Link> getOutgoingLinks()
	{
		return outgoingLinks;
	}

	@Override
	public Document build()
	{
		return new DocumentImpl(this);
	}
}
