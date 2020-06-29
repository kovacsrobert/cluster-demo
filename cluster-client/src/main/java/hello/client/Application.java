package hello.client;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.RandomStringUtils;
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

        while (!Thread.currentThread().isInterrupted()) {
            String randomText = RandomStringUtils.randomAlphabetic(10);
            logger.info("new random text: {}", randomText);

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }

        loggingUtil.removeLoggingContext();
    }
}
