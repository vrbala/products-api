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
