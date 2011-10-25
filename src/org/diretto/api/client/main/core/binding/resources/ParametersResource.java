package org.diretto.api.client.main.core.binding.resources;

/**
 * This class represents a POJO based {@code ParametersResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class ParametersResource
{
	private int paginationSize;
	private int batchLimit;

	public int getPaginationSize()
	{
		return paginationSize;
	}

	public void setPaginationSize(int paginationSize)
	{
		this.paginationSize = paginationSize;
	}

	public int getBatchLimit()
	{
		return batchLimit;
	}

	public void setBatchLimit(int batchLimit)
	{
		this.batchLimit = batchLimit;
	}
}
