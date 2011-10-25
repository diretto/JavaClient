package org.diretto.api.client.main.core.binding.resources;

import java.util.ArrayList;

/**
 * This class represents a POJO based {@code LinksResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class LinksResource
{
	private HyperLinkResource link;
	private ArrayList<DocumentLinkResource> in;
	private ArrayList<DocumentLinkResource> out;

	public HyperLinkResource getLink()
	{
		return link;
	}

	public void setLink(HyperLinkResource link)
	{
		this.link = link;
	}

	public ArrayList<DocumentLinkResource> getIn()
	{
		return in;
	}

	public void setIn(ArrayList<DocumentLinkResource> in)
	{
		this.in = in;
	}

	public ArrayList<DocumentLinkResource> getOut()
	{
		return out;
	}

	public void setOut(ArrayList<DocumentLinkResource> out)
	{
		this.out = out;
	}
}
