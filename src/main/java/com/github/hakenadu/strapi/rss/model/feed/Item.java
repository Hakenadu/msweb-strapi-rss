package com.github.hakenadu.strapi.rss.model.feed;

import java.net.URI;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class Item {

	/*
	 * REQUIRED
	 */

	@JacksonXmlProperty
	private final String title;

	@JacksonXmlProperty
	private final String description;

	@JacksonXmlProperty
	private final URI link;

	/*
	 * Optional
	 */

	@JacksonXmlProperty
	private final String author;

	@JacksonXmlProperty
	private final String category;

	@JacksonXmlProperty
	private final String enclosure;

	@JacksonXmlProperty
	private final String guid;

	/**
	 * String for simplicity in serialization... validation will happen in
	 * ItemFactory
	 */
	@JacksonXmlProperty
	private final String pubDate;

	@JacksonXmlProperty
	private final String source;

	public Item(final String title, final String description, final URI link, final String author,
			final String category, final String enclosure, final String guid, final String pubDate,
			final String source) {
		Objects.requireNonNull(title, "no title for item passed");
		Objects.requireNonNull(description, "no description for item passed");
		Objects.requireNonNull(link, "no link for item passed");

		this.title = title;
		this.description = description;
		this.link = link;
		this.author = author;
		this.category = category;
		this.enclosure = enclosure;
		this.guid = guid;
		this.pubDate = pubDate;
		this.source = source;
	}
}
