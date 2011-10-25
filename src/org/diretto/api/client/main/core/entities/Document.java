package org.diretto.api.client.main.core.entities;

import java.util.List;

import org.diretto.api.client.base.characteristic.Commentable;
import org.diretto.api.client.base.characteristic.Describable;
import org.diretto.api.client.base.characteristic.Linkable;
import org.diretto.api.client.base.characteristic.Tagable;
import org.diretto.api.client.base.data.BoundingBox;
import org.diretto.api.client.base.data.MediaMainType;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.TopographicPoint;
import org.diretto.api.client.base.data.UploadInfo;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.main.core.entities.creation.ExternalAttachmentCreationData;
import org.diretto.api.client.main.core.entities.creation.PlatformAttachmentCreationData;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This interface represents a {@code Document}.
 * 
 * @author Tobias Schlecht
 */
public interface Document extends Entity<DocumentID>, Commentable, Tagable, Linkable<DocumentID>, Describable
{
	/**
	 * Adds a new alternative {@link Position} to the {@link Document} and
	 * returns the {@link PositionID} if it was successful. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Position} of a {@link Document} refers to the
	 * location where the {@code Document} has been created.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param topographicPoint The {@code TopographicPoint} of the
	 *        {@code Position}
	 * @return The corresponding {@code PositionID}
	 */
	PositionID addAlternativePosition(UserSession userSession, TopographicPoint topographicPoint);

	/**
	 * Adds a new alternative {@link Time} to the {@link Document} and returns
	 * the {@link TimeID} if it was successful. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Time} of a {@link Document} refers to the
	 * point in time when the {@code Document} has been created.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param dateTime The exact creation {@code DateTime} of the
	 *        {@code Document}
	 * @return The corresponding {@code TimeID}
	 */
	TimeID addAlternativeTime(UserSession userSession, DateTime dateTime);

	/**
	 * Adds a new alternative {@link Time}. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Time} of a {@link Document} refers to the
	 * point in time when the {@code Document} has been created. <br/><br/>
	 * 
	 * <i>Important:</i> The {@link TimeRange} parameter is used to declare
	 * inaccurate data and not to specify a {@code Document} which has itself a
	 * duration (e.g. a video {@code Document}). The start {@link DateTime} of
	 * the {@code TimeRange} represents the earliest possible creation
	 * {@code DateTime} and the end {@code DateTime} of the {@code TimeRange}
	 * represents the latest possible creation {@code DateTime}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param timeRange The creation {@code TimeRange} of the {@code Document}
	 * @return The corresponding {@code TimeID}
	 */
	TimeID addAlternativeTime(UserSession userSession, TimeRange timeRange);

	/**
	 * Adds a new external {@link Attachment} to the {@link Document} and
	 * returns the {@link AttachmentID} if it was successful. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that the functionality of this method is not
	 * required for the creation of an initial {@code Attachment} of a
	 * {@link Document}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param externalAttachmentCreationData The
	 *        {@code ExternalAttachmentCreationData} object
	 * @return The corresponding {@code AttachmentID}
	 */
	AttachmentID addExternalAttachment(UserSession userSession, ExternalAttachmentCreationData externalAttachmentCreationData);

	/**
	 * Adds a new {@link KeyValue} to the {@link Document} and returns the
	 * {@link KeyValueID} if it was successful. <br/><br/>
	 * 
	 * <i>Annotation:</i> The valid characters for the {@code String} of the
	 * {@code key} parameter are alphanumeric characters or one of the following
	 * characters: "-" / ":" / "." / "_"
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param key The key of the {@code KeyValue}
	 * @param value The value of the {@code KeyValue}
	 * @return The corresponding {@code KeyValueID}
	 */
	KeyValueID addKeyValue(UserSession userSession, String key, String value);

