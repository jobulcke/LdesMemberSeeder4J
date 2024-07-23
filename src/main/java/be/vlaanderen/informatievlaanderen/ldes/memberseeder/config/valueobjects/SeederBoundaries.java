package be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.valueobjects;

public class SeederBoundaries {
	private int inclusiveStart = 0;
	private int exclusiveEnd = 10000;

	public int getInclusiveStart() {
		return inclusiveStart;
	}

	public void setInclusiveStart(int inclusiveStart) {
		this.inclusiveStart = inclusiveStart;
	}

	public int getExclusiveEnd() {
		return exclusiveEnd;
	}

	public void setExclusiveEnd(int exclusiveEnd) {
		this.exclusiveEnd = exclusiveEnd;
	}
}
