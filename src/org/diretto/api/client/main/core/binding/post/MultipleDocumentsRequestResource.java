package org.diretto.api.client.main.core.binding.post;

import java.util.ArrayList;

/**
 * This class represents a POJO based {@code MultipleDocumentsRequestResource}.
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
public final class MultipleDocumentsRequestResource
{
	private ArrayList<String> documents;

	public ArrayList<String> getDocuments()
	{
		return documents;
	}

	public void setDocuments(ArrayList<String> documents)
	{
		this.documents = documents;
	}
}
