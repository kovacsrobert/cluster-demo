package hello.client.random;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RandomClient {

	private static final Logger logger = LogManager.getLogger(RandomClient.class);

	private final RestTemplate restTemplate;
	private final String prefix;

	public RandomClient(RestTemplate restTemplate, String prefix) {
		this.restTemplate = restTemplate;
		this.prefix = prefix;
	}

	public void startCalls() {
		while (!Thread.interrupted()) {
			fetchWelcomeMessage();
			sleep(1);
		}
	}

	private void fetchWelcomeMessage() {
		final String randomSuffix = RandomStringUtils.randomAlphabetic(2);
		final String value = prefix + "-" + randomSuffix;
		try {
			ResponseEntity<String> response = restTemplate.getForEntity("/hello/{name}", String.class, value);
			logger.info("Calling for '{}', response was: {}", value, response.getBody());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	private void sleep(int timeoutInSeconds) {
		try {
			TimeUnit.SECONDS.sleep(timeoutInSeconds);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}
}
