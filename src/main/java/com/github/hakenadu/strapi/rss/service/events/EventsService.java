package com.github.hakenadu.strapi.rss.service.events;

import com.github.hakenadu.strapi.rss.model.Event;

public interface EventsService {

	void createEvent(Event event);
}
