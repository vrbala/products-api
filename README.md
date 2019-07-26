# Products API

A simple product management API.

[![Build Status](https://travis-ci.com/vrbala/products-api.svg?branch=master)](https://travis-ci.com/vrbala/products-api)


# Components

## products library
Core library providing the domain model, persistence and messaging support.

## ultraboost
Component providing the REST API. Currently supports reads and writes. Writes can be configured to be synchronous or async.

### GET
- `/api/v1/products` - List of all `Product`s in the system retrieved in chronological order of their creation.
- `/api/v1/products?page={pageNumber}&limit={numRecords}` - List `numRecords` `Product`s from page `pageNumber` in same order as above.
- `/api/v1/product/{productId}` - Returns a single `Product` record.

### POST
- `/api/v1/product` - Accepts a `json` body of `Product` information conforming to the json schema below and sends to messaging for persistence.

### Admin endpoints (Can be consumed by a cloud orchestrator)
- `/api/v1/_ping` - Responds with `200 OK` when the service is ready to respond. 
- `/api/v1/_health` - Does a complete health check of the system my sending a message to queue and make sure it is persisted in reasonable time. This is a process intensive endpoint and should be used judiciously.
- `/api/v1/_sample` - Mostly to bootstrap development by populating some synthetic data into the system.


## powerboost
Ingester to providing persistence service, processing incoming messages and updating storage. For scalability, desired read models can be built into read optimized data storaged in this component itself.

## Deployment
The service is self-contained and can run in dockerize-env. It brings up 
1. postgres from a standard image with some sample data (in addition to those provided by `_sample` endpoint above
2. single node kafka (built for this application and available in public domain now) 
3. single node ultraboost and single instance powerboost (these have to be built and available in local. Instructions below on how to build them.)

### To build and run
From top level directory,
```bash
$ mvn install 
$ docker-compose up -d
```


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
