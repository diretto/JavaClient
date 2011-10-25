package org.diretto.api.client.main.core.entities.creation;

import java.util.ArrayList;
import java.util.List;

import org.diretto.api.client.base.data.Contributor;
import org.diretto.api.client.main.core.entities.Attachment;
import org.diretto.api.client.user.UserID;

/**
 * This {@code abstract} class provides base implementation for specific
 * {@code AttachmentCreationData} classes.
 * 
 * @author Tobias Schlecht
 */
public abstract class AttachmentCreationData
{
	private final String title;
	private final String description;
	private final String license;
	private final ArrayList<Contributor> contributors;
	private final ArrayList<UserID> creators;

	/**
	 * Provides base implementation to construct an
	 * {@link AttachmentCreationData} object.
	 * 
	 * @param builder A {@code Builder} object
	 */
	AttachmentCreationData(Builder builder)
	{
		title = builder.title;

		if(builder.description == null)
		{
			description = "";
		}
		else
		{
			description = builder.description;
		}

		if(builder.license == null)
		{
			license = "";
		}
		else
		{
			license = builder.license;
		}

		if(builder.contributors == null)
		{
			contributors = new ArrayList<Contributor>();
		}
		else
		{
			contributors = builder.contributors;
		}

		if(builder.creators == null)
		{
			creators = new ArrayList<UserID>();
		}
		else
		{
			creators = builder.creators;
		}

		if(title == null)
		{
			throw new NullPointerException();
		}
		else if(title.trim().equals(""))
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the title of the {@link Attachment}.
	 * 
	 * @return The title of the {@code Attachment}
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Returns the description of the {@link Attachment}.
	 * 
	 * @return The description of the {@code Attachment}
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * Returns the license of the {@link Attachment}.
	 * 
	 * @return The license of the {@code Attachment}
	 */
	public String getLicense()
	{
		return license;
	}

	/**
	 * Returns the {@link List} with the {@link Contributor}s.
	 * 
	 * @return The {@code List} with the {@code Contributor}s
	 */
	public ArrayList<Contributor> getContributors()
	{
		return contributors;
	}

	/**
	 * Returns the {@link List} with the {@link UserID}s of the creators.
	 * 
	 * @return The {@code List} with the {@code UserID}s of the creators
	 */
	public ArrayList<UserID> getCreators()
	{
		return creators;
	}

	/**
	 * This {@code abstract} <i>static member</i> class provides base
	 * implementation for specific {@link AttachmentCreationData}
	 * {@code Builder}s.
	 */
	public static abstract class Builder
	{
		private String title = null;
		private String description = null;
		private String license = null;
		private ArrayList<Contributor> contributors = null;
		private ArrayList<UserID> creators = null;

		/**
		 * Sets the title of the {@link Attachment}.
		 * 
		 * @param title The title of the {@code Attachment}
		 * @return The {@code Builder} object
		 */
		public Builder title(String title)
		{
			this.title = title;
			return this;
		}

		/**
		 * Sets the description of the {@link Attachment}. <br/><br/>
		 * 
		 * <i>Annotation:</i> The description is optional.
		 * 
		 * @param description The description of the {@code Attachment}
		 * @return The {@code Builder} object
		 */
		public Builder description(String description)
		{
			this.description = description;
			return this;
		}

		/**
		 * Sets the license of the {@link Attachment}. <br/><br/>
		 * 
		 * <i>Annotation:</i> The license is optional. But if it is used, the
		 * given {@code String} can represent either the title of a license or a
		 * license text or an {@code URL} to a license.
		 * 
		 * @param license The license of the {@code Attachment}
		 * @return The {@code Builder} object
		 */
		public Builder license(String license)
		{
			this.license = license;
			return this;
		}

		/**
		 * Sets the {@link List} with the {@link Contributor}s.
		 * 
		 * @param contributors A {@code List} with the {@code Contributor}s
		 * @return The {@code Builder} object
		 */
		public Builder contributors(ArrayList<Contributor> contributors)
		{
			this.contributors = contributors;
			return this;
		}

		/**
		 * Sets the {@link List} with the {@link UserID}s of the creators.
		 * 
		 * @param creators A {@code List} with the {@code UserID}s of the
		 *        creators
		 * @return The {@code Builder} object
		 */
		public Builder creators(ArrayList<UserID> creators)
		{
			this.creators = creators;
			return this;
		}
	}
}
