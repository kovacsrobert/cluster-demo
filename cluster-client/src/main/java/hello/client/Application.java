package hello.client;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import hello.client.random.RandomClient;
import hello.util.LoggingUtil;

@SpringBootApplication(scanBasePackages = { "hello" })
public class Application implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(Application.class);

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoggingUtil loggingUtil;

    private static final AtomicInteger counter = new AtomicInteger(1);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        loggingUtil.fillLoggingContext();

        final String clientPrefix = "client-" + counter.getAndIncrement();

        logger.info("Creating RandomClient with prefix: {}", clientPrefix);

        RandomClient randomClient = new RandomClient(restTemplate, clientPrefix);
        randomClient.startCalls();

        logger.info("Stopping RandomClient with prefix: {}", clientPrefix);

        loggingUtil.removeLoggingContext();
    }
}
