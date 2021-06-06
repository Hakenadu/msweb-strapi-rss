package de.mseiche.msweb.rss.model.feed;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class Channel {

	@JacksonXmlProperty
	private Image image;

	@JacksonXmlProperty
	@Value("${rss.channel.title}")
	private String title;

	@JacksonXmlProperty
	@Value("${rss.channel.description}")
	private String description;

	@JacksonXmlProperty
	@Value("${rss.channel.link}")
	private URI link;

	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Item> item;

	public Channel(final Image image, final String title, final String description, final URI link,
			final List<Item> item) {
		super();
		this.image = image;
		this.title = title;
		this.description = description;
		this.link = link;
		this.item = item;
	}

	public void addItem(final Item item) {
		if (this.item == null) {
			this.item = new LinkedList<>();
		}
		this.item.add(item);
	}

	public void setItem(final List<Item> item) {
		this.item = item;
	}
}
