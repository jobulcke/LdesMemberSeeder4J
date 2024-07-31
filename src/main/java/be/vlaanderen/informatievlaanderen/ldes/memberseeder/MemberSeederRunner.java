package be.vlaanderen.informatievlaanderen.ldes.memberseeder;

import be.vlaanderen.informatievlaanderen.ldes.memberseeder.seeder.MemberSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MemberSeederRunner implements CommandLineRunner {
	private final MemberSeeder memberSeeder;

	public MemberSeederRunner(MemberSeeder memberSeeder) {
		this.memberSeeder = memberSeeder;
	}

	@Override
	public void run(String... args) {
		memberSeeder.seed();
	}
}
