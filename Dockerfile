# --------------------------------------------------------------------------------
# BUILD
# --------------------------------------------------------------------------------
FROM maven:3.8.1-adoptopenjdk-16 AS build

WORKDIR /src
COPY . .
RUN mvn package spring-boot:repackage

# --------------------------------------------------------------------------------
# DISTRIBUTION
# --------------------------------------------------------------------------------
FROM adoptopenjdk:16-jre-hotspot-focal

RUN adduser --disabled-password --gecos '' rss
USER rss
WORKDIR /home/rss

COPY --from=build /src/target/strapi-rss-feed-provider.jar ./strapi-rss-feed-provider.jar

EXPOSE 8080

CMD ["java","-jar", "strapi-rss-feed-provider.jar"]
