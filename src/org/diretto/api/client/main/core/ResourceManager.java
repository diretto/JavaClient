package org.diretto.api.client.main.core;

import java.net.URL;
import java.util.UUID;

import org.diretto.api.client.base.data.PlatformMediaType;
import org.diretto.api.client.base.data.TimeRange;
import org.diretto.api.client.base.data.TopographicPoint;
import org.diretto.api.client.base.data.UploadInfo;
import org.diretto.api.client.base.entities.Entity;
import org.diretto.api.client.base.entities.EntityID;
import org.diretto.api.client.base.entities.SubEntityID;
import org.diretto.api.client.base.exceptions.NoResultsException;
import org.diretto.api.client.base.types.MessageBoxType;
import org.diretto.api.client.base.types.VoteType;
import org.diretto.api.client.main.core.binding.base.QueryResultPageResource;
import org.diretto.api.client.main.core.binding.base.ResultPageResource;
import org.diretto.api.client.main.core.binding.major.AnonymousTagResultPageResource;
import org.diretto.api.client.main.core.binding.major.BaseTagResource;
import org.diretto.api.client.main.core.binding.major.CollectionReceptionResource;
import org.diretto.api.client.main.core.binding.major.CommentResultPageResource;
import org.diretto.api.client.main.core.binding.major.DocumentFullResource;
import org.diretto.api.client.main.core.binding.major.DocumentMetaDataResource;
import org.diretto.api.client.main.core.binding.major.DocumentQueryResultPageResource;
import org.diretto.api.client.main.core.binding.major.DocumentResultPageResource;
import org.diretto.api.client.main.core.binding.major.DocumentSnapShotResource;
import org.diretto.api.client.main.core.binding.major.LinkResultPageResource;
import org.diretto.api.client.main.core.binding.major.MessageReceptionResource;
import org.diretto.api.client.main.core.binding.major.MessageResultPageResource;
import org.diretto.api.client.main.core.binding.major.MultipleCollectionsResource;
import org.diretto.api.client.main.core.binding.major.MultipleFullDocumentsResource;
import org.diretto.api.client.main.core.binding.major.MultipleMetaDataDocumentsResource;
import org.diretto.api.client.main.core.binding.major.MultipleSnapShotDocumentsResource;
import org.diretto.api.client.main.core.binding.major.MultipleTagsResource;
import org.diretto.api.client.main.core.binding.major.MultipleUserInfosResource;
import org.diretto.api.client.main.core.binding.major.SupportedMediaTypesResource;
import org.diretto.api.client.main.core.binding.major.UploadInfoResource;
import org.diretto.api.client.main.core.binding.major.UserInfoResource;
import org.diretto.api.client.main.core.binding.major.UserInfoResultPageResource;
import org.diretto.api.client.main.core.binding.major.UserVoteResource;
import org.diretto.api.client.main.core.binding.post.CollectionCreationResource;
import org.diretto.api.client.main.core.binding.post.CommentCreationResource;
import org.diretto.api.client.main.core.binding.post.DispatchDocumentQueryResource;
import org.diretto.api.client.main.core.binding.post.DocumentHyperLinkResource;
import org.diretto.api.client.main.core.binding.post.ExternalAttachmentCreationResource;
import org.diretto.api.client.main.core.binding.post.ExternalDocumentCreationResource;
import org.diretto.api.client.main.core.binding.post.LinkCreationResource;
import org.diretto.api.client.main.core.binding.post.MessageCreationResource;
import org.diretto.api.client.main.core.binding.post.MultipleDocumentsRequestResource;
import org.diretto.api.client.main.core.binding.post.MultipleUsersRequestResource;
import org.diretto.api.client.main.core.binding.post.MultipleValuesRequestResource;
import org.diretto.api.client.main.core.binding.post.PlatformAttachmentCreationResource;
import org.diretto.api.client.main.core.binding.post.PlatformDocumentCreationResource;
import org.diretto.api.client.main.core.binding.post.TagCreationResource;
import org.diretto.api.client.main.core.binding.post.UserCreationResource;
import org.diretto.api.client.main.core.binding.post.ValueRequestResource;
import org.diretto.api.client.main.core.entities.Attachment;
import org.diretto.api.client.main.core.entities.AttachmentID;
import org.diretto.api.client.main.core.entities.Collection;
import org.diretto.api.client.main.core.entities.CollectionID;
import org.diretto.api.client.main.core.entities.Comment;
import org.diretto.api.client.main.core.entities.CommentID;
import org.diretto.api.client.main.core.entities.CoreServiceEntityIDFactory;
import org.diretto.api.client.main.core.entities.Document;
import org.diretto.api.client.main.core.entities.DocumentID;
import org.diretto.api.client.main.core.entities.KeyValue;
import org.diretto.api.client.main.core.entities.KeyValueID;
import org.diretto.api.client.main.core.entities.Link;
import org.diretto.api.client.main.core.entities.LinkID;
import org.diretto.api.client.main.core.entities.Message;
import org.diretto.api.client.main.core.entities.MessageID;
import org.diretto.api.client.main.core.entities.Position;
import org.diretto.api.client.main.core.entities.PositionID;
import org.diretto.api.client.main.core.entities.Tag;
import org.diretto.api.client.main.core.entities.TagID;
import org.diretto.api.client.main.core.entities.Time;
import org.diretto.api.client.main.core.entities.TimeID;
import org.diretto.api.client.session.Session;
import org.diretto.api.client.session.SystemSession;
import org.diretto.api.client.session.UserSession;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserFactory;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;
import org.restlet.Client;
import org.restlet.data.Reference;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

