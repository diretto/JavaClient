package org.diretto.api.client.main.core;

import java.util.List;
import java.util.UUID;

import org.diretto.api.client.base.data.BoundingBox;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.TopographicPoint;
import org.diretto.api.client.base.data.UploadInfo;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.types.LoadType;
import org.diretto.api.client.base.types.MessageBoxType;
import org.diretto.api.client.main.core.entities.Attachment;
import org.diretto.api.client.main.core.entities.Collection;
import org.diretto.api.client.main.core.entities.CollectionID;
import org.diretto.api.client.main.core.entities.Comment;
import org.diretto.api.client.main.core.entities.CommentID;
import org.diretto.api.client.main.core.entities.Document;
import org.diretto.api.client.main.core.entities.DocumentID;
import org.diretto.api.client.main.core.entities.Link;
import org.diretto.api.client.main.core.entities.LinkID;
import org.diretto.api.client.main.core.entities.Message;
import org.diretto.api.client.main.core.entities.MessageID;
import org.diretto.api.client.main.core.entities.Position;
import org.diretto.api.client.main.core.entities.Tag;
import org.diretto.api.client.main.core.entities.TagID;
import org.diretto.api.client.main.core.entities.Time;
import org.diretto.api.client.main.core.entities.creation.ExternalAttachmentCreationData;
import org.diretto.api.client.main.core.entities.creation.PlatformAttachmentCreationData;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserID;
import org.diretto.api.client.user.UserInfo;
import org.joda.time.DateTime;

/**
 * This interface represents a {@code DataManager}. <br/><br/>
 * 
 * The {@code DataManager} provides the bulk of the platform functionalities in
 * respect of {@code Core Service} API operations.
 * 
 * @author Tobias Schlecht
 */
public interface DataManager
{
	/**
	 * Creates a new {@link Collection} and returns the {@link CollectionID} if
	 * it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param title The title of the {@code Collection}
	 * @param description The description of the {@code Collection}
	 * @param privateSetting {@code true} if the {@code Collection} is
	 *        {@code private} (then only the {@code User} who created the
	 *        {@code Collection} has access to the {@code Collection});
	 *        {@code false} if the {@code Collection} is {@code public}
	 * @return The corresponding {@code CollectionID}
	 */
	CollectionID createCollection(UserSession userSession, String title, String description, boolean privateSetting);

	/**
	 * Creates a new {@link Document} with an external {@link Attachment} and
	 * returns the {@link DocumentID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param externalAttachmentCreationData The
	 *        {@code ExternalAttachmentCreationData} object
	 * @param topographicPoint The {@code TopographicPoint} of the location
	 *        where the {@code Document} has been created
	 * @param timeRange The {@code TimeRange} when the {@code Document} has been
	 *        created
	 * @return The corresponding {@code DocumentID}
	 */
	DocumentID createDocument(UserSession userSession, ExternalAttachmentCreationData externalAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange);

	/**
	 * Creates a new {@link Document} with a platform {@link Attachment} and
	 * returns the {@link UploadInfo} if it was successful. <br/><br/>
	 * 
	 * <i>Important:</i> After the invocation of this method the resource must
	 * be stored by using the {@code JavaClientStoragePlugin} and the returned
	 * {@link UploadInfo} object. <br/><br/>
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param platformAttachmentCreationData The
	 *        {@code PlatformAttachmentCreationData} object
	 * @param topographicPoint The {@code TopographicPoint} of the location
	 *        where the {@code Document} has been created
	 * @param timeRange The {@code TimeRange} when the {@code Document} has been
	 *        created
	 * @return The corresponding {@code UploadInfo}
	 */
	UploadInfo createDocument(UserSession userSession, PlatformAttachmentCreationData platformAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange);

	/**
	 * Creates a new {@link Document} with an external {@link Attachment} and
	 * returns the {@link DocumentID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param uuid A {@code UUID} which will become the
	 *        {@code resource identifier} of the {@code DocumentID}
	 * @param externalAttachmentCreationData The
	 *        {@code ExternalAttachmentCreationData} object
	 * @param topographicPoint The {@code TopographicPoint} of the location
	 *        where the {@code Document} has been created
	 * @param timeRange The {@code TimeRange} when the {@code Document} has been
	 *        created
	 * @return The corresponding {@code DocumentID}
	 */
	DocumentID createDocument(UserSession userSession, UUID uuid, ExternalAttachmentCreationData externalAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange);

