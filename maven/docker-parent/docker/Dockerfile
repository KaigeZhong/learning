FROM java
MAINTAINER Keven Zhong

# Add jar file
ARG JAR_FILE
ADD target/${JAR_FILE} /opt/${JAR_FILE}
RUN ln -s /opt/${JAR_FILE} /opt/my-server.jar
##You can use the exec form of ENTRYPOINT to set fairly stable default commands and arguments
##and then use either form of CMD to set additional defaults that are more likely to be changed.
##If CMD is used to provide default arguments for the ENTRYPOINT instruction, both the CMD and ENTRYPOINT instructions should be specified with the JSON array format.
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/my-server.jar", "--spring.profiles.active=dev"]
#CMD ["-jar", "/opt/my-server.jar", "--spring.profiles.active=dev"]
