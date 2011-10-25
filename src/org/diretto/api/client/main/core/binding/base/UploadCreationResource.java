package org.diretto.api.client.main.core.binding.base;

/**
 * This interface represents an {@code UploadCreationResource}.
 * 
 * @author Tobias Schlecht
 */
public interface UploadCreationResource
{
	String getTitle();

	void setTitle(String title);

	String getDescription();

	void setDescription(String description);

	String getMimeType();

	void setMimeType(String mimeType);

	String getLicense();

	long getFileSize();
}
