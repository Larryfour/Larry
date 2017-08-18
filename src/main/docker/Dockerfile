FROM registry.xuebastudy.com/java:8

MAINTAINER ZhaoJin <zhaojin@xuebaclass.com>

VOLUME /tmp

ENV USER_NAME sato

ENV APP_HOME /opt/$USER_NAME

RUN \
useradd -ms /bin/bash $USER_NAME && \
mkdir -p $APP_HOME

ADD sales-crm.jar ${APP_HOME}/sales-crm.jar

RUN \
chown $USER_NAME $APP_HOME/sales-crm.jar && \
bash -c 'touch ${APP_HOME}/sales-crm.jar'

ENV JAVA_TOOL_OPTIONS "-Xms128M -Xmx256M -Djava.awt.headless=true -Djava.security.egd=file:/dev/./urandom"

USER $USER_NAME

WORKDIR $APP_HOME

HEALTHCHECK --timeout=3s CMD curl --fail http://localhost:8080/sato/health || exit 1

ENTRYPOINT ["java", "-jar", "sales-crm.jar"]

# Run as:
# docker run -idt -p 8701:8701 -e appPort=8701 registry.xuebastudy.com/sato/sales-crm:latest
