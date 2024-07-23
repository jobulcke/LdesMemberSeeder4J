package be.vlaanderen.informatievlaanderen.ldes.memberseeder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Bean
	public WebClient ldesServerClient(MemberSeederConfig memberSeederConfig) {
		return WebClient.builder()
				.baseUrl(memberSeederConfig.getLdesServer().getHost())
				.build();
	}
}
