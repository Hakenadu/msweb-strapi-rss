package com.github.hakenadu.strapi.rss.service.feed;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Iterator;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hakenadu.strapi.rss.model.feed.AtomLink;
import com.github.hakenadu.strapi.rss.model.feed.Rss;

/**
 * this class is a monitor!
 */
@Service
public class FeedServiceImpl implements FeedService {

	@Autowired
	private Rss feed;

	@Autowired
	private AtomLink atomLink;

	@Autowired
	private ItemFactory itemFactory;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${strapi.collection-url}")
	private URI strapiCollectionUrl;

	/**
	 * initially we'll load all existent posts into memory
	 */
	@PostConstruct
	private void initialize() {
		this.feed.getChannel().setAtomLink(atomLink);

		final HttpRequest request = HttpRequest.newBuilder(strapiCollectionUrl)
				.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE).build();

		final ArrayNode collection;
		try {
			collection = HttpClient.newHttpClient().send(request, new JsonBodyHandler<>(objectMapper, ArrayNode.class))
					.body().get();
		} catch (final IOException | InterruptedException exception) {
			throw new IllegalStateException("error reading initial feeds", exception);
		}

		final Iterator<JsonNode> entryIterator = collection.elements();
		while (entryIterator.hasNext()) {
			final JsonNode entry = entryIterator.next();
			if (!entry.isObject()) {
				throw new IllegalStateException("read entry is not of type object");
			}
			this.updateFeed((ObjectNode) entry);
		}
	}

	@Override
	public synchronized Rss getFeed() {
		return feed;
	}

	@Override
	public synchronized void updateFeed(final ObjectNode objectNode) {
		feed.getChannel().addItem(itemFactory.create(objectNode));
	}
}
