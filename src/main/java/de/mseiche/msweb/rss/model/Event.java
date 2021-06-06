package de.mseiche.msweb.rss.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * <pre>
 *	{
 *		"event": "entry.create",
 *		"model": "post",
 *		"entry": {
 *			"id": 1,
 *			"title": "java.net.Socket Example"
 *		}
 *	}
 * </pre>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Event {

	private final String event;
	private final String model;
	private final ObjectNode entry;

	@JsonCreator(mode = Mode.PROPERTIES)
	public Event(final @JsonProperty("event") String event, final @JsonProperty("model") String model,
			final @JsonProperty("entry") ObjectNode entry) {
		this.event = event;
		this.model = model;
		this.entry = entry;
	}

	public String getEvent() {
		return event;
	}

	public String getModel() {
		return model;
	}

	public ObjectNode getEntry() {
		return entry;
	}
}
