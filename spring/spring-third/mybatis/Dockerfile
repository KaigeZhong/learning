FROM java

# Add jar file
ARG JAR_FILE
ADD target/${JAR_FILE} /opt/${JAR_FILE}
RUN ln -s /opt/${JAR_FILE} /opt/my-server.jar

ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/my-server.jar"]
#CMD ["-jar", "/opt/my-server.jar", "--spring.profiles.active=dev"]
