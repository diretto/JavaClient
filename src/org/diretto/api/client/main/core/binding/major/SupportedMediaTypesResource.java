package org.diretto.api.client.main.core.binding.major;

import org.diretto.api.client.main.core.binding.resources.MediaTypesResource;

/**
 * This class represents a POJO based {@code SupportedMediaTypesResource}.
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
public final class SupportedMediaTypesResource
{
	private MediaTypesResource mediatypes;

	public MediaTypesResource getMediatypes()
	{
		return mediatypes;
	}

	public void setMediatypes(MediaTypesResource mediatypes)
	{
		this.mediatypes = mediatypes;
	}
}
