package org.diretto.api.client.main.core.entities;

import org.diretto.api.client.base.data.Builder;
import org.diretto.api.client.base.data.ResultSet;
import org.diretto.api.client.main.core.DataManager;
import org.diretto.api.client.main.core.DataManagerImpl;
import org.diretto.api.client.user.UserID;
import org.joda.time.DateTime;

/**
 * This class implements the {@link Builder} interface and serves for creating
 * {@code Builder} objects for {@link Collection}s.
 * 
 * @author Tobias Schlecht
 */
public final class CollectionBuilder implements Builder<Collection>
{
	private final CollectionID collectionID;

	private final DataManagerImpl dataManager;

	private final ResultSet<DocumentID, Document> documents;

	private String title = null;
	private String description = null;
	private DateTime creationTime = null;
	private UserID creator = null;
	private boolean privateSetting = true;

	/**
	 * Constructs a {@link CollectionBuilder} object.
	 * 
	 * @param collectionID The {@code CollectionID}
	 * @param dataManager The {@code DataManager}
	 * @param documents The {@code ResultSet} with the {@code Document}s
	 */
	public CollectionBuilder(CollectionID collectionID, DataManagerImpl dataManager, ResultSet<DocumentID, Document> documents)
	{
		this.collectionID = collectionID;
		this.dataManager = dataManager;
		this.documents = documents;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title The title
	 * @return The {@code CollectionBuilder} object
	 */
	public CollectionBuilder title(String title)
	{
		this.title = title;
		return this;
	}

	/**
	 * Sets the description.
	 * 
	 * @param description The description
	 * @return The {@code CollectionBuilder} object
	 */
	public CollectionBuilder description(String description)
	{
		this.description = description;
		return this;
	}

	/**
	 * Sets the creation {@link DateTime}.
	 * 
	 * @param creationTime The creation {@code DateTime}
	 * @return The {@code CollectionBuilder} object
	 */
	public CollectionBuilder creationTime(DateTime creationTime)
	{
		this.creationTime = creationTime;
		return this;
	}

	/**
	 * Sets the {@link UserID} of the creator.
	 * 
	 * @param creator The {@code UserID} of the creator
	 * @return The {@code CollectionBuilder} object
	 */
	public CollectionBuilder creator(UserID creator)
	{
		this.creator = creator;
		return this;
	}

	/**
	 * Sets the private setting.
	 * 
	 * @param privateSetting {@code true} if the {@code Collection} is
	 *        {@code private} (then only the {@code User} who created the
	 *        {@code Collection} has access to the {@code Collection});
	 *        {@code false} if the {@code Collection} is {@code public}
	 */
	public CollectionBuilder privateSetting(boolean privateSetting)
	{
		this.privateSetting = privateSetting;
		return this;
	}

	/**
	 * Returns the {@link CollectionID}.
	 * 
	 * @return The {@code CollectionID}
	 */
	public CollectionID getCollectionID()
	{
		return collectionID;
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
	 * Returns the private setting.
	 * 
	 * @return {@code true} if the {@code Collection} is {@code private} (then
	 *         only the {@code User} who created the {@code Collection} has
	 *         access to the {@code Collection}); {@code false} if the
	 *         {@code Collection} is {@code public}
	 */
	public boolean getPrivateSetting()
	{
		return privateSetting;
	}

	/**
	 * Returns the {@link ResultSet} with the {@link Document}s.
	 * 
	 * @return The {@code ResultSet} with the {@code Document}s
	 */
	public ResultSet<DocumentID, Document> getDocuments()
	{
		return documents;
	}

	@Override
	public Collection build()
	{
		return new CollectionImpl(this);
	}
}
