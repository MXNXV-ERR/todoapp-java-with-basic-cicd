# Use a base image with JDK 17 installed
FROM maven:3.9.6-eclipse-temurin-17 AS build 

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project descriptor
COPY pom.xml .

# Copy the application source code
COPY src ./src


RUN apt-get update -qqy \
	&& apt-get -qqy install gpg unzip \
	&& wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
	&& echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
	&& apt-get update -qqy \
	&& apt-get -qqy install google-chrome-stable \
	&& rm /etc/apt/sources.list.d/google-chrome.list \
	&& rm -rf /var/lib/apt/lists/* /var/cache/apt/* \
	&& sed -i 's/"$HERE\/chrome"/"$HERE\/chrome" --no-sandbox/g' /opt/google/chrome/google-chrome

RUN wget -q -O /tmp/chromedriver.zip https://storage.googleapis.com/chrome-for-testing-public/122.0.6261.111/linux64/chrome-linux64.zip \
	&& unzip /tmp/chromedriver.zip -d /opt \
	&& rm /tmp/chromedriver.zip \
	&& ln -s /opt/chromedriver-linux64/chromedriver /usr/bin/chromedriver

RUN mvn package 

# Create a lightweight image with JRE to run the application
FROM openjdk:17-slim AS final

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/*.war app.war

# Expose the port on which the application will run
EXPOSE 8091

# Command to run the application when the container starts
CMD ["java", "-jar", "app.war"]
