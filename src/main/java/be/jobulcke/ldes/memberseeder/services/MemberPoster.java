package be.jobulcke.ldes.memberseeder.services;

import be.jobulcke.ldes.memberseeder.config.LdesServerProperties;
import be.jobulcke.ldes.memberseeder.entities.Member;
import be.jobulcke.ldes.memberseeder.exceptions.MemberPostFailedException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MemberPoster {
	private final WebClient serverClient;
	private final LdesServerProperties config;

	public MemberPoster(WebClient serverClient, LdesServerProperties config) {
		this.serverClient = serverClient;
		this.config = config;
	}

	public void postMember(Member member) {
		serverClient
				.post()
				.uri(config.getCollectionUri())
				.contentType(MediaType.valueOf(config.getRdfFormat()))
				.body(BodyInserters.fromValue(member.model()))
				.exchangeToMono(response -> {
					if (response.statusCode().is2xxSuccessful()) {
						return response.bodyToMono(String.class);
					} else {
						throw new MemberPostFailedException(member.id(), response.statusCode().value());
					}
				}).block();
	}
}
