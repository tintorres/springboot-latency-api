package monitoring;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;


@RestController
public class LatencyController {

    private final Counter requestCounter;
    private final Timer latencyTimer;

    public LatencyController(MeterRegistry registry) {

        this.requestCounter = Counter.builder("api_request_total")
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
                    "service", "springboot-latency-api v0.1",
                    "status", "success",
                    "message", "Hello World"
            );
        });
    }

    @GetMapping("/hit")
    public Map<String, Object> hit() throws Exception {

        return latencyTimer.record(() -> {

            requestCounter.increment();

            int latencyMs = ThreadLocalRandom.current()
                    .nextInt(100, 1000);

            try {
                Thread.sleep(latencyMs);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return Map.of(
                    "hit", "success",
                    "latencyMs", latencyMs
            );
        });
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return latencyTimer.record(() -> {

            requestCounter.increment();

            int latencyMs = ThreadLocalRandom.current()
                    .nextInt(100, 1000);

            try {
                Thread.sleep(latencyMs);
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