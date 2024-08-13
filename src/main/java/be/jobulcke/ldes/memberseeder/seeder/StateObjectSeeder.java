package be.jobulcke.ldes.memberseeder.seeder;

import be.jobulcke.ldes.memberseeder.services.MemberPoster;
import be.jobulcke.ldes.memberseeder.services.MemberSeederLogger;
import be.jobulcke.ldes.memberseeder.entities.Member;
import be.jobulcke.ldes.memberseeder.valueobjects.MemberTemplate;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StateObjectSeeder implements MemberSeeder {
	private final MemberSeederLogger logger = new MemberSeederLogger();
	private final Stream<IntStream> memberRanges;
	private final MemberTemplate memberTemplate;
	private final MemberPoster memberPoster;

	public StateObjectSeeder(Stream<IntStream> memberRanges, MemberTemplate memberTemplate, MemberPoster memberPoster) {
		this.memberRanges = memberRanges;
		this.memberTemplate = memberTemplate;
		this.memberPoster = memberPoster;
	}

	@Override
	public void seed() {
		logger.start();
		AtomicInteger stateObjectCounter = new AtomicInteger();

		memberRanges
				.map(this::createStateObjects)
				.map(model -> new Member(stateObjectCounter.incrementAndGet(), model))
				.forEach(member -> {
					memberPoster.postMember(member);
					logger.incrementAndLog();
				});

		logger.finalLogAndClear();
	}

	private String createStateObjects(IntStream memberRange) {
		return memberRange
				.mapToObj(memberTemplate::createMember)
				.map(Member::model)
				.collect(Collectors.joining("\n"));
	}
}
