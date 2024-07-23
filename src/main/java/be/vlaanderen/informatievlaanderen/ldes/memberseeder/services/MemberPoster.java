package be.vlaanderen.informatievlaanderen.ldes.memberseeder.services;

import be.vlaanderen.informatievlaanderen.ldes.memberseeder.MemberSeeder;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.MemberSeederConfig;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.entities.Member;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MemberPoster {
	private final WebClient serverClient;
	private final MemberSeederConfig config;

	public MemberPoster(WebClient serverClient, MemberSeederConfig config) {
		this.serverClient = serverClient;
		this.config = config;
	}

	public void postMember(Member member) {
		serverClient
				.post()
				.uri(config.getLdesServer().getCollectionUri())
				.contentType(MediaType.valueOf(config.getRdfFormat()))
				.body(BodyInserters.fromValue(member.model()))
				.exchangeToMono(response -> {
					if (response.statusCode().is2xxSuccessful()) {
						return response.bodyToMono(String.class);
					} else {
						throw new RuntimeException(String.valueOf(member.id()));
					}
				}).block();
	}
}
