package org.diretto.api.client.main.core.binding.post;

import org.diretto.api.client.main.core.binding.base.AttachmentCreationResource;

/**
 * This class represents a POJO based {@code ExternalAttachmentCreationResource}
 * . <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class ExternalAttachmentCreationResource extends AttachmentCreationResource
{
	private String external;

	public String getExternal()
	{
		return external;
	}

	public void setExternal(String external)
	{
		this.external = external;
	}
}
