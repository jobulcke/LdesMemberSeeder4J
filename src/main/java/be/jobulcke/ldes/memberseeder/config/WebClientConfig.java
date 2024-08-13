package be.jobulcke.ldes.memberseeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient ldesServerClient(LdesServerProperties ldesServerProperties) {
		return WebClient.builder()
				.baseUrl(ldesServerProperties.getHost())
				.build();
	}
}
