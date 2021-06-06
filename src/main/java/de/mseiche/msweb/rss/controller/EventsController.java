package de.mseiche.msweb.rss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.mseiche.msweb.rss.model.Event;
import de.mseiche.msweb.rss.service.events.EventsService;

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
