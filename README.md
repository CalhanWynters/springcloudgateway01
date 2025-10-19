# springcloudgateway01

Used the official version for GatewayRoutes from github:
https://github.com/spring-cloud/spring-cloud-gateway/blob/main/spring-cloud-gateway-sample/src/main/java/org/springframework/cloud/gateway/sample/GatewaySampleApplication.java

I have an issue with the Rate limiter library TokenBucket. It is now dead.
I need to migrate to a well maintained library like resilience4j or bucket4j... 
    Probably will go with bucket4j: https://github.com/bucket4j/bucket4j
    Reference https://github.com/bbeck/token-bucket/tree/master for replacing methods.