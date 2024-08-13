package be.jobulcke.ldes.memberseeder.valueobjects;

import be.jobulcke.ldes.memberseeder.entities.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MemberTemplate {
	private final Random random = new Random();
	private final List<String> templateLines;

	public MemberTemplate(List<String> templateLines) {
		this.templateLines = templateLines;
	}

	public Member createMember(int id) {
		final int randomHoursAddition = random.nextInt(50 + id) + 24;
		final LocalDateTime timestamp = LocalDateTime.now().minusHours(randomHoursAddition);
		final String model = templateLines.stream()
				.map(s -> s.replace("#ID", String.valueOf(600000 + id)))
				.map(s -> s.replace("#VERSION", String.valueOf(10810400)))
				.map(s -> s.replace("#TIME", String.valueOf(timestamp)))
				.collect(Collectors.joining("\n"));
		return new Member(id, model);
	}
}
