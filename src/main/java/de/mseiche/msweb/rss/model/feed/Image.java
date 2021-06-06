package de.mseiche.msweb.rss.model.feed;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class Image {

	@JacksonXmlProperty
	private URI url;

	@JacksonXmlProperty
	private String title;

	@JacksonXmlProperty
	private String description;

	@JacksonXmlProperty
	private URI link;

	@JacksonXmlProperty
	private Integer width;

	@JacksonXmlProperty
	private Integer height;

	public Image(final URI url, final String title, final String description, final URI link, final Integer width,
			final Integer height) {
		super();
		this.url = url;
		this.title = title;
		this.description = description;
		this.link = link;
		this.width = width;
		this.height = height;
	}
}
