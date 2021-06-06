package com.github.hakenadu.strapi.rss.service.feed;

import java.net.URI;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.hakenadu.strapi.rss.model.feed.Item;

@ConstructorBinding
@ConfigurationProperties("item-factory")
public final class ItemFactory {

	private static final Pattern JSON_COORDINATE_MATCHER = Pattern.compile("<<([^\\[\\]\\s]+)>>");

	private static final DateTimeFormatter TARGET_DATE_FORMATTER = DateTimeFormatter.RFC_1123_DATE_TIME;

	/*
	 * Required
	 */

	private final String title;
	private final String description;
	private final String link;

	/*
	 * Optional
	 */

	private final String author;
	private final String category;
	private final String enclosure;
	private final String guid;
	private final String pubDate;
	private final String source;

	public ItemFactory(final String title, final String description, final String link, final String author,
			final String category, final String enclosure, final String guid, final String pubDate,
			final String source) {
		Objects.requireNonNull(title, "no title pattern for item-factory passed");
		Objects.requireNonNull(description, "no description pattern for item-factory passed");
		Objects.requireNonNull(link, "no link pattern for item-factory passed");

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

	private String resolveJsonCoordinate(final JsonNode entry, final String jsonCoordinate) {
		return Optional.of(jsonCoordinate).map(entry::at).map(JsonNode::asText).orElseThrow();
	}

	private String resolveItemValue(final JsonNode entry, final String pattern) {
		if (pattern == null) {
			return null;
		}

		final StringBuffer itemValueBuffer = new StringBuffer();

		final Matcher matcher = JSON_COORDINATE_MATCHER.matcher(pattern);
		while (matcher.find()) {
			final String jsonCoordinate = matcher.group(1);
			final String replacement = resolveJsonCoordinate(entry, jsonCoordinate);
			matcher.appendReplacement(itemValueBuffer, replacement);
		}

		matcher.appendTail(itemValueBuffer);

		return itemValueBuffer.toString();
	}

	public Item create(final JsonNode entry) {
		return new Item(resolveItemValue(entry, title), // required
				resolveItemValue(entry, description), // required
				URI.create(resolveItemValue(entry, link)), // required
				resolveItemValue(entry, author), // optional
				resolveItemValue(entry, category), // optional
				resolveItemValue(entry, enclosure), // optional
				resolveItemValue(entry, guid), // optional
				Optional.ofNullable(resolveItemValue(entry, pubDate))
						.map(pubDateString -> ZonedDateTime.parse(pubDateString))
						.map(intermediateLocalDateTime -> intermediateLocalDateTime.format(TARGET_DATE_FORMATTER))
						.orElse(null), // optional
				resolveItemValue(entry, source) // optional
		);
	}
}
