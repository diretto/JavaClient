package org.diretto.api.client.main.core.entities;

import java.util.List;
import java.util.Vector;

import org.diretto.api.client.base.characteristic.Cachable;
import org.diretto.api.client.base.data.BoundingBox;
import org.diretto.api.client.base.data.MediaMainType;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.ResultSetImpl;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.TopographicPoint;
import org.diretto.api.client.base.data.UploadInfo;
import org.diretto.api.client.base.entities.AbstractEntity;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.main.core.DataManagerImpl;
import org.diretto.api.client.main.core.entities.creation.ExternalAttachmentCreationData;
import org.diretto.api.client.main.core.entities.creation.PlatformAttachmentCreationData;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class is the implementation class of the {@link Document} interface.
 * 
 * @author Tobias Schlecht
 */
final class DocumentImpl extends AbstractEntity<DocumentID> implements Document, Cachable
{
	private final DataManagerImpl dataManager;

	private final boolean completelyLoaded;
	private final LoadType loadType;

	private final MediaMainType mediaMainType;
	private final DateTime publishingTime;
	private final UserID publisher;
	private final String title;
	private final String description;

	private final ResultSet<CommentID, Comment> comments;
	private final ResultSet<TagID, Tag> tags;
	private final ResultSet<AttachmentID, Attachment> attachments;
	private final ResultSet<PositionID, Position> positions;
	private final ResultSet<TimeID, Time> times;
	private final ResultSet<KeyValueID, KeyValue> keyValues;
	private final ResultSet<LinkID, Link> incomingLinks;
	private final ResultSet<LinkID, Link> outgoingLinks;

	private Attachment initialAttachment = null;
	private Position bestVotedPosition = null;
	private Time bestVotedTime = null;

	/**
	 * Constructs an object of the {@link Document} interface.
	 * 
	 * @param builder The {@code DocumentBuilder} object
	 */
	DocumentImpl(DocumentBuilder builder)
	{
		super(builder.getDocumentID());

		dataManager = builder.getDataManager();

		loadType = builder.getLoadType();

		if(loadType == LoadType.COMPLETE)
		{
			completelyLoaded = true;
		}
		else
		{
			completelyLoaded = false;
		}

		mediaMainType = builder.getMediaMainType();
		publishingTime = builder.getPublishingTime();
		publisher = builder.getPublisher();
		title = builder.getTitle();
		description = builder.getDescription();

		comments = builder.getComments();
		tags = builder.getTags();
		attachments = builder.getAttachments();
		positions = builder.getPositions();
		times = builder.getTimes();
		keyValues = builder.getKeyValues();
		incomingLinks = builder.getIncomingLinks();
		outgoingLinks = builder.getOutgoingLinks();

		if(loadType == LoadType.SNAPSHOT)
		{
			initialAttachment = ((ResultSetImpl<AttachmentID, Attachment>) attachments).getLoadedData().get(0);
			bestVotedPosition = ((ResultSetImpl<PositionID, Position>) positions).getLoadedData().get(0);
			bestVotedTime = ((ResultSetImpl<TimeID, Time>) times).getLoadedData().get(0);
		}

		if(dataManager == null || loadType == null)
		{
			throw new NullPointerException();
		}
	}

	@Override
	public boolean isCompletelyLoaded()
	{
		return completelyLoaded;
	}

	@Override
	public LoadType getLoadType()
	{
		return loadType;
	}

