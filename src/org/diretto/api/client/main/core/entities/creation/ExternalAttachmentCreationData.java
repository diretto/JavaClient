package org.diretto.api.client.main.core.entities.creation;

import java.net.URL;

import org.diretto.api.client.base.data.ExternalMediaType;
import org.diretto.api.client.main.core.entities.Attachment;

/**
 * This class encapsulates the necessary data for creating an external
 * {@link Attachment}. <br/><br/>
 * 
 * <i>Annotation:</i> The <u>Builder pattern</u> is used for creating instances
 * of this class.
 * 
 * @author Tobias Schlecht
 */
public final class ExternalAttachmentCreationData extends AttachmentCreationData
{
	private final ExternalMediaType externalMediaType;
	private final URL fileURL;

	/**
	 * Constructs an {@link ExternalAttachmentCreationData} object. <br/><br/>
	 * 
	 * <i>Annotation:</i> The constructor is {@code private}, because it is
	 * intended that only the {@link Builder} creates instances.
	 * 
	 * @param builder A {@code Builder} object
	 */
	private ExternalAttachmentCreationData(Builder builder)
	{
		super(builder);

		externalMediaType = builder.externalMediaType;
		fileURL = builder.fileURL;

		if(externalMediaType == null || fileURL == null)
		{
			throw new NullPointerException();
		}
	}

	/**
	 * Returns the {@link ExternalMediaType} of the resource.
	 * 
	 * @return The {@code ExternalMediaType} of the resource
	 */
	public ExternalMediaType getExternalMediaType()
	{
		return externalMediaType;
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
	 * This <i>static member</i> class implements the
	 * {@link org.diretto.api.client.base.data.Builder} interface and serves for
	 * creating {@code Builder} objects for
	 * {@link ExternalAttachmentCreationData}.
	 */
	public static class Builder extends AttachmentCreationData.Builder implements org.diretto.api.client.base.data.Builder<ExternalAttachmentCreationData>
	{
		private ExternalMediaType externalMediaType = null;
		private URL fileURL = null;

		/**
		 * Sets the {@link ExternalMediaType} of the resource.
		 * 
		 * @param externalMediaType The {@code ExternalMediaType} of the
		 *        resource
		 * @return The {@code Builder} object
		 */
		public Builder externalMediaType(ExternalMediaType externalMediaType)
		{
			this.externalMediaType = externalMediaType;
			return this;
		}

		/**
		 * Sets the {@link URL} of the file.
		 * 
		 * @param fileURL The {@code URL} of the file.
		 * @return The {@code Builder} object
		 */
		public Builder fileURL(URL fileURL)
		{
			this.fileURL = fileURL;
			return this;
		}

		@Override
		public ExternalAttachmentCreationData build()
		{
			return new ExternalAttachmentCreationData(this);
		}
	}
}
