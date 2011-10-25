package org.diretto.api.client.main.core.entities.creation;

import org.diretto.api.client.base.data.PlatformMediaType;
import org.diretto.api.client.main.core.entities.Attachment;

/**
 * This class encapsulates the necessary data for creating a platform
 * {@link Attachment}. <br/><br/>
 * 
 * <i>Annotation:</i> The <u>Builder pattern</u> is used for creating instances
 * of this class.
 * 
 * @author Tobias Schlecht
 */
public final class PlatformAttachmentCreationData extends AttachmentCreationData
{
	private final PlatformMediaType platformMediaType;
	private final long fileSize;

	/**
	 * Constructs an {@link PlatformAttachmentCreationData} object. <br/><br/>
	 * 
	 * <i>Annotation:</i> The constructor is {@code private}, because it is
	 * intended that only the {@link Builder} creates instances.
	 * 
	 * @param builder A {@code Builder} object
	 */
	private PlatformAttachmentCreationData(Builder builder)
	{
		super(builder);

		platformMediaType = builder.platformMediaType;
		fileSize = builder.fileSize;

		if(platformMediaType == null)
		{
			throw new NullPointerException();
		}

		if(fileSize <= 0)
		{
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Returns the {@link PlatformMediaType} of the resource.
	 * 
	 * @return The {@code PlatformMediaType} of the resource
	 */
	public PlatformMediaType getPlatformMediaType()
	{
		return platformMediaType;
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
	 * This <i>static member</i> class implements the
	 * {@link org.diretto.api.client.base.data.Builder} interface and serves for
	 * creating {@code Builder} objects for
	 * {@link PlatformAttachmentCreationData}.
	 */
	public static class Builder extends AttachmentCreationData.Builder implements org.diretto.api.client.base.data.Builder<PlatformAttachmentCreationData>
	{
		private PlatformMediaType platformMediaType = null;
		private long fileSize = 0;

		/**
		 * Sets the {@link PlatformMediaType} of the resource.
		 * 
		 * @param platformMediaType The {@code PlatformMediaType} of the
		 *        resource
		 * @return The {@code Builder} object
		 */
		public Builder platformMediaType(PlatformMediaType platformMediaType)
		{
			this.platformMediaType = platformMediaType;
			return this;
		}

		/**
		 * Sets the size of the file in {@code Bytes}.
		 * 
		 * @param fileSize The size of the file in {@code Bytes}
		 * @return The {@code Builder} object
		 */
		public Builder fileSize(long fileSize)
		{
			this.fileSize = fileSize;
			return this;
		}

		@Override
		public PlatformAttachmentCreationData build()
		{
			return new PlatformAttachmentCreationData(this);
		}
	}
}
