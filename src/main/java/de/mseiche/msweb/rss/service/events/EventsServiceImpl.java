package de.mseiche.msweb.rss.service.events;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import de.mseiche.msweb.rss.model.Event;
import de.mseiche.msweb.rss.service.feed.FeedService;

@Service
public class EventsServiceImpl implements EventsService {

	@Autowired
	private FeedService feedService;

	@Value("${strapi.model}")
	private String strapiModel;

	@Override
	public void createEvent(final Event event) {
		LoggerFactory.getLogger(getClass()).info("accepted event of type {}", event.getEvent());

		if (!"entry.create".equals(event.getEvent())) {
			LoggerFactory.getLogger(getClass()).trace("ignored event of type {}", event.getEvent());
			return;
		}

		if (!strapiModel.equals(event.getModel())) {
			LoggerFactory.getLogger(getClass()).trace("ignored creation event for model {}", event.getModel());
			return;
		}

		feedService.updateFeed(event.getEntry());
	}
}
