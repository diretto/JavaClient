package org.diretto.api.client.main.core.binding.major;

import java.util.ArrayList;

import org.diretto.api.client.main.core.binding.base.ResultPageResource;
import org.diretto.api.client.main.core.binding.post.DocumentHyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;

/**
 * This class represents a POJO based {@code DocumentResultPageResource}.
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
public final class DocumentResultPageResource implements ResultPageResource
{
	private HyperLinkResourceWrapper page;
	private ArrayList<DocumentHyperLinkResource> list;
	private ArrayList<HyperLinkResourceWrapper> related;

	public HyperLinkResourceWrapper getPage()
	{
		return page;
	}

	public void setPage(HyperLinkResourceWrapper page)
	{
		this.page = page;
	}

	public ArrayList<DocumentHyperLinkResource> getList()
	{
		return list;
	}

	public void setList(ArrayList<DocumentHyperLinkResource> list)
	{
		this.list = list;
	}

	public ArrayList<HyperLinkResourceWrapper> getRelated()
	{
		return related;
	}

	public void setRelated(ArrayList<HyperLinkResourceWrapper> related)
	{
		this.related = related;
	}
}
