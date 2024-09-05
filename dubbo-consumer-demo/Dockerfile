FROM openjdk:11
ENV TZ Asia/Shanghai
MAINTAINER Zhiguo.Chen<chenzhiguo@jd.com>
USER root

# 临时模拟，正式环境需去掉
#ENV JMESH_SERVICE_APP=jmsf-provider-demo

WORKDIR /export/servers/
COPY ./target/deploy-package/ /export/servers/
#RUN /bin/bash -c 'for f in /export/servers/*; do if [ ${f##*.} == 'zip' ];then unzip -q -d /export/servers/ $f; fi; done'
#RUN chmod +x /export/servers/bin/*.sh

VOLUME ["/export/servers/logs", "/export/servers/config"]
#EXPOSE 8080
#ENTRYPOINT ["/export/servers/bin/startup.sh"]
ENTRYPOINT ["java","-jar", "/export/servers/dubbo-consumer-demo.jar"]
