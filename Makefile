version=1.3.9-SNAPSHOT
#version=1.3.9-SNAPSHOT-$(shell git rev-parse --short HEAD)

all: clean build image-consumer image-provider docker-login push-consumer push-provider undeploy-consumer undeploy-provider deploy-provider deploy-consumer

all-push: clean build image-consumer image-provider docker-login push-consumer push-provider

all-consumer: clean build image-consumer docker-login push-consumer

all-provider: clean build image-provider docker-login push-provider

clean:
	mvnd clean -f ../pom.xml

build:
	mvnd package -f ../pom.xml -DskipTests=true package -P artifactory,test

#mvn k8s:build -f ./jmsf-dubbo-consumer-demo/pom.xml
image-consumer:
	docker build --platform linux/amd64 -t hub.jdcloud.com/jmsf/jmsf-dubbo-consumer-pro:${version}-AMD64 ./jmsf-dubbo-consumer-demo
image-consumer-arm:
	docker build --platform linux/arm64 -t hub.jdcloud.com/mesh/jmsf-dubbo-consumer-pro:${version}-ARM64 -f ./jmsf-dubbo-consumer-demo/Dockerfile-ARM.dockerfile ./jmsf-dubbo-consumer-demo
#mvn k8s:build -f ./jmsf-dubbo-provider-demo/pom.xml
image-provider:
	docker build --platform linux/amd64 -t hub.jdcloud.com/jmsf/jmsf-dubbo-provider-pro:${version}-AMD64 ./jmsf-dubbo-provider-demo
image-provider-arm:
	docker build --platform linux/arm64 -t hub.jdcloud.com/mesh/jmsf-dubbo-provider-pro:${version}-ARM64 -f ./jmsf-dubbo-provider-demo/Dockerfile-ARM.dockerfile ./jmsf-dubbo-provider-demo
docker-login:
	docker login hub-pub.jdcloud.com -u 'jrwangwei3' -p 'Jmsf1234'

push-consumer:
	docker push hub.jdcloud.com/jmsf/jmsf-dubbo-consumer-pro:${version}-AMD64
push-consumer-arm:
	docker push hub.jdcloud.com/mesh/jmsf-dubbo-consumer-pro:${version}-ARM64
push-provider:
	docker push hub.jdcloud.com/jmsf/jmsf-dubbo-provider-pro:${version}-AMD64
push-provider-arm:
	docker push hub.jdcloud.com/mesh/jmsf-dubbo-provider-pro:${version}-ARM64

undeploy-consumer:
	mvn k8s:undeploy -f ./jmsf-dubbo-consumer-demo/pom.xml

undeploy-provider:
	mvn k8s:undeploy -f ./jmsf-dubbo-provider-demo/pom.xml

deploy-consumer:
	kubectl apply -f ./kubernetes/dubbo-consumer-pro.yml

deploy-provider:
	kubectl apply -f ./kubernetes/dubbo-provider-pro.yml