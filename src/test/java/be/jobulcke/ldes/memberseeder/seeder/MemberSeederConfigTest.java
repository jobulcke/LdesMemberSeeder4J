package be.jobulcke.ldes.memberseeder.seeder;

import be.jobulcke.ldes.memberseeder.config.MemberSeederProperties;
import be.jobulcke.ldes.memberseeder.services.MemberPoster;
import be.jobulcke.ldes.memberseeder.services.MemberTemplateReader;
import be.jobulcke.ldes.memberseeder.valueobjects.MemberTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class MemberSeederConfigTest {
	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withUserConfiguration(MemberSeederConfig.class)
			.withBean(MemberSeederProperties.class)
			.withBean(MemberSeederConfigTestConfiguration.class);

	@Test
	void given_StateObjectEnabled_when_GetSeeder_then_ReturnStateObjectSeeder() {
		contextRunner
				.withPropertyValues("member-seeder.state-object-seeding.enabled=true")
				.run(context -> assertThat(context)
						.hasSingleBean(StateObjectSeeder.class)
						.doesNotHaveBean(VersionObjectSeeder.class));
	}

	@Test
	void given_NoPropertyDefined_when_GetSeeder_then_ReturnVersionObjectSeeder() {
		contextRunner
				.run(context -> assertThat(context)
						.hasSingleBean(VersionObjectSeeder.class)
						.doesNotHaveBean(StateObjectSeeder.class));
	}

	@Test
	void given_StateObjectNotEnabledEnabled_when_GetSeeder_then_ReturnVersionObjectSeeder() {
		contextRunner
				.withPropertyValues("member-seeder.state-object-seeding.enabled=false")
				.run(context -> assertThat(context)
						.hasSingleBean(VersionObjectSeeder.class)
						.doesNotHaveBean(StateObjectSeeder.class));
	}


	@TestConfiguration
	static class MemberSeederConfigTestConfiguration {
		@Bean
		public MemberTemplateReader memberTemplateReader() {
			return fileName -> new MemberTemplate(List.of());
		}

		@Bean
		public MemberPoster memberPoster() {
			return mock(MemberPoster.class);
		}
	}
}