/**
 * The {@code ResourceManager} is a manager class and responsible for the
 * communication with the API. For the simplification of the communication
 * <i>Restlet</i> (RESTful web framework for Java) is used.
 * 
 * @author Tobias Schlecht
 */
final class ResourceManager
{
	private final URL apiBaseURL;
	private final Client restletClient;
	private final SystemSession systemSession;

	/**
	 * The constructor is {@code private} to have strict control what instances
	 * exist at any time. Instead of the constructor the {@code public}
	 * <i>static factory method</i> {@link #getInstance(DataManagerImpl)}
	 * returns the instances of the class.
	 * 
	 * @param dataManager The corresponding {@code DataManager}
	 */
	private ResourceManager(DataManagerImpl dataManager)
	{
		apiBaseURL = dataManager.getAPIBaseURL();
		restletClient = dataManager.getResletClient();
		systemSession = dataManager.getSystemSession();
	}

	/**
	 * Returns a {@link ResourceManager} instance for the corresponding
	 * {@link DataManager}.
	 * 
	 * @param dataManager The corresponding {@code DataManager}
	 * @return A {@code ResourceManager} instance
	 */
	static synchronized ResourceManager getInstance(DataManagerImpl dataManager)
	{
		return new ResourceManager(dataManager);
	}

	/**
	 * Adds a new alternative {@link Position} to a {@link Document} and returns
	 * the {@link PositionID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param topographicPoint The {@code TopographicPoint} of the
	 *        {@code Position}
	 * @return The corresponding {@code PositionID}
	 */
	PositionID addAlternativePositionToDocument(UserSession userSession, DocumentID documentID, TopographicPoint topographicPoint)
	{
		ClientResource clientResource = createClientResource(userSession, documentID.getUniqueResourceURL(), "location", (float) topographicPoint.getLatitude() + "," + (float) topographicPoint.getLongitude() + "," + topographicPoint.getVariance());

		try
		{
			clientResource.put(null);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getPositionIDInstance(clientResource.getResponse().getLocationRef().toUrl(), documentID, documentID);
	}

	/**
	 * Adds a new alternative {@link Time} to a {@link Document} and returns the
	 * {@link TimeID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param timeRange The creation {@code TimeRange} of the {@code Document}
	 * @return The corresponding {@code TimeID}
	 */
	TimeID addAlternativeTimeToDocument(UserSession userSession, DocumentID documentID, TimeRange timeRange)
	{
		ClientResource clientResource = createClientResource(userSession, documentID.getUniqueResourceURL(), "time", timeRange.getStartDateTime().toString(Time.ISO_UTC_DATE_TIME_FORMATTER) + "--" + timeRange.getEndDateTime().toString(Time.ISO_UTC_DATE_TIME_FORMATTER));

		try
		{
			clientResource.put(null);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getTimeIDInstance(clientResource.getResponse().getLocationRef().toUrl(), documentID, documentID);
	}

	/**
	 * Adds a new {@link Comment} to a {@link Document} and returns the
	 * {@link CommentID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param commentCreationResource The {@code CommentCreationResource}
	 * @return The corresponding {@code CommentID}
	 */
	CommentID addCommentToDocument(UserSession userSession, DocumentID documentID, CommentCreationResource commentCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, documentID.getUniqueResourceURL(), "comments");

		try
		{
			clientResource.post(commentCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getCommentIDInstance(clientResource.getResponse().getLocationRef().toUrl(), documentID, documentID);
	}

	/**
	 * Adds a {@link Document} to a {@link Collection}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID The corresponding {@code CollectionID}
	 * @param documentHyperLinkResource The {@code DocumentHyperLinkResource}
	 * @return {@code true} if the addition was successful; otherwise
	 *         {@code false}
	 */
	boolean addDocumentToCollection(UserSession userSession, CollectionID collectionID, DocumentHyperLinkResource documentHyperLinkResource)
	{
		ClientResource clientResource = createClientResource(userSession, collectionID.getUniqueResourceURL(), "documents");

		try
		{
			clientResource.post(documentHyperLinkResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Adds a new external {@link Attachment} to a {@link Document} and returns
	 * the {@link AttachmentID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param externalAttachmentCreationResource The
	 *        {@code ExternalAttachmentCreationResource}
	 * @return The corresponding {@code AttachmentID}
	 */
	AttachmentID addExternalAttachmentToDocument(UserSession userSession, DocumentID documentID, ExternalAttachmentCreationResource externalAttachmentCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, documentID.getUniqueResourceURL(), "attachments");

		try
		{
			clientResource.post(externalAttachmentCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getAttachmentIDInstance(clientResource.getResponse().getLocationRef().toUrl(), documentID, documentID);
	}

	/**
	 * Adds a new {@link KeyValue} to a {@link Document} and returns the
	 * {@link KeyValueID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param key The key of the {@code KeyValue}
	 * @param valueRequestResource The {@code ValueRequestResource}
	 * @return The corresponding {@code KeyValueID}
	 */
	KeyValueID addKeyValueToDocument(UserSession userSession, DocumentID documentID, String key, ValueRequestResource valueRequestResource)
	{
		ClientResource clientResource = createClientResource(userSession, documentID.getUniqueResourceURL(), "value", userSession.getUser().getAuthID(), key);

		try
		{
			clientResource.put(valueRequestResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getKeyValueIDInstance(clientResource.getResponse().getLocationRef().toUrl(), documentID, documentID);
	}

	/**
	 * Adds a new {@link Link} between a source {@link Document} and a
	 * destination {@code Document} and returns the {@link LinkID} if it was
	 * successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param sourceDocumentID The {@code DocumentID} of the source
	 *        {@code Document}
	 * @param linkCreationResource The {@code LinkCreationResource}
	 * @return The corresponding {@code LinkID}
	 */
	LinkID addLinkBetweenDocuments(UserSession userSession, DocumentID sourceDocumentID, LinkCreationResource linkCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, "links");

		try
		{
			clientResource.post(linkCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getLinkIDInstance(clientResource.getResponse().getLocationRef().toUrl(), sourceDocumentID, sourceDocumentID);
	}

	/**
	 * Adds a new platform {@link Attachment} to a {@link Document} and returns
	 * the {@link UploadInfo} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param documentID The corresponding {@code DocumentID}
	 * @param platformMediaType The corresponding {@code PlatformMediaType}
	 * @param platformAttachmentCreationResource The
	 *        {@code PlatformAttachmentCreationResource}
	 * @return The corresponding {@code UploadInfo}
	 */
	UploadInfo addPlatformAttachmentToDocument(UserSession userSession, DocumentID documentID, PlatformMediaType platformMediaType, PlatformAttachmentCreationResource platformAttachmentCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, documentID.getUniqueResourceURL(), "attachments");

		UploadInfoResource uploadInfoResource;

		try
		{
			uploadInfoResource = clientResource.post(platformAttachmentCreationResource, UploadInfoResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		UploadInfoResource.UploadResource uploadResource = uploadInfoResource.getUpload();

		long fileSize = platformAttachmentCreationResource.getFileSize();
		AttachmentID attachmentID = CoreServiceEntityIDFactory.getAttachmentIDInstance(clientResource.getResponse().getLocationRef().toUrl(), documentID, documentID);
		String token = uploadResource.getToken();
		String fileURL = uploadResource.getLocation().getLink().getHref();
		String target = uploadResource.getTarget().getLink().getHref();

		return new UploadInfo(fileSize, platformMediaType, attachmentID, token, fileURL, target);
	}

	/**
	 * Adds a new {@link Tag} to an {@link Entity} and returns the {@link TagID}
	 * if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param entityID The corresponding {@code EntityID}
	 * @param tagCreationResource The {@code TagCreationResource}
	 * @return The corresponding {@code TagID}
	 */
	TagID addTagToEntity(UserSession userSession, EntityID entityID, TagCreationResource tagCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, "tags");

		BaseTagResource baseTagResource;

		try
		{
			baseTagResource = clientResource.post(tagCreationResource, BaseTagResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		clientResource = createClientResource(userSession, entityID.getUniqueResourceURL(), "tags");

		try
		{
			clientResource.post(baseTagResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		if(entityID instanceof SubEntityID<?, ?>)
		{
			@SuppressWarnings("unchecked")
			SubEntityID<DocumentID, ?> subEntityID = (SubEntityID<DocumentID, ?>) entityID;

			return CoreServiceEntityIDFactory.getTagIDInstance(clientResource.getResponse().getLocationRef().toUrl(), subEntityID.getRootID(), subEntityID);
		}
		else
		{
			return CoreServiceEntityIDFactory.getTagIDInstance(clientResource.getResponse().getLocationRef().toUrl(), entityID, entityID);
		}
	}

	/**
	 * Changes the meta data of the {@link Collection} with the given
	 * {@link CollectionID}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID The corresponding {@code CollectionID}
	 * @param collectionCreationResource The {@code CollectionCreationResource}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	boolean changeCollection(UserSession userSession, CollectionID collectionID, CollectionCreationResource collectionCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, collectionID.getUniqueResourceURL());

		try
		{
			clientResource.put(collectionCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Changes the {@link User} data of the {@code User}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param userCreationResource The {@code UserCreationResource}
	 * @return {@code true} if the change was successful; otherwise
	 *         {@code false}
	 */
	boolean changeUserData(UserSession userSession, UserCreationResource userCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, userSession.getUser().getID().getUniqueResourceURL());

		try
		{
			clientResource.put(userCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Creates a new {@link Collection} and returns the {@link CollectionID} if
	 * it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionCreationResource The {@code CollectionCreationResource}
	 * @return The corresponding {@code CollectionID}
	 */
	CollectionID createCollection(UserSession userSession, CollectionCreationResource collectionCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, userSession.getUser().getID().getUniqueResourceURL(), "collections");

		try
		{
			clientResource.post(collectionCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getCollectionIDInstance(clientResource.getResponse().getLocationRef().toUrl());
	}

	/**
	 * Creates a new {@link Document} with an external {@link Attachment} and
	 * returns the {@link DocumentID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param uuid A {@code UUID} which will become the
	 *        {@code resource identifier} of the {@code DocumentID}
	 * @param externalDocumentCreationResource The
	 *        {@code ExternalDocumentCreationResource}
	 * @return The corresponding {@code DocumentID}
	 */
	DocumentID createDocument(UserSession userSession, UUID uuid, ExternalDocumentCreationResource externalDocumentCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, "document", uuid.toString());

		try
		{
			clientResource.put(externalDocumentCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getDocumentIDInstance(clientResource.getResponse().getLocationRef().toUrl());
	}

	/**
	 * Creates a new {@link Document} with a platform {@link Attachment} and
	 * returns the {@link UploadInfo} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param uuid A {@code UUID} which will become the
	 *        {@code resource identifier} of the {@code DocumentID}
	 * @param platformMediaType The corresponding {@code PlatformMediaType}
	 * @param platformDocumentCreationResource The
	 *        {@code PlatformDocumentCreationResource}
	 * @return The corresponding {@code UploadInfo}
	 */
	UploadInfo createDocument(UserSession userSession, UUID uuid, PlatformMediaType platformMediaType, PlatformDocumentCreationResource platformDocumentCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, "document", uuid.toString());

		UploadInfoResource uploadInfoResource;

		try
		{
			uploadInfoResource = clientResource.put(platformDocumentCreationResource, UploadInfoResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		UploadInfoResource.UploadResource uploadResource = uploadInfoResource.getUpload();

		long fileSize = platformDocumentCreationResource.getFileSize();
		DocumentID documentID = CoreServiceEntityIDFactory.getDocumentIDInstance(clientResource.getResponse().getLocationRef().toUrl());
		AttachmentID attachmentID = CoreServiceEntityIDFactory.getAttachmentIDInstance(documentID.getUniqueResourceURL().toExternalForm() + "/attachment/" + documentID.getResourceIdentifier(), documentID, documentID);
		String token = uploadResource.getToken();
		String fileURL = uploadResource.getLocation().getLink().getHref();
		String target = uploadResource.getTarget().getLink().getHref();

		return new UploadInfo(fileSize, platformMediaType, attachmentID, token, fileURL, target);
	}

	/**
	 * Creates a new {@link User} and returns the {@link UserID} if it was
	 * successful.
	 * 
	 * @param userCreationResource The {@code UserCreationResource}
	 * @return The corresponding {@code UserID}
	 */
	UserID createUser(UserCreationResource userCreationResource)
	{
		ClientResource clientResource = createClientResource((UserSession) null, "users");

		try
		{
			clientResource.post(userCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return UserFactory.getUserIDInstance(clientResource.getResponse().getLocationRef().toUrl());
	}

	/**
	 * Executes a query with the given {@link DispatchDocumentQueryResource} and
	 * returns the corresponding {@link DocumentQueryResultPageResource}.
	 * 
	 * @param dispatchDocumentQueryResource The
	 *        {@code DispatchDocumentQueryResource}
	 * @return The {@code DocumentQueryResultPageResource}
	 * @throws NoResultsException
	 */
	DocumentQueryResultPageResource executeQuery(DispatchDocumentQueryResource dispatchDocumentQueryResource) throws NoResultsException
	{
		ClientResource clientResource = createClientResource("query");

		try
		{
			clientResource.post(dispatchDocumentQueryResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return getQueryResultPageResource(DocumentQueryResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link DocumentResultPageResource}.
	 * 
	 * @return The {@code DocumentResultPageResource}
	 * @throws NoResultsException
	 */
	DocumentResultPageResource getAllDocuments() throws NoResultsException
	{
		ClientResource clientResource = createClientResource("documents");

		return getResultPageResource(DocumentResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link LinkResultPageResource}.
	 * 
	 * @return The {@code LinkResultPageResource}
	 * @throws NoResultsException
	 */
	LinkResultPageResource getAllLinks() throws NoResultsException
	{
		ClientResource clientResource = createClientResource("links");

		return getResultPageResource(LinkResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link AnonymousTagResultPageResource}.
	 * 
	 * @return The {@code AnonymousTagResultPageResource}
	 * @throws NoResultsException
	 */
	AnonymousTagResultPageResource getAllTags() throws NoResultsException
	{
		ClientResource clientResource = createClientResource("tags");

		return getResultPageResource(AnonymousTagResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link UserInfoResultPageResource}.
	 * 
	 * @return The {@code UserInfoResultPageResource}
	 * @throws NoResultsException
	 */
	UserInfoResultPageResource getAllUserInfos() throws NoResultsException
	{
		ClientResource clientResource = createClientResource("users");

		return getResultPageResource(UserInfoResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link CollectionReceptionResource}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID A {@code CollectionID}
	 * @return The {@code CollectionReceptionResource}
	 */
	CollectionReceptionResource getCollection(UserSession userSession, CollectionID collectionID)
	{
		ClientResource clientResource = createClientResource(userSession, collectionID.getUniqueResourceURL());

		try
		{
			return clientResource.get(CollectionReceptionResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link MultipleCollectionsResource}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param creator The {@code UserID} of the {@code User} who has created the
	 *        requested {@code Collection}s
	 * @return The {@code MultipleCollectionsResource}
	 */
	MultipleCollectionsResource getCollectionsByUser(UserSession userSession, UserID creator)
	{
		ClientResource clientResource = createClientResource(userSession, creator.getUniqueResourceURL(), "collections");

		try
		{
			return clientResource.get(MultipleCollectionsResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link CommentResultPageResource}.
	 * 
	 * @param userID The {@code UserID} of the {@code User} who has published
	 *        the requested {@code Comment}s
	 * @return The {@code CommentResultPageResource}
	 * @throws NoResultsException
	 */
	CommentResultPageResource getCommentsByUser(UserID userID) throws NoResultsException
	{
		ClientResource clientResource = createClientResource(userID.getUniqueResourceURL(), "comments");

		return getResultPageResource(CommentResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link DocumentResultPageResource}.
	 * 
	 * @param time The {@code DateTime} after which the {@code Document}s should
	 *        be returned
	 * @return The {@code DocumentResultPageResource}
	 * @throws NoResultsException
	 */
	DocumentResultPageResource getDocumentsAfter(DateTime time) throws NoResultsException
	{
		ClientResource clientResource = createClientResource("documents", "since", time.toString(Time.ISO_UTC_DATE_TIME_FORMATTER));

		return getResultPageResource(DocumentResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link DocumentResultPageResource}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID A {@code CollectionID}
	 * @return The {@code DocumentResultPageResource}
	 * @throws NoResultsException
	 */
	DocumentResultPageResource getDocumentsByCollection(UserSession userSession, CollectionID collectionID) throws NoResultsException
	{
		ClientResource clientResource = createClientResource(userSession, collectionID.getUniqueResourceURL(), "documents");

		return getResultPageResource(DocumentResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link DocumentResultPageResource}.
	 * 
	 * @param tagCreationResource The {@code TagCreationResource}
	 * @return The {@code DocumentResultPageResource}
	 * @throws NoResultsException
	 */
	DocumentResultPageResource getDocumentsByTag(TagCreationResource tagCreationResource) throws NoResultsException
	{
		TagID tagID = getAnonymousTagID(tagCreationResource);

		ClientResource clientResource = createClientResource(tagID.getUniqueResourceURL(), "documents");

		return getResultPageResource(DocumentResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link DocumentResultPageResource}.
	 * 
	 * @param userID The {@code UserID} of the {@code User} who has published
	 *        the requested {@code Document}s
	 * @return The {@code DocumentResultPageResource}
	 * @throws NoResultsException
	 */
	DocumentResultPageResource getDocumentsByUser(UserID userID) throws NoResultsException
	{
		ClientResource clientResource = createClientResource(userID.getUniqueResourceURL(), "documents");

		return getResultPageResource(DocumentResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link DocumentFullResource}.
	 * 
	 * @param documentID A {@code DocumentID}
	 * @return The {@code DocumentFullResource}
	 */
	DocumentFullResource getFullDocument(DocumentID documentID)
	{
		ClientResource clientResource = createClientResource(documentID.getUniqueResourceURL(), "full");

		try
		{
			return clientResource.get(DocumentFullResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link LinkResultPageResource}.
	 * 
	 * @param time The {@code DateTime} after which the {@code Link}s should be
	 *        returned
	 * @return The {@code LinkResultPageResource}
	 * @throws NoResultsException
	 */
	LinkResultPageResource getLinksAfter(DateTime time) throws NoResultsException
	{
		ClientResource clientResource = createClientResource("links", "since", time.toString(Time.ISO_UTC_DATE_TIME_FORMATTER));

		return getResultPageResource(LinkResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link LinkResultPageResource}.
	 * 
	 * @param tagCreationResource The {@code TagCreationResource}
	 * @return The {@code LinkResultPageResource}
	 * @throws NoResultsException
	 */
	LinkResultPageResource getLinksByTag(TagCreationResource tagCreationResource) throws NoResultsException
	{
		TagID tagID = getAnonymousTagID(tagCreationResource);

		ClientResource clientResource = createClientResource(tagID.getUniqueResourceURL(), "links");

		return getResultPageResource(LinkResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link MessageReceptionResource}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageID A {@code MessageID}
	 * @return The {@code MessageReceptionResource}
	 */
	MessageReceptionResource getMessage(UserSession userSession, MessageID messageID)
	{
		ClientResource clientResource = createClientResource(userSession, messageID.getUniqueResourceURL());

		try
		{
			return clientResource.get(MessageReceptionResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link MessageResultPageResource}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageBoxType The {@code MessageBoxType}
	 * @return The {@code MessageResultPageResource}
	 * @throws NoResultsException
	 */
	MessageResultPageResource getMessages(UserSession userSession, MessageBoxType messageBoxType) throws NoResultsException
	{
		ClientResource clientResource = createClientResource(userSession, userSession.getUser().getID().getUniqueResourceURL(), messageBoxType.getURLParameter());

		return getResultPageResource(MessageResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link MessageResultPageResource}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageBoxType The {@code MessageBoxType}
	 * @param time The {@code DateTime} after which the {@code Message}s should
	 *        be returned
	 * @return The {@code MessageResultPageResource}
	 * @throws NoResultsException
	 */
	MessageResultPageResource getMessagesAfter(UserSession userSession, MessageBoxType messageBoxType, DateTime time) throws NoResultsException
	{
		ClientResource clientResource = createClientResource(userSession, userSession.getUser().getID().getUniqueResourceURL(), messageBoxType.getURLParameter(), "messages", "since", time.toString(Time.ISO_UTC_DATE_TIME_FORMATTER));

		return getResultPageResource(MessageResultPageResource.class, clientResource);
	}

	/**
	 * Returns the corresponding {@link DocumentMetaDataResource}.
	 * 
	 * @param documentID A {@code DocumentID}
	 * @return The {@code DocumentMetaDataResource}
	 */
	DocumentMetaDataResource getMetaDataDocument(DocumentID documentID)
	{
		ClientResource clientResource = createClientResource(documentID.getUniqueResourceURL());

		try
		{
			return clientResource.get(DocumentMetaDataResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link MultipleFullDocumentsResource}.
	 * 
	 * @param multipleDocumentsRequestResource The
	 *        {@code MultipleDocumentsRequestResource}
	 * @return The {@code MultipleFullDocumentsResource}
	 */
	MultipleFullDocumentsResource getMultipleFullDocuments(MultipleDocumentsRequestResource multipleDocumentsRequestResource)
	{
		ClientResource clientResource = createClientResource("documents", "full");

		try
		{
			return clientResource.post(multipleDocumentsRequestResource, MultipleFullDocumentsResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link MultipleMetaDataDocumentsResource}.
	 * 
	 * @param multipleDocumentsRequestResource The
	 *        {@code MultipleDocumentsRequestResource}
	 * @return The {@code MultipleMetaDataDocumentsResource}
	 */
	MultipleMetaDataDocumentsResource getMultipleMetaDataDocuments(MultipleDocumentsRequestResource multipleDocumentsRequestResource)
	{
		ClientResource clientResource = createClientResource("documents", "multiple");

		try
		{
			return clientResource.post(multipleDocumentsRequestResource, MultipleMetaDataDocumentsResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link MultipleSnapShotDocumentsResource}.
	 * 
	 * @param multipleDocumentsRequestResource The
	 *        {@code MultipleDocumentsRequestResource}
	 * @return The {@code MultipleSnapShotDocumentsResource}
	 */
	MultipleSnapShotDocumentsResource getMultipleSnapShotDocuments(MultipleDocumentsRequestResource multipleDocumentsRequestResource)
	{
		ClientResource clientResource = createClientResource("documents", "snapshot");

		try
		{
			return clientResource.post(multipleDocumentsRequestResource, MultipleSnapShotDocumentsResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link MultipleTagsResource}.
	 * 
	 * @param multipleValuesRequestResource The
	 *        {@code MultipleValuesRequestResource}
	 * @return The {@code MultipleTagsResource}
	 */
	MultipleTagsResource getMultipleTags(MultipleValuesRequestResource multipleValuesRequestResource)
	{
		ClientResource clientResource = createClientResource("tags", "multiple");

		try
		{
			return clientResource.post(multipleValuesRequestResource, MultipleTagsResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link MultipleUserInfosResource}.
	 * 
	 * @param multipleUsersRequestResource The
	 *        {@code MultipleUsersRequestResource}
	 * @return The {@code MultipleUserInfosResource}
	 */
	MultipleUserInfosResource getMultipleUserInfos(MultipleUsersRequestResource multipleUsersRequestResource)
	{
		ClientResource clientResource = createClientResource("users", "multiple");

		try
		{
			return clientResource.post(multipleUsersRequestResource, MultipleUserInfosResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the {@link QueryResultPageResource} for the given query result
	 * page {@link URL}.
	 * 
	 * @param queryResultPageResourceClass The exact class of the
	 *        {@code QueryResultPageResource}
	 * @param pageURL The {@code URL} of the query result page
	 * @return The {@code QueryResultPageResource}
	 */
	<T extends QueryResultPageResource> T getQueryResultPageResource(Class<T> queryResultPageResourceClass, URL pageURL)
	{
		ClientResource clientResource = createClientResource(new Reference(pageURL));

		try
		{
			return clientResource.get(queryResultPageResourceClass);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the {@link ResultPageResource} for the given result page
	 * {@link URL}.
	 * 
	 * @param resultPageResourceClass The exact class of the
	 *        {@code ResultPageResource}
	 * @param pageURL The {@code URL} of the result page
	 * @return The {@code ResultPageResource}
	 */
	<T extends ResultPageResource> T getResultPage(Class<T> resultPageResourceClass, URL pageURL)
	{
		ClientResource clientResource = createClientResource(new Reference(pageURL));

		try
		{
			return clientResource.get(resultPageResourceClass);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link DocumentSnapShotResource}.
	 * 
	 * @param documentID A {@code DocumentID}
	 * @return The {@code DocumentSnapShotResource}
	 */
	DocumentSnapShotResource getSnapShotDocument(DocumentID documentID)
	{
		ClientResource clientResource = createClientResource(documentID.getUniqueResourceURL(), "snapshot");

		try
		{
			return clientResource.get(DocumentSnapShotResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link SupportedMediaTypesResource}.
	 * 
	 * @return The {@code SupportedMediaTypesResource}
	 */
	SupportedMediaTypesResource getSupportedMediaTypes()
	{
		ClientResource clientResource = createClientResource("service", "mediatypes");

		try
		{
			return clientResource.get(SupportedMediaTypesResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link UserInfoResource}.
	 * 
	 * @param userID A {@code UserID}
	 * @return The {@code UserInfoResource}
	 */
	UserInfoResource getUserInfo(UserID userID)
	{
		ClientResource clientResource = createClientResource(userID.getUniqueResourceURL());

		try
		{
			return clientResource.get(UserInfoResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the corresponding {@link UserInfoResource}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @return The {@code UserInfoResource}
	 */
	UserInfoResource getUserInfo(UserSession userSession)
	{
		ClientResource clientResource = createClientResource(userSession, userSession.getUser().getID().getUniqueResourceURL());

		try
		{
			return clientResource.get(UserInfoResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Returns the current {@link VoteType} of the {@link User}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param entityID An {@code EntityID}
	 * @return The {@code UserVoteResource}
	 */
	UserVoteResource getUserVote(UserSession userSession, EntityID entityID)
	{
		ClientResource clientResource = createClientResource(userSession, entityID.getUniqueResourceURL(), "vote", "user", userSession.getUser().getAuthID());

		try
		{
			return clientResource.get(UserVoteResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Removes the {@link Collection} with the specified {@link CollectionID} if
	 * the {@link User} of the given {@link UserSession} is the creator of the
	 * {@code Collection}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID A {@code CollectionID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeCollection(UserSession userSession, CollectionID collectionID)
	{
		ClientResource clientResource = createClientResource(userSession, collectionID.getUniqueResourceURL());

		try
		{
			clientResource.delete();
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Removes the {@link Document} with the specified {@link DocumentID} from
	 * the {@link Collection} with the specified {@link CollectionID} if the
	 * {@code Collection} contains the {@code Document}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param collectionID The corresponding {@code CollectionID}
	 * @param documentID A {@code DocumentID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeDocumentFromCollection(UserSession userSession, CollectionID collectionID, DocumentID documentID)
	{
		ClientResource clientResource = createClientResource(userSession, userSession.getUser().getID().getUniqueResourceURL(), "collection", collectionID.getResourceIdentifier(), "document", documentID.getResourceIdentifier());

		try
		{
			clientResource.delete();
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Removes the {@link KeyValue} with the specified {@link KeyValueID} from
	 * the {@link Document} if the {@link User} of the given {@link UserSession}
	 * has created the {@code KeyValue}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param keyValueID A {@code KeyValueID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeKeyValueFromDocument(UserSession userSession, KeyValueID keyValueID)
	{
		ClientResource clientResource = createClientResource(userSession, keyValueID.getUniqueResourceURL());

		try
		{
			clientResource.delete();
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Removes the {@link Message} with the specified {@link MessageID}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param messageID A {@code MessageID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeMessage(UserSession userSession, MessageID messageID)
	{
		ClientResource clientResource = createClientResource(userSession, messageID.getUniqueResourceURL());

		try
		{
			clientResource.delete();
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Removes the vote of the {@link User} if the {@code User} has been voted
	 * before.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param entityID An {@code EntityID}
	 * @return {@code true} if the removal was successful; otherwise
	 *         {@code false}
	 */
	boolean removeUserVote(UserSession userSession, EntityID entityID)
	{
		ClientResource clientResource = createClientResource(userSession, entityID.getUniqueResourceURL(), "vote", "user", userSession.getUser().getAuthID());

		try
		{
			clientResource.delete();
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Sends a {@link Message} to the {@link User} of the given {@link UserID}
	 * and returns the {@link MessageID} if it was successful.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param receiver The {@code UserID} of the receiver of the {@code Message}
	 * @param messageCreationResource The {@code MessageCreationResource}
	 * @return The corresponding {@code MessageID}
	 */
	MessageID sendMessage(UserSession userSession, UserID receiver, MessageCreationResource messageCreationResource)
	{
		ClientResource clientResource = createClientResource(userSession, receiver.getUniqueResourceURL(), "inbox", "messages");

		try
		{
			clientResource.post(messageCreationResource);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getMessageIDInstance(clientResource.getResponse().getLocationRef().toUrl());
	}

	/**
	 * Sets the given {@link VoteType} of the {@link User}. If the {@code User}
	 * has already been voted with another {@code VoteType}, the
	 * {@code VoteType} will be changed.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param voteType The {@code VoteType} of the {@code User}
	 * @param entityID An {@code EntityID}
	 * @return {@code true} if the voting was successful; otherwise
	 *         {@code false}
	 */
	boolean setUserVote(UserSession userSession, VoteType voteType, EntityID entityID)
	{
		ClientResource clientResource = createClientResource(userSession, entityID.getUniqueResourceURL(), "vote", "user", userSession.getUser().getAuthID(), voteType.getURLParameter());

		try
		{
			clientResource.put(null);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return false;
		}

		return true;
	}

	/**
	 * Builds a {@code String} representation of the target {@code URL} with the
	 * specified target {@code URL} parts. <br/><br/>
	 * 
	 * <i>Annotation:</i> The parts are preceded by the given
	 * {@code resourceURL}. If the {@code resourceURL} is {@code null}, the
	 * parts are preceded by the API base {@code URL}.
	 * 
	 * @param resourceURL The corresponding resource {@code URL}
	 * @param parts The target {@code URL} parts
	 * @return A {@code String} representation of the target {@code URL}
	 */
	private String buildURL(URL resourceURL, String... parts)
	{
		StringBuilder url;

		if(resourceURL == null)
		{
			url = new StringBuilder(apiBaseURL.toExternalForm());
		}
		else
		{
			url = new StringBuilder(resourceURL.toExternalForm());
		}

		for(String part : parts)
		{
			url.append("/");
			url.append(part);
		}

		System.out.println("[CoreService ResourceManager] " + url.toString());

		return url.toString();
	}

	/**
	 * Creates a {@link ClientResource} with the specified target
	 * {@link Reference}.
	 * 
	 * @param reference The target {@code Reference}
	 * @return The {@code ClientResource}
	 */
	private ClientResource createClientResource(Reference reference)
	{
		ClientResource clientResource = new ClientResource(reference);
		handleClientResource(systemSession, clientResource);

		System.out.println("[CoreService ResourceManager] " + reference.toString());

		return clientResource;
	}

	/**
	 * Creates a {@link ClientResource} with the specified target {@code URL}
	 * parts. <br/><br/>
	 * 
	 * <i>Annotation:</i> The parts are preceded by the API base {@code URL}.
	 * For example if the parts {@code "xyz"} and {@code "15"} are delivered and
	 * the API base {@code URL} is {@code http://example.diretto.org/v2} the
	 * result for the target {@code URL} is
	 * {@code http://example.diretto.org/v2/xyz/15}.
	 * 
	 * @param parts The target {@code URL} parts
	 * @return The {@code ClientResource}
	 */
	private ClientResource createClientResource(String... parts)
	{
		ClientResource clientResource = new ClientResource(buildURL(null, parts));
		handleClientResource(systemSession, clientResource);

		return clientResource;
	}

	/**
	 * Creates a {@link ClientResource} with the specified target {@code URL}
	 * parts. <br/><br/>
	 * 
	 * <i>Annotation:</i> The parts are preceded by the given
	 * {@code resourceURL}. For example if the parts {@code "xyz"} and
	 * {@code "15"} are delivered and the {@code resourceURL} is
	 * {@code http://example.diretto.org/v2} the result for the target
	 * {@code URL} is {@code http://example.diretto.org/v2/xyz/15}.
	 * 
	 * @param resourceURL The corresponding resource {@code URL}
	 * @param parts The target {@code URL} parts
	 * @return The {@code ClientResource}
	 */
	private ClientResource createClientResource(URL resourceURL, String... parts)
	{
		ClientResource clientResource = new ClientResource(buildURL(resourceURL, parts));
		handleClientResource(systemSession, clientResource);

		return clientResource;
	}

	/**
	 * Creates a {@link ClientResource} with the specified target {@code URL}
	 * parts. <br/><br/>
	 * 
	 * <i>Annotation:</i> The parts are preceded by the API base {@code URL}.
	 * For example if the parts {@code "xyz"} and {@code "15"} are delivered and
	 * the API base {@code URL} is {@code http://example.diretto.org/v2} the
	 * result for the target {@code URL} is
	 * {@code http://example.diretto.org/v2/xyz/15}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param parts The target {@code URL} parts
	 * @return The {@code ClientResource}
	 */
	private ClientResource createClientResource(UserSession userSession, String... parts)
	{
		ClientResource clientResource = new ClientResource(buildURL(null, parts));
		handleClientResource(userSession, clientResource);

		return clientResource;
	}

	/**
	 * Creates a {@link ClientResource} with the specified target {@code URL}
	 * parts. <br/><br/>
	 * 
	 * <i>Annotation:</i> The parts are preceded by the given
	 * {@code resourceURL}. For example if the parts {@code "xyz"} and
	 * {@code "15"} are delivered and the {@code resourceURL} is
	 * {@code http://example.diretto.org/v2} the result for the target
	 * {@code URL} is {@code http://example.diretto.org/v2/xyz/15}.
	 * 
	 * @param userSession The corresponding {@code UserSession}
	 * @param resourceURL The corresponding resource {@code URL}
	 * @param parts The target {@code URL} parts
	 * @return The {@code ClientResource}
	 */
	private ClientResource createClientResource(UserSession userSession, URL resourceURL, String... parts)
	{
		ClientResource clientResource = new ClientResource(buildURL(resourceURL, parts));
		handleClientResource(userSession, clientResource);

		return clientResource;
	}

	/**
	 * Returns an <i>anonymous</i> {@link TagID} for the given
	 * {@link TagCreationResource}.
	 * 
	 * @param tagCreationResource The {@code TagCreationResource}
	 * @return The <i>anonymous</i> {@code TagID}
	 */
	private TagID getAnonymousTagID(TagCreationResource tagCreationResource)
	{
		ClientResource clientResource = createClientResource("tags");

		BaseTagResource baseTagResource;

		try
		{
			baseTagResource = clientResource.post(tagCreationResource, BaseTagResource.class);
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}

		return CoreServiceEntityIDFactory.getAnonymousTagIDInstance(baseTagResource.getBaseTag().getLink().getHref());
	}

	/**
	 * Returns the {@link QueryResultPageResource} for the given
	 * {@link ClientResource}.
	 * 
	 * @param queryResultPageResourceClass The exact class of the
	 *        {@code QueryResultPageResource}
	 * @param clientResource The {@code ClientResource}
	 * @return The {@code QueryResultPageResource}
	 * @throws NoResultsException
	 */
	private <T extends QueryResultPageResource> T getQueryResultPageResource(Class<T> queryResultPageResourceClass, ClientResource clientResource) throws NoResultsException
	{
		if(clientResource.getResponse().getStatus().getCode() == 202)
		{
			clientResource = createClientResource((clientResource.getResponse().getLocationRef()));

			try
			{
				T queryResultPageResource = clientResource.get(queryResultPageResourceClass);

				if(clientResource.getResponse().getStatus().getCode() == 204)
				{
					throw new NoResultsException();
				}

				return queryResultPageResource;
			}
			catch(ResourceException e)
			{
				System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
				return null;
			}
		}
		else
		{
			return null;
		}
	}

	/**
	 * Returns the {@link ResultPageResource} for the given
	 * {@link ClientResource}.
	 * 
	 * @param resultPageResourceClass The exact class of the
	 *        {@code ResultPageResource}
	 * @param clientResource The {@code ClientResource}
	 * @return The {@code ResultPageResource}
	 * @throws NoResultsException
	 */
	private <T extends ResultPageResource> T getResultPageResource(Class<T> resultPageResourceClass, ClientResource clientResource) throws NoResultsException
	{
		try
		{
			T resultPageResource = clientResource.get(resultPageResourceClass);

			if(clientResource.getResponse().getStatus().getCode() == 204)
			{
				throw new NoResultsException();
			}

			return resultPageResource;
		}
		catch(ResourceException e)
		{
			System.err.println("[CoreService ResourceManager] " + e.getStatus().getCode());
			return null;
		}
	}

	/**
	 * Handles the given {@link ClientResource}.
	 * 
	 * @param session The corresponding {@code Session}
	 * @param clientResource The {@code ClientResource} to be handled
	 */
	private void handleClientResource(Session session, ClientResource clientResource)
	{
		clientResource.setNext(restletClient);

		if(session != null)
		{
			UserFactory.authenticateClientResource(session.getUser(), clientResource);
		}
	}
}
