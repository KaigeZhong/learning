FROM java

# 接收build-args参数JAR_FILE
ARG JAR_FILE
# 将JAR_FILE参数设置到环境变量当中，以供后面CMD执行时使用
# ENV会覆盖ARG
ENV JAR_FILE=${JAR_FILE}
ADD target/${JAR_FILE} /opt/${JAR_FILE}

COPY run.sh /opt/
RUN chmod 777 /opt/run.sh
CMD ["/bin/bash", "-c", "/opt/run.sh"]

