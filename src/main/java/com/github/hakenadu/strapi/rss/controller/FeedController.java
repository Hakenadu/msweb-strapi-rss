package com.github.hakenadu.strapi.rss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hakenadu.strapi.rss.model.feed.Rss;
import com.github.hakenadu.strapi.rss.service.feed.FeedService;

@RestController
@RequestMapping("/feed")
public class FeedController {

	@Autowired
	private FeedService feedService;

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public Rss getFeed() {
		return feedService.getFeed();
	}
}
