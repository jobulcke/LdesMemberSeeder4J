package be.jobulcke.ldes.memberseeder.seeder;

import be.jobulcke.ldes.memberseeder.config.MemberSeederProperties;
import be.jobulcke.ldes.memberseeder.services.MemberPoster;
import be.jobulcke.ldes.memberseeder.services.MemberTemplateReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggingEventBuilder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberSeederConfig {
	private static final Logger log = LoggerFactory.getLogger(MemberSeederConfig.class);
	private static final LoggingEventBuilder beanCreatedLoggingEventBuilder = log.atInfo().setMessage("USED MEMBER SEEDER: {}");
	static final String PROPERTY_PREFIX = "member-seeder.state-object-seeding";
	static final String PROPERTY_NAME = "enabled";

	@Bean
	@ConditionalOnProperty(prefix = PROPERTY_PREFIX, name = PROPERTY_NAME, havingValue = "true")
	public MemberSeeder stateObjectSeeder(MemberSeederProperties properties,
	                                      MemberTemplateReader memberTemplateReader,
	                                      MemberPoster memberPoster) {
		beanCreatedLoggingEventBuilder.addArgument(StateObjectSeeder.class.getSimpleName()).log();
		return new StateObjectSeeder(
				properties.getStateObjectRanges(),
				memberTemplateReader.readMemberTemplate(properties.getMemberTemplate()),
				memberPoster
		);
	}

	@Bean
	@ConditionalOnProperty(prefix = PROPERTY_PREFIX, name = PROPERTY_NAME, havingValue = "false", matchIfMissing = true)
	public MemberSeeder versionObjectSeeder(MemberSeederProperties properties,
	                                        MemberTemplateReader memberTemplateReader,
	                                        MemberPoster memberPoster) {
		beanCreatedLoggingEventBuilder.addArgument(VersionObjectSeeder.class.getSimpleName()).log();
		return new VersionObjectSeeder(
				properties.getMemberRange(),
				memberTemplateReader.readMemberTemplate(properties.getMemberTemplate()),
				memberPoster
		);
	}
}
