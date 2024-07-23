package be.vlaanderen.informatievlaanderen.ldes.memberseeder;

import be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.MemberSeederConfig;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.services.MemberPoster;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.services.MemberSeederLogger;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.services.MemberTemplateReader;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.valueobjects.MemberTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class MemberSeeder implements CommandLineRunner {
	private final MemberSeederLogger logger = new MemberSeederLogger();
	private final MemberSeederConfig config;
	private final MemberTemplate memberTemplate;
	private final MemberPoster memberPoster;

	public MemberSeeder(MemberSeederConfig config, MemberTemplateReader memberTemplateReader, MemberPoster memberPoster) {
		this.config = config;
		this.memberTemplate = memberTemplateReader.readMemberTemplate(config.getMemberTemplate());
		this.memberPoster = memberPoster;
	}

	@Override
	public void run(String... args) {
		logger.start();

		IntStream.range(config.getBoundaries().getInclusiveStart(), config.getBoundaries().getExclusiveEnd())
				.forEach(index -> {
					memberPoster.postMember(memberTemplate.createMember(index));
					logger.incrementAndLog();
				});

		logger.finalLogAndClear();
	}


}