	/**
	 * Creates a new {@link Document} with a platform {@link Attachment} and
	 * returns the {@link UploadInfo} if it was successful. <br/><br/>
	 * 
	 * <i>Important:</i> After the invocation of this method the resource must
	 * be stored by using the {@code JavaClientStoragePlugin} and the returned
	 * {@link UploadInfo} object. <br/><br/>
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param uuid A {@code UUID} which will become the
	 *        {@code resource identifier} of the {@code DocumentID}
	 * @param platformAttachmentCreationData The
	 *        {@code PlatformAttachmentCreationData} object
	 * @param topographicPoint The {@code TopographicPoint} of the location
	 *        where the {@code Document} has been created
	 * @param timeRange The {@code TimeRange} when the {@code Document} has been
	 *        created
	 * @return The corresponding {@code UploadInfo}
	 */
	UploadInfo createDocument(UserSession userSession, UUID uuid, PlatformAttachmentCreationData platformAttachmentCreationData, TopographicPoint topographicPoint, TimeRange timeRange);

	/**
	 * Creates a new {@link User} and returns the {@link UserID} if it was
	 * successful.
	 * 
	 * @param emailAddress The Email address of the {@code User}
	 * @param password The password of the {@code User}
	 * @param userName The name of the {@code User}
	 * @return The corresponding {@code UserID}
	 */
	UserID createUser(String emailAddress, String password, String userName);

	/**
	 * Returns a {@link ResultSet} with all {@link Document}s and the mapped
	 * {@link DocumentID}s available in the {@code Cache}. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getAllCachedDocuments();

	/**
	 * Returns a {@link ResultSet} with all available {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> This method is <u>critical</u> in respect of
	 * performance aspects and should therefore only be invoked if it is
	 * absolutely necessary. Note also that for the time being only the meta
	 * data of the {@code Document}s without their sub elements will be loaded.
	 * <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getAllDocuments();

	/**
	 * Returns a {@link ResultSet} with all available {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> This method is <u>critical</u> in respect of
	 * performance aspects and should therefore only be invoked if it is
	 * absolutely necessary. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param loadType The desired {@code LoadType}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getAllDocuments(LoadType loadType);

	/**
	 * Returns a {@link ResultSet} with all available {@link Link}s and the
	 * mapped {@link LinkID}s. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @return A {@code ResultSet} with the {@code Link}s
	 */
	ResultSet<LinkID, Link> getAllLinks();

	/**
	 * Returns a {@link ResultSet} with all available {@link Tag}s and the
	 * mapped {@link TagID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> The returned {@code Tag}s and {@code TagID}s are
	 * <i>anonymous</i>. This implies that the returned {@code Tag}s and
	 * {@code TagID}s are not connected with their parent and root
	 * {@link Entity}s or do not even belong to a parent or root {@code Entity}.
	 * Therefore all method invocations with regards to voting functionalities
	 * or parent and root {@code Entity}s of the {@code Tag}s and {@code TagID}s
	 * will result in an {@link UnsupportedOperationException}. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @return A {@code ResultSet} with the {@code Tag}s
	 */
	ResultSet<TagID, Tag> getAllTags();

	/**
	 * Returns a {@link ResultSet} with all available {@link UserInfo}s and the
	 * mapped {@link UserID}s.
	 * 
	 * @return A {@code ResultSet} with the {@code UserInfo}s
	 */
	ResultSet<UserID, UserInfo> getAllUserInfos();

