package com.github.hakenadu.strapi.rss.service.feed;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.hakenadu.strapi.rss.model.feed.Rss;

public interface FeedService {

	public Rss getFeed();

	public void updateFeed(ObjectNode objectNode);
}
