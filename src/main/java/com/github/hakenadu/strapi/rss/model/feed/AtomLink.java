package com.github.hakenadu.strapi.rss.model.feed;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Component
public final class AtomLink {

	@JacksonXmlProperty(isAttribute = true)
	@Value("${rss.channel.atom-link.href}")
	private URI href;

	@JacksonXmlProperty(isAttribute = true)
	private final String rel = "self";

	@JacksonXmlProperty(isAttribute = true)
	private final String type = MediaType.APPLICATION_RSS_XML_VALUE;
}