	/**
	 * Adds a new platform {@link Attachment} to the {@link Document} and
	 * returns the {@link UploadInfo} if it was successful. <br/><br/>
	 * 
	 * <i>Important:</i> After the invocation of this method the resource must
	 * be stored by using the {@code JavaClientStoragePlugin} and the returned
	 * {@link UploadInfo} object. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that the functionality of this method is not
	 * required for the creation of an initial {@code Attachment} of a
	 * {@link Document}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param platformAttachmentCreationData The
	 *        {@code PlatformAttachmentCreationData} object
	 * @return The {@code UploadInfo} object
	 */
	UploadInfo addPlatformAttachment(UserSession userSession, PlatformAttachmentCreationData platformAttachmentCreationData);

	/**
	 * Returns the {@link Attachment} with the specified {@link AttachmentID},
	 * or {@code null} if there is no {@code Attachment} with the given
	 * {@code AttachmentID}.
	 * 
	 * @param attachmentID An {@code AttachmentID}
	 * @return The {@code Attachment}
	 */
	Attachment getAttachment(AttachmentID attachmentID);

	/**
	 * Returns a {@link ResultSet} of all {@link Attachment}s and the mapped
	 * {@link AttachmentID}s.
	 * 
	 * @return A {@code ResultSet} with the {@code Attachment}s
	 */
	ResultSet<AttachmentID, Attachment> getAttachments();

	/**
	 * Returns the best voted {@link Position}. <br/><br/>
	 * 
	 * The best voted {@code Position} is the {@code Position} with the highest
	 * returned value of {@link Votes#getCalculatedValue()}. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Position} of a {@link Document} refers to the
	 * location where the {@code Document} has been created.
	 * 
	 * @return The best voted {@code Position}
	 */
	Position getBestVotedPosition();

	/**
	 * Returns the best voted {@link Time}. <br/><br/>
	 * 
	 * The best voted {@code Time} is the {@code Time} with the highest returned
	 * value of {@link Votes#getCalculatedValue()}. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Time} of a {@link Document} refers to the
	 * point in time when the {@code Document} has been created.
	 * 
	 * @return The best voted {@code Time}
	 */
	Time getBestVotedTime();

	/**
	 * Returns the initial {@link Attachment}. <br/><br/>
	 * 
	 * The initial {@code Attachment} is the first {@code Attachment} of a
	 * {@link Document}.
	 * 
	 * @return The initial {@code Attachment}
	 */
	Attachment getInitialAttachment();

	/**
	 * Returns the {@link KeyValue} with the specified {@link KeyValueID} or
	 * {@code null} if there is no {@code KeyValue} with the given
	 * {@code KeyValueID}.
	 * 
	 * @param keyValueID A {@code KeyValueID}
	 * @return The {@code KeyValue}
	 */
	KeyValue getKeyValue(KeyValueID keyValueID);

	/**
	 * Returns a {@link ResultSet} of all {@link KeyValue}s and the mapped
	 * {@link KeyValueID}s.
	 * 
	 * @return A {@code ResultSet} with the {@code KeyValue}s
	 */
	ResultSet<KeyValueID, KeyValue> getKeyValues();

	/**
	 * Returns the {@link MediaMainType}.
	 * 
	 * @return The {@code MediaMainType}
	 */
	MediaMainType getMediaMainType();

	/**
	 * Returns the {@link Position} with the specified {@link PositionID} or
	 * {@code null} if there is no {@code Position} with the given
	 * {@code PositionID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Position} of a {@link Document} refers to the
	 * location where the {@code Document} has been created.
	 * 
	 * @param positionID A {@code PositionID}
	 * @return The {@code Position}
	 */
	Position getPosition(PositionID positionID);

	/**
	 * Returns a {@link ResultSet} of all {@link Position}s and the mapped
	 * {@link PositionID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Position} of a {@link Document} refers to the
	 * location where the {@code Document} has been created.
	 * 
	 * @return A {@code ResultSet} with the {@code Position}s
	 */
	ResultSet<PositionID, Position> getPositions();

	/**
	 * Returns the {@link UserID} of the publisher.
	 * 
	 * @return The {@code UserID} of the publisher
	 */
	UserID getPublisher();

	/**
	 * Returns the publishing {@link DateTime}.
	 * 
	 * @return The publishing {@code DateTime}
	 */
	DateTime getPublishingTime();

