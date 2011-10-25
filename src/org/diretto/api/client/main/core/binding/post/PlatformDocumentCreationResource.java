package org.diretto.api.client.main.core.binding.post;

import org.diretto.api.client.main.core.binding.base.DocumentCreationResource;
import org.diretto.api.client.main.core.binding.base.UploadCreationResource;

/**
 * This class represents a POJO based {@code PlatformDocumentCreationResource}.
 * <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class PlatformDocumentCreationResource extends DocumentCreationResource implements UploadCreationResource
{
	private long fileSize;

	@Override
	public long getFileSize()
	{
		return fileSize;
	}

	public void setFileSize(long fileSize)
	{
		this.fileSize = fileSize;
	}
}
