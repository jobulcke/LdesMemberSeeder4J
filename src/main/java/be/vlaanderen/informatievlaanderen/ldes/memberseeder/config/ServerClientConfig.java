package be.vlaanderen.informatievlaanderen.ldes.memberseeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ServerClientConfig {
	@Bean
	public WebClient serverClient() {
		return WebClient.builder()
				.baseUrl("http://localhost:8080")
				.build();
	}
}
