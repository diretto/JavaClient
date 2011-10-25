package org.diretto.api.client.base.data;

import java.net.MalformedURLException;
import java.net.URL;

import org.diretto.api.client.main.core.entities.AttachmentID;

/**
 * This class is responsible for the encapsulation of the upload data. And
 * therefore an {@code UploadInfo} object is used for the carriage of the upload
 * data from the {@code CoreService} of the base {@code JavaClient} to the
 * {@code JavaClientStoragePlugin}. <br/><br/>
 * 
 * <i>Annotation:</i> It is an immutable class.
 * 
 * @author Tobias Schlecht
 */
public final class UploadInfo
{
	private final long fileSize;
	private final PlatformMediaType platformMediaType;
	private final AttachmentID attachmentID;
	private final String token;
	private final URL fileURL;
	private final URL target;

	/**
	 * Constructs an {@link UploadInfo} object.
	 * 
	 * @param fileSize The size of the file in {@code Bytes}
	 * @param platformMediaType The {@code PlatformMediaType} of the resource
	 * @param attachmentID The corresponding {@code AttachmentID}
	 * @param token The upload token
	 * @param fileURL The file {@code URL}
	 * @param target The target {@code URL}
	 */
	public UploadInfo(long fileSize, PlatformMediaType platformMediaType, AttachmentID attachmentID, String token, URL fileURL, URL target)
	{
		this.fileSize = fileSize;
		this.platformMediaType = platformMediaType;
		this.attachmentID = attachmentID;
		this.token = token;
		this.fileURL = fileURL;
		this.target = target;
	}

	/**
	 * Constructs an {@link UploadInfo} object.
	 * 
	 * @param fileSize The size of the file in {@code Bytes}
	 * @param platformMediaType The {@code PlatformMediaType} of the resource
	 * @param attachmentID The corresponding {@code AttachmentID}
	 * @param token The upload token
	 * @param fileURL The {@code String} representation of the file {@code URL}
	 * @param target The {@code String} representation of the target {@code URL}
	 */
	public UploadInfo(long fileSize, PlatformMediaType platformMediaType, AttachmentID attachmentID, String token, String fileURL, String target)
	{
		this.fileSize = fileSize;
		this.platformMediaType = platformMediaType;
		this.attachmentID = attachmentID;
		this.token = token;

		URL initFileURL = null;
		URL initTarget = null;

		try
		{
			initFileURL = new URL(fileURL);
			initTarget = new URL(target);
		}
		catch(MalformedURLException e)
		{
			e.printStackTrace();
		}

		this.fileURL = initFileURL;
		this.target = initTarget;
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
	 * Returns the {@link PlatformMediaType} of the resource.
	 * 
	 * @return The {@code PlatformMediaType} of the resource
	 */
	public PlatformMediaType getPlatformMediaType()
	{
		return platformMediaType;
	}

	/**
	 * Returns the corresponding {@link AttachmentID}.
	 * 
	 * @return The corresponding {@code AttachmentID}
	 */
	public AttachmentID getAttachmentID()
	{
		return attachmentID;
	}

	/**
	 * Returns the upload token.
	 * 
	 * @return The upload token
	 */
	public String getToken()
	{
		return token;
	}

	/**
	 * Returns the {@link URL} of the file.
	 * 
	 * @return The file {@code URL}
	 */
	public URL getFileURL()
	{
		return fileURL;
	}

	/**
	 * Returns the target {@link URL}.
	 * 
	 * @return The target {@code URL}
	 */
	public URL getTarget()
	{
		return target;
	}
}
