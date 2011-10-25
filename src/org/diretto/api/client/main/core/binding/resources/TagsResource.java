package org.diretto.api.client.main.core.binding.resources;

import java.util.ArrayList;

import org.diretto.api.client.main.core.binding.entities.TagResource;

/**
 * This class represents a POJO based {@code TagsResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class TagsResource
{
	private HyperLinkResource link;
	private ArrayList<ListEntry> list;

	public HyperLinkResource getLink()
	{
		return link;
	}

	public void setLink(HyperLinkResource link)
	{
		this.link = link;
	}

	public ArrayList<ListEntry> getList()
	{
		return list;
	}

	public void setList(ArrayList<ListEntry> list)
	{
		this.list = list;
	}

	public static class ListEntry
	{
		private TagResource tag;

		public TagResource getTag()
		{
			return tag;
		}

		public void setTag(TagResource tag)
		{
			this.tag = tag;
		}
	}
}
