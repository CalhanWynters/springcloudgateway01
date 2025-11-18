# springcloudgateway01

Used the official version for GatewayRoutes from github:
https://github.com/spring-cloud/spring-cloud-gateway/blob/main/spring-cloud-gateway-sample/src/main/java/org/springframework/cloud/gateway/sample/GatewaySampleApplication.java

Migrated rate limiting library to bucket4j... 
    link: https://github.com/bucket4j/bucket4j
    Reference https://github.com/bbeck/token-bucket/tree/master for replacing methods.

Wish List:
    > **WIP** Rate Limiting and Throttling: Controlling the number of requests per user or period to prevent abuse and protect backend services from becoming overwhelmed by traffic spikes.
    > Change rate limiting features from just global rate limiting to hybrid global and per client based rate limiting
    > Input Validation: Validating incoming request payloads to ensure only correctly formatted data enters the system.
    > SSL/TLS Offloading: The gateway should handle the encryption and decryption of traffic (SSL termination), an intensive task that frees up backend services.
    > Threat Protection: It should provide protection against common web exploits and threats like Distributed Denial of Service (DDoS) attacks, SQL injection, and parameter tampering. Integration with Web Application Firewalls (WAFs) is a          key feature.
    > Routing and Composition: Routing requests to the appropriate backend services (often multiple microservices) and aggregating the responses into a single, unified response for the client.
    > Load Balancing (Service Level) and Scalability: Distributing traffic across multiple instances of a service to ensure high availability and supporting auto-scaling based on demand.
    > Protocol Translation: The ability to translate between different protocols and data formats (e.g., SOAP to REST, or XML to JSON) to support diverse or legacy systems.
    > Caching: Storing responses for frequently accessed data to reduce latency and the load on backend services.
    > Resiliency and Fault Tolerance: Implementing patterns like circuit breakers and automatic retries to handle backend service failures gracefully and ensure system stability. 
    > Monitoring and Logging: Tracking key metrics such as request counts, response times, and error rates, and providing unified logging for all API activities.
    > Tracing: Integrating with tracing tools (like AWS X-Ray) to trace requests end-to-end through complex microservice environments.
    > API Lifecycle Management: Features to manage different API versions and safely roll out changes (e.g., canary releases) without disrupting existing clients.
    > Mocking: The ability to return static, mock responses during the development phase to reduce dependencies between teams.
    > Integration with DevOps Tools: Seamless integration with existing cloud provider services, Kubernetes, and CI/CD pipelines to streamline deployment and management. 

Modules:
    > api_gateway_module
    > gate_security_module
    > infrastructure
    > general_service_plugin
        - maintain_module
        - ansible_module
        - logging_module
        - kafka_module
        - infrastructure

        
