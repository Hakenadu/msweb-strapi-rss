package de.mseiche.msweb.rss.model.feed;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class Item {

	@JacksonXmlProperty
	private String title;

	@JacksonXmlProperty
	private String description;

	@JacksonXmlProperty
	private URI link;

	public Item(final String title, final String description, final URI link) {
		this.title = title;
		this.description = description;
		this.link = link;
	}
}
