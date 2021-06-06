package de.mseiche.msweb.rss.service.feed;

import java.net.URI;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import com.fasterxml.jackson.databind.JsonNode;

import de.mseiche.msweb.rss.model.feed.Item;

@ConstructorBinding
@ConfigurationProperties("item-factory")
public final class ItemFactory {

	private static final Pattern JSON_COORDINATE_MATCHER = Pattern.compile("<<([^\\[\\]\\s]+)>>");

	private final String title;
	private final String description;
	private final String link;

	public ItemFactory(final String title, final String description, final String link) {
		this.title = title;
		this.description = description;
		this.link = link;
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
		return new Item(resolveItemValue(entry, title), resolveItemValue(entry, description),
				URI.create(resolveItemValue(entry, link)));
	}
}
