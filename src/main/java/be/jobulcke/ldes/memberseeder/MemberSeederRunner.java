package be.jobulcke.ldes.memberseeder;

import be.jobulcke.ldes.memberseeder.seeder.MemberSeeder;
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
