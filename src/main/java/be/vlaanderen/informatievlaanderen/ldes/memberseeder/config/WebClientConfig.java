package be.vlaanderen.informatievlaanderen.ldes.memberseeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient ldesServerClient(MemberSeederProperties memberSeederProperties) {
		return WebClient.builder()
				.baseUrl(memberSeederProperties.getLdesServer().getHost())
				.build();
	}
}
