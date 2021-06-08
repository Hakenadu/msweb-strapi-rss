# strapi-rss-feed-provider
Documentation W. I. P.

I'm currently creating a service which may be run via docker that automatically generates rss feeds based on [strapi](https://strapi.io/) collections that are updated via [strapi webhooks](https://strapi.gitee.io/documentation/v3.x/concepts/webhooks.html).

## run via docker-compose
The following is an example docker-compose.yml.
The *ITEM_FACTORY_* prefixed variables are patterns which will be resolved based on the entries of your strapi collection at *STRAPI_COLLECTION_URL*.

```json
version: '3'
services:
  strapi-rss-feed-provider:
    image: hakenadu/strapi-rss-feed-provider
    container_name: strapi-rss-feed-provider
    environment:
      RSS_CHANNEL_ATOM_LINK_HREF: https://api.mseiche.de/rss/feed
      RSS_CHANNEL_TITLE: Manuel Seiche's Blog
      RSS_CHANNEL_DESCRIPTION: On this page I am posting about some implementation stuff. Because I'm much more of a developer than an author, you'll often see code samples or even interactive UI components for demonstration purposes.
      RSS_CHANNEL_LINK: https://mseiche.de/blog
      RSS_CHANNEL_IMAGE_URL: https://strapi.mseiche.dehttps://strapi.mseiche.de/uploads/favicon_primary_21e9ac0793.png
      RSS_CHANNEL_IMAGE_WIDTH: 48
      RSS_CHANNEL_IMAGE_HEIGHT: 48
      RSS_CHANNEL_IMAGE_TITLE: MSeiche Logo
      RSS_CHANNEL_IMAGE_LINK: https://mseiche.de
      RSS_CHANNEL_IMAGE_DESCRIPTION: Manuel Seiche's Website Logo
      STRAPI_MODEL: post
      STRAPI_COLLECTION_URL: https://strapi.mseiche.de/posts
      ITEM_FACTORY_GUID: https://mseiche.de/blog/posts/<</id>>
      ITEM_FACTORY_LINK: https://mseiche.de/blog/posts/<</id>>
      ITEM_FACTORY_TITLE: <</title>>
      ITEM_FACTORY_DESCRIPTION: <</description>>
    ports:
    - 8080:8080
    build:
      context: ./
```

## get generated RSS Feed
**GET** /feed

```xml
<rss version="2.0"
  xmlns:atom="http://www.w3.org/2005/Atom">
  <channel>
    <atom:link href="https://api.mseiche.de/rss/feed" rel="self" type="application/rss+xml"/>
    <image>
      <url>https://strapi.mseiche.dehttps://strapi.mseiche.de/uploads/favicon_primary_21e9ac0793.png</url>
      <title>MSeiche Logo</title>
      <description>Manuel Seiche's Website Logo</description>
      <link>https://mseiche.de</link>
      <width>48</width>
      <height>48</height>
    </image>
    <title>Manuel Seiche's Blog</title>
    <description>On this page I am posting about some implementation stuff. Because I'm much more of a developer than an author, you'll often see code samples or even interactive UI components for demonstration purposes.</description>
    <link>https://mseiche.de/blog</link>
    <item>
      <title>java.net.Socket Example</title>
      <description>An example for the usage of the java.net.Socket class to communicate between client and server using the transmission control protocol</description>
      <link>https://mseiche.de/blog/posts/1</link>
    </item>
    <item>
      <title>Provide RSS Feeds for Strapi Collections</title>
      <description>Live Generation of RSS Feeds using Strapi, Java 16, Spring Boot and Docker</description>
      <link>https://mseiche.de/blog/posts/9</link>
    </item>
  </channel>
</rss>
```

## update RSS Feed via Webhook
Configure Strapi to **POST** against the /events resource for create-Events.
The current feed will be updated threadsafe and the next **GET** against the /feed resource will result in an updated RSS Feed.
