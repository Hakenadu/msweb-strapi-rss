package de.mseiche.msweb.rss.model.feed;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * Minimal example (see
 * <a href="https://www.w3schools.com/xml/xml_rss.asp">w3schools
 * documentation</a>)
 */
@JacksonXmlRootElement
@ConfigurationProperties("rss")
@ConstructorBinding
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class Rss {

	@JacksonXmlProperty(isAttribute = true)
	private final String version = "2.0";

	@JacksonXmlProperty
	private final Channel channel;

	public Rss(final Channel channel) {
		super();
		this.channel = channel;
	}

	public Channel getChannel() {
		return channel;
	}
}