	@Override
	public MediaMainType getMediaMainType()
	{
		return mediaMainType;
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
	public CommentID addComment(UserSession userSession, String content)
	{
		return dataManager.addCommentToDocument(userSession, getID(), content);
	}

	@Override
	public Comment getComment(CommentID commentID)
	{
		if(completelyLoaded)
		{
			Comment comment = comments.get(commentID);

			if(comment != null)
			{
				return comment;
			}
		}

		return ((DocumentImpl) dataManager.getDocument(getID(), LoadType.COMPLETE, true)).getCommentFromLatestData(commentID);
	}

	/**
	 * Returns the {@link Comment} with the specified {@link CommentID}, or
	 * {@code null} if there is no {@code Comment} with the given
	 * {@code CommentID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> This method should only be used if it is sure that
	 * this object represents the latest data from the API.
	 * 
	 * @param commentID A {@code CommentID}
	 * @return The {@code Comment}
	 */
	private Comment getCommentFromLatestData(CommentID commentID)
	{
		return comments.get(commentID);
	}

	@Override
	public ResultSet<CommentID, Comment> getComments()
	{
		if(completelyLoaded)
		{
			return comments;
		}
		else
		{
			return dataManager.getDocument(getID(), LoadType.COMPLETE, true).getComments();
		}
	}

	@Override
	public TagID addTag(UserSession userSession, String value)
	{
		return dataManager.addTagToEntity(userSession, getID(), value);
	}

	@Override
	public Tag getTag(TagID tagID)
	{
		if(loadType == LoadType.COMPLETE || loadType == LoadType.SNAPSHOT)
		{
			Tag tag = tags.get(tagID);

			if(tag != null)
			{
				return tag;
			}
		}

		return ((DocumentImpl) dataManager.getDocument(getID(), LoadType.SNAPSHOT, true)).getTagFromLatestData(tagID);
	}

	/**
	 * Returns the {@link Tag} with the specified {@link TagID}, or {@code null}
	 * if there is no {@code Tag} with the given {@code TagID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> This method should only be used if it is sure that
	 * this object represents the latest data from the API.
	 * 
	 * @param tagID A {@code TagID}
	 * @return The {@code Tag}
	 */
	private Tag getTagFromLatestData(TagID tagID)
	{
		return tags.get(tagID);
	}

	@Override
	public ResultSet<TagID, Tag> getTags()
	{
		if(loadType == LoadType.COMPLETE || loadType == LoadType.SNAPSHOT)
		{
			return tags;
		}
		else
		{
			return dataManager.getDocument(getID(), LoadType.SNAPSHOT, true).getTags();
		}
	}

	@Override
	public UploadInfo addPlatformAttachment(UserSession userSession, PlatformAttachmentCreationData platformAttachmentCreationData)
	{
		return dataManager.addPlatformAttachmentToDocument(userSession, getID(), platformAttachmentCreationData);
	}

	@Override
	public AttachmentID addExternalAttachment(UserSession userSession, ExternalAttachmentCreationData externalAttachmentCreationData)
	{
		return dataManager.addExternalAttachmentToDocument(userSession, getID(), externalAttachmentCreationData);
	}

	@Override
	public Attachment getInitialAttachment()
	{
		if(initialAttachment != null)
		{
			return initialAttachment;
		}

		if(loadType == LoadType.COMPLETE)
		{
			List<Attachment> attachments = ((ResultSetImpl<AttachmentID, Attachment>) this.attachments).getLoadedData();

			Attachment initialAttachment = attachments.get(0);
			DateTime initialAttachmentTime = initialAttachment.getPublishingTime();

			for(Attachment attachment : attachments)
			{
				if(attachment.getPublishingTime().isBefore(initialAttachmentTime))
				{
					initialAttachment = attachment;
					initialAttachmentTime = attachment.getPublishingTime();
				}
			}

			this.initialAttachment = initialAttachment;

			return initialAttachment;
		}
		else
		{
			initialAttachment = ((DocumentImpl) dataManager.getDocument(getID(), LoadType.SNAPSHOT, true)).initialAttachment;

			return initialAttachment;
		}
	}

	@Override
	public Attachment getAttachment(AttachmentID attachmentID)
	{
		if(loadType == LoadType.COMPLETE || loadType == LoadType.SNAPSHOT)
		{
			Attachment attachment = attachments.get(attachmentID);

			if(attachment != null)
			{
				return attachment;
			}
		}

		return ((DocumentImpl) dataManager.getDocument(getID(), LoadType.COMPLETE, true)).getAttachmentFromLatestData(attachmentID);
	}

	/**
	 * Returns the {@link Attachment} with the specified {@link AttachmentID},
	 * or {@code null} if there is no {@code Attachment} with the given
	 * {@code AttachmentID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> This method should only be used if it is sure that
	 * this object represents the latest data from the API.
	 * 
	 * @param attachmentID A {@code AttachmentID}
	 * @return The {@code Attachment}
	 */
	private Attachment getAttachmentFromLatestData(AttachmentID attachmentID)
	{
		return attachments.get(attachmentID);
	}

	@Override
	public ResultSet<AttachmentID, Attachment> getAttachments()
	{
		if(loadType == LoadType.COMPLETE)
		{
			return attachments;
		}
		else
		{
			return dataManager.getDocument(getID(), LoadType.COMPLETE, true).getAttachments();
		}
	}

	@Override
	public PositionID addAlternativePosition(UserSession userSession, TopographicPoint topographicPoint)
	{
		return dataManager.addAlternativePositionToDocument(userSession, getID(), topographicPoint);
	}

	@Override
	public Position getBestVotedPosition()
	{
		if(bestVotedPosition != null)
		{
			return bestVotedPosition;
		}

		if(loadType == LoadType.COMPLETE)
		{
			List<Position> positions = ((ResultSetImpl<PositionID, Position>) this.positions).getLoadedData();

			Position bestVotedPosition = positions.get(0);
			int bestVotedPositionValue = bestVotedPosition.getVotes().getCalculatedValue();

			for(Position position : positions)
			{
				if(position.getVotes().getCalculatedValue() > bestVotedPositionValue)
				{
					bestVotedPosition = position;
					bestVotedPositionValue = position.getVotes().getCalculatedValue();
				}
			}

			this.bestVotedPosition = bestVotedPosition;

			return bestVotedPosition;
		}
		else
		{
			bestVotedPosition = ((DocumentImpl) dataManager.getDocument(getID(), LoadType.SNAPSHOT, true)).bestVotedPosition;

			return bestVotedPosition;
		}
	}

	@Override
	public Position getPosition(PositionID positionID)
	{
		if(loadType == LoadType.COMPLETE || loadType == LoadType.SNAPSHOT)
		{
			Position position = positions.get(positionID);

			if(position != null)
			{
				return position;
			}
		}

		return ((DocumentImpl) dataManager.getDocument(getID(), LoadType.COMPLETE, true)).getPositionFromLatestData(positionID);
	}

	/**
	 * Returns the {@link Position} with the specified {@link PositionID}, or
	 * {@code null} if there is no {@code Position} with the given
	 * {@code PositionID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> This method should only be used if it is sure that
	 * this object represents the latest data from the API.
	 * 
	 * @param positionID A {@code PositionID}
	 * @return The {@code Position}
	 */
	private Position getPositionFromLatestData(PositionID positionID)
	{
		return positions.get(positionID);
	}

	@Override
	public ResultSet<PositionID, Position> getPositions()
	{
		if(loadType == LoadType.COMPLETE)
		{
			return positions;
		}
		else
		{
			return dataManager.getDocument(getID(), LoadType.COMPLETE, true).getPositions();
		}
	}

	@Override
	public TimeID addAlternativeTime(UserSession userSession, DateTime dateTime)
	{
		return addAlternativeTime(userSession, new TimeRange(dateTime, dateTime));
	}

	@Override
	public TimeID addAlternativeTime(UserSession userSession, TimeRange timeRange)
	{
		return dataManager.addAlternativeTimeToDocument(userSession, getID(), timeRange);
	}

	@Override
	public Time getBestVotedTime()
	{
		if(bestVotedTime != null)
		{
			return bestVotedTime;
		}

		if(loadType == LoadType.COMPLETE)
		{
			List<Time> times = ((ResultSetImpl<TimeID, Time>) this.times).getLoadedData();

			Time bestVotedTime = times.get(0);
			int bestVotedTimeValue = bestVotedTime.getVotes().getCalculatedValue();

			for(Time time : times)
			{
				if(time.getVotes().getCalculatedValue() > bestVotedTimeValue)
				{
					bestVotedTime = time;
					bestVotedTimeValue = time.getVotes().getCalculatedValue();
				}
			}

			this.bestVotedTime = bestVotedTime;

			return bestVotedTime;
		}
		else
		{
			bestVotedTime = ((DocumentImpl) dataManager.getDocument(getID(), LoadType.SNAPSHOT, true)).bestVotedTime;

			return bestVotedTime;
		}
	}

	@Override
	public Time getTime(TimeID timeID)
	{
		if(loadType == LoadType.COMPLETE || loadType == LoadType.SNAPSHOT)
		{
			Time time = times.get(timeID);

			if(time != null)
			{
				return time;
			}
		}

		return ((DocumentImpl) dataManager.getDocument(getID(), LoadType.COMPLETE, true)).getTimeFromLatestData(timeID);
	}

	/**
	 * Returns the {@link Time} with the specified {@link TimeID}, or
	 * {@code null} if there is no {@code Time} with the given {@code TimeID}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> This method should only be used if it is sure that
	 * this object represents the latest data from the API.
	 * 
	 * @param timeID A {@code TimeID}
	 * @return The {@code Time}
	 */
	private Time getTimeFromLatestData(TimeID timeID)
	{
		return times.get(timeID);
	}

	@Override
	public ResultSet<TimeID, Time> getTimes()
	{
		if(loadType == LoadType.COMPLETE)
		{
			return times;
		}
		else
		{
			return dataManager.getDocument(getID(), LoadType.COMPLETE, true).getTimes();
		}
	}

	@Override
	public KeyValueID addKeyValue(UserSession userSession, String key, String value)
	{
		return dataManager.addKeyValueToDocument(userSession, getID(), key, value);
	}

	@Override
	public boolean removeKeyValue(UserSession userSession, KeyValueID keyValueID)
	{
		return dataManager.removeKeyValueFromDocument(userSession, getID(), keyValueID);
	}

	@Override
	public KeyValue getKeyValue(KeyValueID keyValueID)
	{
		if(completelyLoaded)
		{
			KeyValue keyValue = keyValues.get(keyValueID);

			if(keyValue != null)
			{
				return keyValue;
			}
		}

		return ((DocumentImpl) dataManager.getDocument(getID(), LoadType.COMPLETE, true)).getKeyValueFromLatestData(keyValueID);
	}

	/**
	 * Returns the {@link KeyValue} with the specified {@link KeyValueID}, or
	 * {@code null} if there is no {@code KeyValue} with the given
	 * {@code KeyValueID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> This method should only be used if it is sure that
	 * this object represents the latest data from the API.
	 * 
	 * @param keyValueID A {@code KeyValueID}
	 * @return The {@code KeyValue}
	 */
	private KeyValue getKeyValueFromLatestData(KeyValueID keyValueID)
	{
		return keyValues.get(keyValueID);
	}

	@Override
	public ResultSet<KeyValueID, KeyValue> getKeyValues()
	{
		if(completelyLoaded)
		{
			return keyValues;
		}
		else
		{
			return dataManager.getDocument(getID(), LoadType.COMPLETE, true).getKeyValues();
		}
	}

	@Override
	public LinkID addLink(UserSession userSession, DocumentID destinationDocumentID, String title, String description)
	{
		return dataManager.addLinkBetweenDocuments(userSession, getID(), destinationDocumentID, title, description);
	}

	@Override
	public Link getLink(LinkID linkID)
	{
		if(completelyLoaded)
		{
			Link link = outgoingLinks.get(linkID);

			if(link == null)
			{
				link = incomingLinks.get(linkID);
			}

			if(link != null)
			{
				return link;
			}
		}

		return ((DocumentImpl) dataManager.getDocument(getID(), LoadType.COMPLETE, true)).getLinkFromLatestData(linkID);
	}

	/**
	 * Returns the {@link Link} with the specified {@link LinkID}, or
	 * {@code null} if there is no {@code Link} with the given {@code LinkID}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> This method should only be used if it is sure that
	 * this object represents the latest data from the API.
	 * 
	 * @param linkID A {@code LinkID}
	 * @return The {@code Link}
	 */
	private Link getLinkFromLatestData(LinkID linkID)
	{
		Link link = outgoingLinks.get(linkID);

		if(link == null)
		{
			link = incomingLinks.get(linkID);
		}

		return link;
	}

	@Override
	public ResultSet<LinkID, Link> getIncomingLinks()
	{
		if(completelyLoaded)
		{
			return incomingLinks;
		}
		else
		{
			return dataManager.getDocument(getID(), LoadType.COMPLETE, true).getIncomingLinks();
		}
	}

	@Override
	public ResultSet<LinkID, Link> getOutgoingLinks()
	{
		if(completelyLoaded)
		{
			return outgoingLinks;
		}
		else
		{
			return dataManager.getDocument(getID(), LoadType.COMPLETE, true).getOutgoingLinks();
		}
	}

	@Override
	public boolean isLocatedWithin(BoundingBox boundingBox)
	{
		if(boundingBox == null)
		{
			throw new NullPointerException();
		}

		Position position = getBestVotedPosition();

		return boundingBox.surrounds(position.getLatitude(), position.getLongitude());
	}

	@Override
	public boolean isLocatedWithin(TimeRange timeRange)
	{
		if(timeRange == null)
		{
			throw new NullPointerException();
		}

		Time time = getBestVotedTime();

		return timeRange.surrounds(time.getAverageTime());
	}

	@Override
	public boolean isLocatedWithin(BoundingBox boundingBox, TimeRange timeRange)
	{
		if(boundingBox == null || timeRange == null)
		{
			throw new NullPointerException();
		}

		calculateBestVotedPositionAndTime();

		if(isLocatedWithin(boundingBox) && isLocatedWithin(timeRange))
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean containsTags(List<String> tags)
	{
		if(tags == null)
		{
			throw new NullPointerException();
		}

		ResultSet<TagID, Tag> documentTags = getTags();

		List<String> documentTagValues = new Vector<String>();

		for(Tag tag : documentTags)
		{
			documentTagValues.add(tag.getValue());
		}

		if(documentTagValues.containsAll(tags))
		{
			return true;
		}

		return false;
	}

	@Override
	public boolean isLocatedWithinAndContainsTags(BoundingBox boundingBox, TimeRange timeRange, List<String> tags)
	{
		if(isLocatedWithin(boundingBox, timeRange) && containsTags(tags))
		{
			return true;
		}

		return false;
	}

	/**
	 * Calculates the best voted {@link Position} and the best voted
	 * {@link Time} but checks also whether the calculation is really necessary
	 * and in case it is not really necessary the calculation will not be
	 * executed.
	 */
	private void calculateBestVotedPositionAndTime()
	{
		if(bestVotedPosition == null || bestVotedTime == null)
		{
			if(loadType == LoadType.COMPLETE)
			{
				if(bestVotedPosition == null)
				{
					List<Position> positions = ((ResultSetImpl<PositionID, Position>) this.positions).getLoadedData();

					Position bestVotedPosition = positions.get(0);
					int bestVotedPositionValue = bestVotedPosition.getVotes().getCalculatedValue();

					for(Position position : positions)
					{
						if(position.getVotes().getCalculatedValue() > bestVotedPositionValue)
						{
							bestVotedPosition = position;
							bestVotedPositionValue = position.getVotes().getCalculatedValue();
						}
					}

					this.bestVotedPosition = bestVotedPosition;
				}

				if(bestVotedTime == null)
				{
					List<Time> times = ((ResultSetImpl<TimeID, Time>) this.times).getLoadedData();

					Time bestVotedTime = times.get(0);
					int bestVotedTimeValue = bestVotedTime.getVotes().getCalculatedValue();

					for(Time time : times)
					{
						if(time.getVotes().getCalculatedValue() > bestVotedTimeValue)
						{
							bestVotedTime = time;
							bestVotedTimeValue = time.getVotes().getCalculatedValue();
						}
					}

					this.bestVotedTime = bestVotedTime;
				}
			}
			else
			{
				DocumentImpl document = ((DocumentImpl) dataManager.getDocument(getID(), LoadType.SNAPSHOT, true));

				bestVotedPosition = document.bestVotedPosition;
				bestVotedTime = document.bestVotedTime;
			}
		}
	}
}
