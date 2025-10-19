package com.calhan.springapigateway;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Bucket4jGatewayFilter implements GatewayFilter {

    private static final Log log = LogFactory.getLog(Bucket4jGatewayFilter.class);

    private final AtomicReference<Bucket> bucketRef = new AtomicReference<>();

    private int capacity;
    private int refillTokens;
    private int refillPeriod;
    private TimeUnit refillUnit;

    public Bucket4jGatewayFilter setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Bucket4jGatewayFilter setRefillTokens(int refillTokens) {
        this.refillTokens = refillTokens;
        return this;
    }

    public Bucket4jGatewayFilter setRefillPeriod(int refillPeriod) {
        this.refillPeriod = refillPeriod;
        return this;
    }

    public Bucket4jGatewayFilter setRefillUnit(TimeUnit refillUnit) {
        this.refillUnit = refillUnit;
        return this;
    }

    private Bucket getBucket() {
        if (bucketRef.get() == null) {
            synchronized (bucketRef) {
                if (bucketRef.get() == null) {
                    Duration refillDuration = Duration.ofMillis(refillUnit.toMillis(refillPeriod));
                    Refill refill = Refill.intervally(refillTokens, refillDuration);
                    Bandwidth limit = Bandwidth.classic(capacity, refill);
                    bucketRef.set(Bucket.builder().addLimit(limit).build());
                }
            }
        }
        return bucketRef.get();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Bucket bucket = getBucket();
        log.debug("Bucket4j available tokens: " + bucket.getAvailableTokens());

        if (bucket.tryConsume(1)) {
            return chain.filter(exchange);
        } else {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            return exchange.getResponse().setComplete();
        }
    }
}
