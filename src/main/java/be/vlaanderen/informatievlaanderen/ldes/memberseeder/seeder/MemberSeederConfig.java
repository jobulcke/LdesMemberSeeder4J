package be.vlaanderen.informatievlaanderen.ldes.memberseeder.seeder;

import be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.MemberSeederProperties;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.services.MemberPoster;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.services.MemberTemplateReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberSeederConfig {
	private static final Logger log = LoggerFactory.getLogger(MemberSeederConfig.class);

	@Bean
	@ConditionalOnProperty(name = "member-seeder.state-object-seeding.enabled", havingValue = "true")
	public MemberSeeder stateObjectSeeder(MemberSeederProperties properties, MemberTemplateReader memberTemplateReader, MemberPoster memberPoster) {
		log.info("USED MEMBER SEEDER: StateObjectSeeder");
		return new StateObjectSeeder(properties.getStateObjectRange(), memberTemplateReader.readMemberTemplate(properties.getMemberTemplate()), memberPoster);
	}

	@Bean
	@ConditionalOnMissingBean
	public MemberSeeder versionObjectSeeder(MemberSeederProperties properties,
	                                        MemberTemplateReader memberTemplateReader,
	                                        MemberPoster memberPoster) {
		log.info("USED MEMBER SEEDER: VersionObjectSeeder");
		return new VersionObjectSeeder(
				properties.getMemberRange(),
				memberTemplateReader.readMemberTemplate(properties.getMemberTemplate()),
				memberPoster
		);
	}
}