	/**
	 * Returns the {@link Collection} with the specified {@link CollectionID}.
	 * It will be returned {@code null} if there is no {@code Collection} with
	 * the given {@code CollectionID} or if the {@code Collection} is
	 * {@code private} and the {@link User} of the given {@link UserSession} is
	 * not the creator of the requested {@code Collection}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information and is necessary in case the {@link Collection}
	 * is {@code private}. Note also that for the time being only the meta data
	 * of the {@code Collection} without the corresponding {@link Document}s
	 * will be loaded. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID A {@code CollectionID}
	 * @return The {@code Collection}
	 */
	Collection getCollection(UserSession userSession, CollectionID collectionID);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Collection}s and
	 * the mapped {@link CollectionID}s. But if the given {@link UserID} does
	 * not belong to the {@link User} of the {@link UserSession} only the
	 * {@code public} {@code Collection}s are located within the
	 * {@code ResultSet}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information. Note also that for the time being only the meta
	 * data of the {@code Collection}s without the corresponding
	 * {@link Document}s will be loaded. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param creator The {@code UserID} of the {@code User} who has created the
	 *        requested {@code Collection}s
	 * @return A {@code ResultSet} with the {@code Collection}s
	 */
	ResultSet<CollectionID, Collection> getCollectionsByUser(UserSession userSession, UserID creator);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Collection}s and
	 * the mapped {@link CollectionID}s. But if the given {@link UserID} does
	 * not belong to the {@link User} of the {@link UserSession} only the
	 * {@code public} {@code Collection}s are located within the
	 * {@code ResultSet}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information. Note also that for the time being only the meta
	 * data of the {@link Document}s within the {@code Collection}s without
	 * their sub elements will be loaded. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param creator The {@code UserID} of the {@code User} who has created the
	 *        requested {@code Collection}s
	 * @return A {@code ResultSet} with the {@code Collection}s
	 */
	ResultSet<CollectionID, Collection> getCollectionsWithDocumentsByUser(UserSession userSession, UserID creator);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Collection}s and
	 * the mapped {@link CollectionID}s. But if the given {@link UserID} does
	 * not belong to the {@link User} of the {@link UserSession} only the
	 * {@code public} {@code Collection}s are located within the
	 * {@code ResultSet}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param creator The {@code UserID} of the {@code User} who has created the
	 *        requested {@code Collection}s
	 * @param loadType The desired {@code LoadType} of the {@code Document}s
	 *        within the {@code Collection}s
	 * @return A {@code ResultSet} with the {@code Collection}s
	 */
	ResultSet<CollectionID, Collection> getCollectionsWithDocumentsByUser(UserSession userSession, UserID creator, LoadType loadType);

	/**
	 * Returns the {@link Collection} with the specified {@link CollectionID}.
	 * It will be returned {@code null} if there is no {@code Collection} with
	 * the given {@code CollectionID} or if the {@code Collection} is
	 * {@code private} and the {@link User} of the given {@link UserSession} is
	 * not the creator of the requested {@code Collection}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information and is necessary in case the {@link Collection}
	 * is {@code private}. Note also that for the time being only the meta data
	 * of the {@link Document}s within this {@code Collection} without their sub
	 * elements will be loaded. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID A {@code CollectionID}
	 * @return The {@code Collection}
	 */
	Collection getCollectionWithDocuments(UserSession userSession, CollectionID collectionID);

	/**
	 * Returns the {@link Collection} with the specified {@link CollectionID}.
	 * It will be returned {@code null} if there is no {@code Collection} with
	 * the given {@code CollectionID} or if the {@code Collection} is
	 * {@code private} and the {@link User} of the given {@link UserSession} is
	 * not the creator of the requested {@code Collection}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information and is necessary in case the {@link Collection}
	 * is {@code private}. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID A {@code CollectionID}
	 * @param loadType The desired {@code LoadType} of the {@code Document}s
	 *        within the {@code Collection}
	 * @return The {@code Collection}
	 */
	Collection getCollectionWithDocuments(UserSession userSession, CollectionID collectionID, LoadType loadType);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Comment}s and the
	 * mapped {@link CommentID}s. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param userID The {@code UserID} of the {@code User} who has published
	 *        the requested {@code Comment}s
	 * @return A {@code ResultSet} with the {@code Comment}s
	 */
	ResultSet<CommentID, Comment> getCommentsByUser(UserID userID);

	/**
	 * Returns the {@link Document} with the specified {@link DocumentID} or
	 * {@code null} if there is no {@code Document} with the given
	 * {@code DocumentID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> For the time being only the meta data of the
	 * {@code Document} without its sub elements will be loaded. If the
	 * {@code Cache} is activated and the desired {@code Document} is located in
	 * the {@code Cache}, the {@code Cache} version will be returned without a
	 * direct API call. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param documentID A {@code DocumentID}
	 * @return The {@code Document}
	 */
	Document getDocument(DocumentID documentID);

	/**
	 * Returns the {@link Document} with the specified {@link DocumentID} or
	 * {@code null} if there is no {@code Document} with the given
	 * {@code DocumentID}. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param documentID A {@code DocumentID}
	 * @param loadType The desired {@code LoadType}
	 * @param forceAPICall {@code true} if the {@code Document} should be loaded
	 *        directly from the API; {@code false} if the {@code Document} can
	 *        be loaded from the {@code Cache}
	 * @return The {@code Document}
	 */
	Document getDocument(DocumentID documentID, LoadType loadType, boolean forceAPICall);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> For the time being only the meta data of the
	 * {@code Document}s without their sub elements will be loaded. Note also
	 * that if there are multiple entries for the {@link Position} or the
	 * {@link Time} of the {@code Document}s, the entry with the best
	 * {@link Votes} result will be used. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param tags A {@code List} of tags in {@code String} representation
	 * @param boundingBox A {@code BoundingBox}
	 * @param timeRange A {@code TimeRange}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> If there are multiple entries for the {@link Position}
	 * or the {@link Time} of the {@code Document}s, the entry with the best
	 * {@link Votes} result will be used. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param tags A {@code List} of tags in {@code String} representation
	 * @param boundingBox A {@code BoundingBox}
	 * @param timeRange A {@code TimeRange}
	 * @param loadType The desired {@code LoadType}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, LoadType loadType);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> For the time being only the meta data of the
	 * {@code Document}s without their sub elements will be loaded. Note also
	 * that if there are multiple entries for the {@link Position} or the
	 * {@link Time} of the {@code Document}s, the entry with the best
	 * {@link Votes} result will be used.<br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param tags A {@code List} of tags in {@code String} representation
	 * @param boundingBox A {@code BoundingBox}
	 * @param timeRange A {@code TimeRange}
	 * @param publishingTimeRange A {@code TimeRange} for the publishing
	 *        {@code DateTime}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, TimeRange publishingTimeRange);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> If there are multiple entries for the {@link Position}
	 * or the {@link Time} of the {@code Document}s, the entry with the best
	 * {@link Votes} result will be used. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param tags A {@code List} of tags in {@code String} representation
	 * @param boundingBox A {@code BoundingBox}
	 * @param timeRange A {@code TimeRange}
	 * @param publishingTimeRange A {@code TimeRange} for the publishing
	 *        {@code DateTime}
	 * @param loadType The desired {@code LoadType}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocuments(List<String> tags, BoundingBox boundingBox, TimeRange timeRange, TimeRange publishingTimeRange, LoadType loadType);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> For the time being only the meta data of the
	 * {@code Document}s without their sub elements will be loaded. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param time The {@code DateTime} after which the {@code Document}s should
	 *        be returned
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocumentsAfter(DateTime time);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param time The {@code DateTime} after which the {@code Document}s should
	 *        be returned
	 * @param loadType The desired {@code LoadType}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocumentsAfter(DateTime time, LoadType loadType);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> For the time being only the meta data of the
	 * {@code Document}s without their sub elements will be loaded. If the
	 * {@code Cache} is activated and the desired {@code Document}s are located
	 * in the {@code Cache}, the {@code Cache} versions will be returned without
	 * a direct API call. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param documentIDs A {@code List} of {@code DocumentID}s
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocumentsByIDs(List<DocumentID> documentIDs);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param documentIDs A {@code List} of {@code DocumentID}s
	 * @param loadType The desired {@code LoadType}
	 * @param forceAPICall {@code true} if the {@code Document}s should be
	 *        loaded directly from the API; {@code false} if the
	 *        {@code Document}s can be loaded from the {@code Cache}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocumentsByIDs(List<DocumentID> documentIDs, LoadType loadType, boolean forceAPICall);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> For the time being only the meta data of the
	 * {@code Document}s without their sub elements will be loaded. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param value The value of the {@code Tag}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocumentsByTag(String value);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param value The value of the {@code Tag}
	 * @param loadType The desired {@code LoadType}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocumentsByTag(String value, LoadType loadType);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Annotation:</i> For the time being only the meta data of the
	 * {@code Document}s without their sub elements will be loaded. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param publisher The {@code UserID} of the {@code User} who has published
	 *        the requested {@code Document}s
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocumentsByUser(UserID publisher);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Document}s and the
	 * mapped {@link DocumentID}s. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param publisher The {@code UserID} of the {@code User} who has published
	 *        the requested {@code Document}s
	 * @param loadType The desired {@code LoadType}
	 * @return A {@code ResultSet} with the {@code Document}s
	 */
	ResultSet<DocumentID, Document> getDocumentsByUser(UserID publisher, LoadType loadType);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Link}s and the
	 * mapped {@link LinkID}s. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param time The {@code DateTime} after which the {@code Link}s should be
	 *        returned
	 * @return A {@code ResultSet} with the {@code Link}s
	 */
	ResultSet<LinkID, Link> getLinksAfter(DateTime time);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Link}s and the
	 * mapped {@link LinkID}s. <br/><br/>
	 * 
	 * <i>Important:</i> Do not save the returned data or any part of them in an
	 * own variable, but requery the object, because if a method of the stored
	 * object will be invoked later, the received data could no longer be
	 * current.
	 * 
	 * @param value The value of the {@code Tag}
	 * @return A {@code ResultSet} with the {@code Link}s
	 */
	ResultSet<LinkID, Link> getLinksByTag(String value);

	/**
	 * Returns the {@link Message} with the specified {@link MessageID} or
	 * {@code null} if there is no {@code Message} with the given
	 * {@code MessageID}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@link UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageID A {@code MessageID}
	 * @return The {@code Message}
	 */
	Message getMessage(UserSession userSession, MessageID messageID);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Message}s and the
	 * mapped {@link MessageID}s for the {@link User} of the given
	 * {@link UserSession}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageBoxType The {@code MessageBoxType}
	 * @return A {@code ResultSet} with the {@code Message}s
	 */
	ResultSet<MessageID, Message> getMessages(UserSession userSession, MessageBoxType messageBoxType);

	/**
	 * Returns a {@link ResultSet} with the requested {@link Message}s and the
	 * mapped {@link MessageID}s for the {@link User} of the given
	 * {@link UserSession}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@code UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageBoxType The {@code MessageBoxType}
	 * @param time The {@code DateTime} after which the {@code Message}s should
	 *        be returned
	 * @return A {@code ResultSet} with the {@code Message}s
	 */
	ResultSet<MessageID, Message> getMessagesAfter(UserSession userSession, MessageBoxType messageBoxType, DateTime time);

	/**
	 * Returns a {@link User} object for the given data or {@code null} if there
	 * is no {@code User} with the given data.
	 * 
	 * @param emailAddress The Email address of the {@code User}
	 * @param password The password of the {@code User}
	 * @return A {@code User}
	 */
	User getUser(String emailAddress, String password);

	/**
	 * Returns the {@link UserInfo} for the given {@link UserID} or {@code null}
	 * if there is no {@link User} with the given {@code UserID}.
	 * 
	 * @param userID A {@code UserID}
	 * @return The corresponding {@code UserInfo}
	 */
	UserInfo getUserInfo(UserID userID);

	/**
	 * Returns a {@link ResultSet} with the requested {@link UserInfo}s and the
	 * mapped {@link UserID}s.
	 * 
	 * @param userIDs A {@code List} of {@code UserID}s
	 * @return A {@code ResultSet} with the {@code UserInfo}s
	 */
	ResultSet<UserID, UserInfo> getUserInfosByIDs(List<UserID> userIDs);

	/**
	 * Returns a {@link UserSession} for the given {@link User}.
	 * 
	 * @param user The corresponding {@code User}
	 * @return A {@code UserSession}
	 */
	UserSession getUserSession(User user);

	/**
	 * Determines whether there is a {@link User} with the given data or not.
	 * 
	 * @param emailAddress The Email address of the {@code User}
	 * @return {@code true} if there is a {@code User} for the given data;
	 *         otherwise {@code false}
	 */
	boolean hasUser(String emailAddress);

	/**
	 * Determines whether there is a {@link User} with the given data or not.
	 * 
	 * @param emailAddress The Email address of the {@code User}
	 * @param password The password of the {@code User}
	 * @return {@code true} if there is a {@code User} for the given data;
	 *         otherwise {@code false}
	 */
	boolean hasUser(String emailAddress, String password);

	/**
	 * Determines if the {@code Cache} is activated.
	 * 
	 * @return {@code true} if the {@code Cache} is activated; otherwise
	 *         {@code false}
	 */
	boolean isCacheActivated();

	/**
	 * Removes the {@link Collection} with the specified {@link CollectionID} if
	 * the {@link User} of the given {@link UserSession} is the creator of the
	 * {@code Collection}. <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@link UserSession} is used to acquire the
	 * {@code User} information.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID A {@code CollectionID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeCollection(UserSession userSession, CollectionID collectionID);

	/**
	 * Removes the {@link Message} with the specified {@link MessageID}.
	 * <br/><br/>
	 * 
	 * <i>Annotation:</i> The {@link UserSession} is used to acquire the
	 * {@code User} information. <br/><br/>
	 * 
	 * <i>Important:</i> This method invocation will not remove the
	 * {@code Message} copy in the {@code Message} box of the counterpart.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageID A {@code MessageID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeMessage(UserSession userSession, MessageID messageID);

	/**
	 * Sends a {@link Message} to the {@link User} of the given {@link UserID}
	 * and returns the {@link MessageID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param receiver The {@code UserID} of the receiver of the {@code Message}
	 * @param title The title of the {@code Message}
	 * @param content The content of the {@code Message}
	 * @return The corresponding {@code MessageID}
	 */
	MessageID sendMessage(UserSession userSession, UserID receiver, String title, String content);
}
