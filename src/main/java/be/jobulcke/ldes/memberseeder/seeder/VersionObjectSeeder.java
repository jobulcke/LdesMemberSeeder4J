package be.jobulcke.ldes.memberseeder.seeder;

import be.jobulcke.ldes.memberseeder.services.MemberPoster;
import be.jobulcke.ldes.memberseeder.services.MemberSeederLogger;
import be.jobulcke.ldes.memberseeder.valueobjects.MemberTemplate;

import java.util.stream.IntStream;

public class VersionObjectSeeder implements MemberSeeder {
	private final MemberSeederLogger logger = new MemberSeederLogger();
	private final IntStream memberRange;
	private final MemberTemplate memberTemplate;
	private final MemberPoster memberPoster;

	public VersionObjectSeeder(IntStream memberRange, MemberTemplate memberTemplate, MemberPoster memberPoster) {
		this.memberRange = memberRange;
		this.memberTemplate = memberTemplate;
		this.memberPoster = memberPoster;
	}

	@Override
	public void seed() {
		logger.start();
		memberRange.forEach(index -> {
			memberPoster.postMember(memberTemplate.createMember(index));
			logger.incrementAndLog();
		});
		logger.finalLogAndClear();
	}
}
