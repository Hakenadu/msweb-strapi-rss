package com.github.hakenadu.strapi.rss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.github.hakenadu.strapi.rss.model.feed.Rss;
import com.github.hakenadu.strapi.rss.service.feed.ItemFactory;

@EnableConfigurationProperties({ Rss.class, ItemFactory.class })
@SpringBootApplication
@ComponentScan
public class Application extends SpringApplication {

	public static void main(final String[] args) {
		run(Application.class, args);
	}
}
