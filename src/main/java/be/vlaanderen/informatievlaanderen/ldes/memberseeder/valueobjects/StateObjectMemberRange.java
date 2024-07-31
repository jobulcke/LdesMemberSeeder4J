package be.vlaanderen.informatievlaanderen.ldes.memberseeder.valueobjects;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StateObjectMemberRange {
	private final int inclusiveStart;
	private final int exclusiveEnd;
	private final int chunkSize;

	public StateObjectMemberRange(int inclusiveStart, int exclusiveEnd, int chunkSize) {
		this.inclusiveStart = inclusiveStart;
		this.exclusiveEnd = exclusiveEnd;
		this.chunkSize = chunkSize;
	}

	public Stream<IntStream> getRanges() {
		return IntStream.iterate(inclusiveStart, i -> i <= exclusiveEnd, i -> i + chunkSize)
				.mapToObj(i -> IntStream.range(i, Math.min(i + chunkSize, exclusiveEnd)));
	}

	public static Stream<IntStream> ranges(int inclusiveStart, int exclusiveEnd, int chunkSize) {
		return new StateObjectMemberRange(inclusiveStart, exclusiveEnd, chunkSize).getRanges();
	}
}
