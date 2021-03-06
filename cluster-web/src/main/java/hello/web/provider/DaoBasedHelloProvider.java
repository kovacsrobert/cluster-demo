package hello.web.provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DaoBasedHelloProvider implements HelloProvider {

	private static final Logger logger = LogManager.getLogger(DaoBasedHelloProvider.class);

	private final RestTemplate restTemplate;

	public DaoBasedHelloProvider(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public String welcome(String name) {
		ResponseEntity<String> daoResponse;

		try {
			daoResponse = restTemplate.getForEntity("/hello/{name}", String.class, name);
		} catch (Exception ex) {
			logger.error("Failed to fetch welcome message for name: {}", name, ex);
			return "";
		}

		logger.debug("welcome message response for name: {}; is: {}", name, daoResponse);
		return daoResponse.getBody();
	}
}
