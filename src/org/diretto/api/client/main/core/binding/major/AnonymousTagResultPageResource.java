package org.diretto.api.client.main.core.binding.major;

import java.util.ArrayList;

import org.diretto.api.client.main.core.binding.base.ResultPageResource;
import org.diretto.api.client.main.core.binding.entities.AnonymousTagResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;

/**
 * This class represents a POJO based {@code AnonymousTagResultPageResource}.
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
public final class AnonymousTagResultPageResource implements ResultPageResource
{
	private HyperLinkResourceWrapper page;
	private ArrayList<ListEntry> list;
	private ArrayList<HyperLinkResourceWrapper> related;

	public HyperLinkResourceWrapper getPage()
	{
		return page;
	}

	public void setPage(HyperLinkResourceWrapper page)
	{
		this.page = page;
	}

	public ArrayList<ListEntry> getList()
	{
		return list;
	}

	public void setList(ArrayList<ListEntry> list)
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

	public static class ListEntry
	{
		private AnonymousTagResource baseTag;

		public AnonymousTagResource getBaseTag()
		{
			return baseTag;
		}

		public void setBaseTag(AnonymousTagResource baseTag)
		{
			this.baseTag = baseTag;
		}
	}
}
