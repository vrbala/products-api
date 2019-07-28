# TODO

### 1. Edge service 
a. Currently, ultraboost doesn't have any security features implemented. 
Idea is implement them in a dedicated edge service providing authentication, 
authorization, SSL termination, 
rate limiting, request routing etc., 

### 2. Decompose ultraboost
a. Currently, ultraboost serves read and write requests from same component. 
It could benefit from decomposing into read and write components so they can be scaled
independently based on requirement.  

### 3. GraphQL based read API
a. In order to support low latency reads and optimize network bandwidth usage
during data transmission, the read endpoints could implement GraphQL backed by high 
performance read optimized data stores (Eg: ElasticSearch).

### 4. Caching
  
  
# CI/CD
The repository is already integrated with travis for CI. If we should use on-premise CI, 
we could use drone on-premise. 

For continuous delivery, there are no thoughts yet. 
  
# Testing

For integration testing, it could be setup based on Jenkins/Node stack. 