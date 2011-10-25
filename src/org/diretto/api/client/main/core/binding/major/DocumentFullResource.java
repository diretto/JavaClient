package org.diretto.api.client.main.core.binding.major;

import org.diretto.api.client.main.core.binding.resources.AttachmentsResource;
import org.diretto.api.client.main.core.binding.resources.CommentsResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResource;
import org.diretto.api.client.main.core.binding.resources.HyperLinkResourceWrapper;
import org.diretto.api.client.main.core.binding.resources.KeyValuesResource;
import org.diretto.api.client.main.core.binding.resources.LinksResource;
import org.diretto.api.client.main.core.binding.resources.PositionsResource;
import org.diretto.api.client.main.core.binding.resources.TagsResource;
import org.diretto.api.client.main.core.binding.resources.TimesResource;

/**
 * This class represents a POJO based {@code DocumentFullResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class DocumentFullResource
{
	private DocumentResource document;

	public DocumentResource getDocument()
	{
		return document;
	}

	public void setDocument(DocumentResource document)
	{
		this.document = document;
	}

	public static class DocumentResource
	{
		private HyperLinkResource link;
		private String mediaType;
		private String publishedTime;
		private HyperLinkResourceWrapper publisher;
		private String title;
		private String description;
		private CommentsResource comments;
		private TagsResource tags;
		private AttachmentsResource attachments;
		private PositionsResource locations;
		private TimesResource times;
		private KeyValuesResource values;
		private LinksResource documentLinks;

		public HyperLinkResource getLink()
		{
			return link;
		}

		public void setLink(HyperLinkResource link)
		{
			this.link = link;
		}

		public String getMediaType()
		{
			return mediaType;
		}

		public void setMediaType(String mediaType)
		{
			this.mediaType = mediaType;
		}

		public String getPublishedTime()
		{
			return publishedTime;
		}

		public void setPublishedTime(String publishedTime)
		{
			this.publishedTime = publishedTime;
		}

		public HyperLinkResourceWrapper getPublisher()
		{
			return publisher;
		}

		public void setPublisher(HyperLinkResourceWrapper publisher)
		{
			this.publisher = publisher;
		}

		public String getTitle()
		{
			return title;
		}

		public void setTitle(String title)
		{
			this.title = title;
		}

		public String getDescription()
		{
			return description;
		}

		public void setDescription(String description)
		{
			this.description = description;
		}

		public CommentsResource getComments()
		{
			return comments;
		}

		public void setComments(CommentsResource comments)
		{
			this.comments = comments;
		}

		public TagsResource getTags()
		{
			return tags;
		}

		public void setTags(TagsResource tags)
		{
			this.tags = tags;
		}

		public AttachmentsResource getAttachments()
		{
			return attachments;
		}

		public void setAttachments(AttachmentsResource attachments)
		{
			this.attachments = attachments;
		}

		public PositionsResource getLocations()
		{
			return locations;
		}

		public void setLocations(PositionsResource locations)
		{
			this.locations = locations;
		}

		public TimesResource getTimes()
		{
			return times;
		}

		public void setTimes(TimesResource times)
		{
			this.times = times;
		}

		public KeyValuesResource getValues()
		{
			return values;
		}

		public void setValues(KeyValuesResource values)
		{
			this.values = values;
		}

		public LinksResource getDocumentLinks()
		{
			return documentLinks;
		}

		public void setDocumentLinks(LinksResource documentLinks)
		{
			this.documentLinks = documentLinks;
		}
	}
}
