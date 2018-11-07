FROM java

# Add jar file
ARG JAR_FILE
#ENV会覆盖ARG
ENV JAR_FILE=${JAR_FILE}
ADD target/${JAR_FILE} /opt/${JAR_FILE}

CMD ["/bin/bash", "-c", "java -jar /opt/${JAR_FILE}"]
