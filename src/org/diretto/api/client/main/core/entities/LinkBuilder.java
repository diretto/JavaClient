package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.characteristic.VoteManager;
import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.base.data.Votes;
import org.diretto.api.client.main.core.DataManager;
import org.diretto.api.client.main.core.DataManagerImpl;
import org.diretto.api.client.user.User;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Link}s.
 * 
 * @author Tobias Schlecht
 */
public final class LinkBuilder implements Builder<Link>
{
	private final LinkID linkID;

	private final DataManagerImpl dataManager;
	private final VoteManager voteManager;

	private final DocumentID sourceDocumentID;
	private final DocumentID destinationDocumentID;

	private String title = null;
	private String description = null;
	private DateTime creationTime = null;
	private UserID creator = null;
	private Votes votes = null;

	private ResultSet<TagID, Tag> tags = null;

	/**
	 * Constructs a {@link LinkBuilder} object.
	 * 
	 * @param linkID The {@code LinkID}
	 * @param dataManager The {@code DataManager}
	 * @param voteManager The {@code VoteManager}
	 * @param sourceDocumentID The {@code DocumentID} of the source
	 *        {@code Document}
	 * @param destinationDocumentID The {@code DocumentID} of the destination
	 *        {@code Document}
	 */
	public LinkBuilder(LinkID linkID, DataManagerImpl dataManager, VoteManager voteManager, DocumentID sourceDocumentID, DocumentID destinationDocumentID)
	{
		this.linkID = linkID;
		this.dataManager = dataManager;
		this.voteManager = voteManager;
		this.sourceDocumentID = sourceDocumentID;
		this.destinationDocumentID = destinationDocumentID;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title The title
	 * @return The {@code LinkBuilder} object
	 */
	public LinkBuilder title(String title)
	{
		this.title = title;
		return this;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description The description
	 * @return The {@code LinkBuilder} object
	 */
	public LinkBuilder description(String description)
	{
		this.description = description;
		return this;
	}

	/**
	 * Sets the creation {@link DateTime}.
	 * 
	 * @param creationTime The creation {@code DateTime}
	 * @return The {@code LinkBuilder} object
	 */
	public LinkBuilder creationTime(DateTime creationTime)
	{
		this.creationTime = creationTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the creator.
	 * 
	 * @param creator The {@code UserID} of the creator
	 * @return The {@code LinkBuilder} object
	 */
	public LinkBuilder creator(UserID creator)
	{
		this.creator = creator;
		return this;
	}

	/**
	 * Sets the {@link Votes} of all {@link User}s.
	 * 
	 * @param votes The {@code Votes} of all {@code User}s
	 * @return The {@code LinkBuilder} object
	 */
	public LinkBuilder votes(Votes votes)
	{
		this.votes = votes;
		return this;
	}

	/**
	 * Sets the {@link ResultSet} with the {@link Tag}s.
	 * 
	 * @param tags The {@code ResultSet} with the {@code Tag}s
	 * @return The {@code LinkBuilder} object
	 */
	public LinkBuilder tags(ResultSet<TagID, Tag> tags)
	{
		this.tags = tags;
		return this;
	}

	/**
	 * Returns the {@link LinkID}.
	 * 
	 * @return The {@code LinkID}
	 */
	public LinkID getLinkID()
	{
		return linkID;
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
	 * Returns the {@link VoteManager}.
	 * 
	 * @return The {@code VoteManager}
	 */
	public VoteManager getVoteManager()
	{
		return voteManager;
	}

	/**
	 * Returns the {@link DocumentID} of the source {@link Document}.
	 * 
	 * @return The {@code DocumentID} of the source {@code Document}
	 */
	public DocumentID getSourceDocumentID()
	{
		return sourceDocumentID;
	}

	/**
	 * Returns the {@link DocumentID} of the destination {@link Document}.
	 * 
	 * @return The {@code DocumentID} of the destination {@code Document}
	 */
	public DocumentID getDestinationDocumentID()
	{
		return destinationDocumentID;
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
	 * Returns the creation {@link DateTime}.
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

	/**
	 * Returns the {@link ResultSet} with the {@link Tag}s.
	 * 
	 * @return The {@code ResultSet} with the {@code Tag}s
	 */
	public ResultSet<TagID, Tag> getTags()
	{
		return tags;
	}

	@Override
	public Link build()
	{
		return new LinkImpl(this);
	}
}
