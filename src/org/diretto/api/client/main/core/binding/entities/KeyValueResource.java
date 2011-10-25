package org.diretto.api.client.main.core.binding.entities;

import org.diretto.api.client.main.core.binding.resources.KeyResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;

/**
 * This class represents a POJO based {@code KeyValueResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class KeyValueResource
{
	private HyperLinkResource link;
	private KeyResource key;
	private String value;

	public HyperLinkResource getLink()
	{
		return link;
	}

	public void setLink(HyperLinkResource link)
	{
		this.link = link;
	}

	public KeyResource getKey()
	{
		return key;
	}

	public void setKey(KeyResource key)
	{
		this.key = key;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
