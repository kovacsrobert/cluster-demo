package hello.client.random;

import static hello.util.EnvironmentUtils.getConfiguration;
import static hello.util.EnvironmentVariables.WEB_URL;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RandomClientConfig {

	@Bean
	public RestTemplateBuilder restTemplateBuilder() {
		return new RestTemplateBuilder()
				.rootUri(getConfiguration(WEB_URL, "http://cluster-web:8080"));
	}

	@Bean
	public RestTemplate restTemplate() {
		return restTemplateBuilder().build();
	}
}
