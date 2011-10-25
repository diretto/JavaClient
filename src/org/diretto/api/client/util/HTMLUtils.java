package org.diretto.api.client.util;

import org.apache.commons.lang.StringEscapeUtils;

/**
 * {@code HTMLUtils} is a noninstantiable utility class and is responsible for
 * HTML related aspects.
 * 
 * @author Tobias Schlecht
 */
public final class HTMLUtils
{
	/**
	 * The constructor is {@code private} to suppress the default constructor
	 * for noninstantiability.
	 */
	private HTMLUtils()
	{
		throw new AssertionError();
	}

	/**
	 * Escapes the given {@code String}.
	 * 
	 * @param text The {@code String} to be escaped
	 * @return The escaped result {@code String}
	 */
	public static String escapeHTML(String text)
	{
		return escapeHTML(text, false);
	}

	/**
	 * Escapes the given {@code String}.
	 * 
	 * @param text The {@code String} to be escaped
	 * @param replaceLineBreaks {@code true} if the line breaks should also be
	 *        replaced by HTML; otherwise {@code false}
	 * @return The escaped result {@code String}
	 */
	public static String escapeHTML(String text, boolean replaceLineBreaks)
	{
		text = StringEscapeUtils.escapeHtml(text);

		if(replaceLineBreaks)
		{
			text = text.replaceAll("(\r\n|\n)", "<br />");
		}

		return text;
	}
}
