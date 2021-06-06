package de.mseiche.msweb.rss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import de.mseiche.msweb.rss.model.feed.Rss;
import de.mseiche.msweb.rss.service.feed.ItemFactory;

@EnableConfigurationProperties({ Rss.class, ItemFactory.class })
@SpringBootApplication
@ComponentScan
public class Application extends SpringApplication {

	public static void main(final String[] args) {
		run(Application.class, args);
	}
}
