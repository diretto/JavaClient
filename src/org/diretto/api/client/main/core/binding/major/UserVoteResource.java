package org.diretto.api.client.main.core.binding.major;

/**
 * This class represents a POJO based {@code UserVoteResource}. <br/><br/>
 * 
 * It is used for operating with the data interchange format JSON. So it is
 * possible to marshal Java objects into JSON representation and to unmarshal
 * JSON messages into Java objects. <br/><br/>
 * 
 * <i>Annotation:</i> This is also called <u>(full) data binding<u/>
 * 
 * @author Tobias Schlecht
 */
public final class UserVoteResource
{
	private String vote;

	public String getVote()
	{
		return vote;
	}

	public void setVote(String vote)
	{
		this.vote = vote;
	}
}
