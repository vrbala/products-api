Development Notes
=====================

* To have launchd start postgresql now and restart at login:
  brew services start postgresql
* Or, if you don't want/need a background service you can just run:
  pg_ctl -D /usr/local/var/postgres start


template1=# create role products LOGIN password 'stcudorp231';
CREATE ROLE
template1=# create database products encoding 'utf8' owner products;
CREATE DATABASE

Tables:
=====================

product 
	id: text
	name: text
	model_number: text
	product_type: text
	meta_data: json
		page_title: text
		site_name: text
		description: text
		keywords: text
		canonical: text
	pricing_information: json
		standard_price: float
		standard_price_no_vat: float
		currentPrice: float
	product_description: json
		title: text
		subtitle: text
		text: text
		
Endpoints:
===============
/api/v1/product
	- POST
	- PUT
	- GET 
	
/api/v1/product/<id>
	- GET
	
/api/v1/product/?count=<>&skip=<>
	- GET


Docker commands:
===================

docker build .
docker run  -it -p 9000:9000 60cd7e1e917f

docker run -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=`docker-machine ip \`docker-machine active\`` --env ADVERTISED_PORT=9092 spotify/kafka

docker-compose up --force-create
docker-compose down
docker volume ls
docker volume rm ...

docker exec -it f65627971f2a bash

port mapping provided in docker compose config is only to external world. Internally, the docker compose sets up the network with the configured ports.

docker images
docker image ...




On Mac:
==============

spring.datasource.url=jdbc:postgresql://host.docker.internal:5432/products
spring.kafka.consumer.bootstrap-servers=host.docker.internal:9092
spring.kafka.producer.bootstrap-servers=host.docker.internal:9092