	/**
	 * Returns the {@link Time} with the specified {@link TimeID} or
	 * {@code null} if there is no {@code Time} with the given {@code TimeID}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Time} of a {@link Document} refers to the
	 * point in time when the {@code Document} has been created.
	 * 
	 * @param timeID A {@code TimeID}
	 * @return The {@code Time}
	 */
	Time getTime(TimeID timeID);

	/**
	 * Returns a {@link ResultSet} of all {@link Time}s and the mapped
	 * {@link TimeID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> A {@code Time} of a {@link Document} refers to the
	 * point in time when the {@code Document} has been created.
	 * 
	 * @return A {@code ResultSet} with the {@code Time}s
	 */
	ResultSet<TimeID, Time> getTimes();

	/**
	 * Removes the {@link KeyValue} with the specified {@link KeyValueID} if the
	 * {@link User} of the given {@link UserSession} has created the
	 * {@code KeyValue}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param keyValueID The {@code KeyValueID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeKeyValue(UserSession userSession, KeyValueID keyValueID);

	/**
	 * Determines whether this {@link Document} is located within the given
	 * {@link BoundingBox}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that the {@link Position} variance (if this
	 * {@code Document} has one) will not be taken into account.
	 * 
	 * @param boundingBox A {@code BoundingBox}
	 * @return {@code true} if the {@code Document} is located within the
	 *         {@code BoundingBox}; otherwise {@code false}
	 */
	boolean isLocatedWithin(BoundingBox boundingBox);

	/**
	 * Determines whether this {@link Document} is located within the given
	 * {@link TimeRange}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that only the average {@link DateTime} of the
	 * {@code Document} {@link Time} will be taken into account.
	 * 
	 * @param timeRange A {@code TimeRange}
	 * @return {@code true} if the {@code Document} is located within the
	 *         {@code TimeRange}; otherwise {@code false}
	 */
	boolean isLocatedWithin(TimeRange timeRange);

	/**
	 * Determines whether this {@link Document} is located within the given
	 * {@link BoundingBox} and within the given {@link TimeRange}. <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that the {@link Position} variance (if this
	 * {@code Document} has one) will not be taken into account. Note also that
	 * only the average {@link DateTime} of the {@code Document} {@link Time}
	 * will be taken into account.
	 * 
	 * @param boundingBox A {@code BoundingBox}
	 * @param timeRange A {@code TimeRange}
	 * @return {@code true} if the {@code Document} is located within the
	 *         {@code BoundingBox} and within the {@code TimeRange}; otherwise
	 *         {@code false}
	 */
	boolean isLocatedWithin(BoundingBox boundingBox, TimeRange timeRange);

	/**
	 * Determines whether this {@link Document} contains all {@link Tag}s of the
	 * given {@code List}, whereby the {@code List} contains the {@code String}
	 * representations of the {@code Tag}s.
	 * 
	 * @param tags A {@code List} of {@code Tag}s in {@code String}
	 *        representation
	 * @return {@code true} if the {@code Document} contains all {@code Tag}s;
	 *         otherwise {@code false}
	 */
	boolean containsTags(List<String> tags);

	/**
	 * Determines whether this {@link Document} is located within the given
	 * {@link BoundingBox} and within the given {@link TimeRange} and contains
	 * all {@link Tag}s of the given {@code List}, whereby the {@code List}
	 * contains the {@code String} representations of the {@code Tag}s.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> Note that the {@link Position} variance (if this
	 * {@code Document} has one) will not be taken into account. Note also that
	 * only the average {@link DateTime} of the {@code Document} {@link Time}
	 * will be taken into account.
	 * 
	 * @param boundingBox A {@code BoundingBox}
	 * @param timeRange A {@code TimeRange}
	 * @param tags A {@code List} of {@code Tag}s in {@code String}
	 *        representation
	 * @return {@code true} if the {@code Document} is located within the
	 *         {@code BoundingBox} and within the {@code TimeRange} and contains
	 *         all {@code Tag}s; otherwise {@code false}
	 */
	boolean isLocatedWithinAndContainsTags(BoundingBox boundingBox, TimeRange timeRange, List<String> tags);
}
