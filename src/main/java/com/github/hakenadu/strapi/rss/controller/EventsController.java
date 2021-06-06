package com.github.hakenadu.strapi.rss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.hakenadu.strapi.rss.model.Event;
import com.github.hakenadu.strapi.rss.service.events.EventsService;

@RestController
@RequestMapping("/events")
public class EventsController {

	@Autowired
	private EventsService eventsService;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createEvent(final @RequestBody Event payload) {
		eventsService.createEvent(payload);
	}
}
