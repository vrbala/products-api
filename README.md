# Products API

A simple product management API.

## System requirements
- JDK1.8+
- Kafka
- PostgreSQL

## Deployment
The service is self-contained and can run in dockerize-env. It brings up 
1. postgres from a standard image with some sample data (in addition to those provided by `_sample` endpoint above
2. single node kafka from image built for this application and available in [(public domain)](https://cloud.docker.com/u/balavr/repository/docker/balavr/kafka) now 
3. single node ultraboost and single instance powerboost (these have to be built and available in local. Instructions below on how to build them.)

### To build and run
From top level directory,
```bash
$ mvn -Dmaven.test.skip=true install 
$ docker-compose build 
$ docker-compose up -d
```

If you want to develop using local instances of postgresql and kafka without using docker, update right hostnames 
and ports in
[powerboost configuration](https://github.com/vrbala/products-api/blob/master/powerboost/application.properties)
and [ultraboost configuration](https://github.com/vrbala/products-api/blob/master/ultraboost/application.properties).


# Components

## products library
Core library providing the domain model, persistence and messaging support.

## ultraboost
Component providing the REST API. Currently supports reads and writes. Writes can be configured to be synchronous or async.

### GET
- `/api/v1/products` - List of all `Product`s in the system retrieved in chronological order of their creation time. 
Use this only when you need complete snapshot of products. For better performance and control, use the next URL with 
page number and number of records limits.
```bash
$ curl -X GET \
  http://localhost:9000/api/v1/products
```

- `/api/v1/products?page={pageNumber}&limit={numRecords}` - List `numRecords` `Product`s from page `pageNumber` in same order as above.
```bash
$ curl -X GET \
  'http://localhost:9000/api/v1/products?limit=2&page=1'
```

- `/api/v1/product/{productId}` - Returns a single `Product` record.
```bash
$ curl -X GET \
  http://localhost:9000/api/v1/product/AB7091
```

### POST
- `/api/v1/product` - Accepts a `json` body of `Product` information conforming to the json schema below and sends to messaging for persistence.
```bash
$ curl -X POST \
    http://localhost:9000/api/v1/product \
    -H 'Content-Type: application/json' \
    -d '{
  "id": "AB7091",
  "name": "Nite Jogger Shoes",
  "model_number": "BTO97",
  "product_type": "inline",
  "meta_data": {
  "page_title": "adidas Nite Jogger Shoes - Black | adidas UK",
  "site_name": "adidas United Kingdom",
  "description": "Shop for Nite Jogger Shoes - Black at adidas.co.uk! See all the styles and colours of Nite Jogger Shoes - Black at the official adidas UK online store.",
  "keywords": "Nite Jogger Shoes",
  "canonical": "//www.adidas.co.uk/nite-jogger-shoes/CG7088.html"
  },
  "pricing_information": {
  "standard_price": 199.95,
  "standard_price_no_vat": 99.96,
  "currentPrice": 199.95
  },
  "product_description": {
  "title": "Nite Jogger Shoes",
  "subtitle": "Modern cushioning updates this flashy '\''80s standout.",
  "text": "Inspired by the 1980 Nite Jogger, these shoes shine bright with retro style and reflective details. The mesh and nylon ripstop upper is detailed with suede overlays. An updated Boost midsole adds responsive cushioning for all-day comfort."
  }
  }
  '
```

### Admin endpoints (To be consumed for monitoring and orchestration purposes)
- `/api/v1/_ping` - Responds with `200 OK` when the service is ready to respond. 
```bash
$ curl -X GET \
  http://localhost:9000/api/v1/_ping

```
- `/api/v1/_health` - Does a complete health check of the system my sending a message to queue and make sure it is persisted in reasonable time. This is a process intensive endpoint and should be used judiciously.
```bash
$ curl -X GET \
  http://localhost:9000/api/v1/_health
```

- `/api/v1/_sample` - Mostly to bootstrap development by populating some synthetic data into the system.
```bash
$ curl -X POST \
  http://localhost:9000/api/v1/_sample
```

## powerboost
Ingester to providing persistence service, processing incoming messages and updating storage. For scalability, desired read models can be built into read optimized data storaged in this component itself.


## Product json schema 
```javascript
{
  "type":"object",
  "properties": {
    "id": {
      "type": "string"
    },
    "name": {
      "type": "string"
    },
    "model_number": {
      "type": "string"
    },
    "product_type": {
      "type": "string"
      },
    "meta_data": {
      "type": "object",
      "properties": {
        "page_title": {
          "type": "string"
        },
        "site_name": {
          "type": "string"
        },
        "description": {
          "type": "string"
        },
        "keywords": {
          "type": "string"
        },
        "canonical": {
          "type": "string"
        }
      }
    },
    "pricing_information": {
      "type": "object",
      "properties": {
        "standard_price": {
          "type": "number"
        },
        "standard_price_no_vat": {
          "type": "number"
        },
        "currentPrice": {
          "type": "number"
        }        
      }
    },
    "product_description": {
      "type": "object",
      "properties": {
        "title": {
          "type": "string"
        },
        "subtitle": {
          "type": "string"
        },
        "text": {
          "type": "string"
        }
      }
    }
  }
}
```

Copyright (c) Bala Raman, 2019.
