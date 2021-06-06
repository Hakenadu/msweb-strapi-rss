package de.mseiche.msweb.rss.service.feed;

import com.fasterxml.jackson.databind.node.ObjectNode;

import de.mseiche.msweb.rss.model.feed.Rss;

public interface FeedService {

	public Rss getFeed();

	public void updateFeed(ObjectNode objectNode);
}
