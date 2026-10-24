package monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class LatencyController {

    private final Counter requestCounter;
    private final Timer latencyTimer;

    @Value("${latency.min-ms}")
    private int minLatencyMs;

    @Value("${latency.max-ms}")
    private int maxLatencyMs;

    public LatencyController(MeterRegistry registry) {

        this.requestCounter = Counter.builder("springboot_latency_api_request_total")
                .description("Total API requests")
                .register(registry);

        this.latencyTimer = Timer.builder("api_request_duration")
                .description("API latency")
                .register(registry);
    }

    @GetMapping("/")
    public Map<String, String> hello() {

        return latencyTimer.record(() -> {

            requestCounter.increment();

            return Map.of(
                    "service", "springboot-latency-api v0.2",
                    "status", "success",
                    "message", "Hello World"
            );
        });
    }

    @GetMapping("/hitGet")
    public Map<String, Object> hitGet() throws Exception {

        return latencyTimer.record(() -> {

            requestCounter.increment();

            int latencyMs = ThreadLocalRandom.current()
                    .nextInt(minLatencyMs, maxLatencyMs + 1);

            try {
                Thread.sleep(latencyMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return Map.of(
                    "hitGet", "success",
                    "latencyMs", latencyMs
            );
        });
    }
    
    @PostMapping("/hitPost")
    public Map<String, Object> HitPost(@RequestBody String entity) throws Exception {
                
         return latencyTimer.record(() -> {

            requestCounter.increment();

            int latencyMs = ThreadLocalRandom.current()
                    .nextInt(minLatencyMs, maxLatencyMs + 1);

            try {
                Thread.sleep(latencyMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return Map.of(
                    "hitPost", "success",
                    "latencyMs", latencyMs,
                    "entity", entity    
            );
        });
    }
    

    int getRandomLatency() {
        return ThreadLocalRandom.current()
                .nextInt(minLatencyMs, maxLatencyMs + 1);
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return latencyTimer.record(() -> {

            requestCounter.increment();

            int latencyMs = ThreadLocalRandom.current()
                    .nextInt(minLatencyMs, maxLatencyMs + 1);

            try {
                Thread.sleep( latencyMs );
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return Map.of(
                    "service", "springboot-latency-api",
                    "status", "UP",
                    "latencyMs", latencyMs                    
            );
        });
    }
    
}