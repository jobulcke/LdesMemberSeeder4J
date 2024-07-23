package be.vlaanderen.informatievlaanderen.ldes.memberseeder.entities;

public record Member(int id, String model){
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Member member)) return false;

		return id == member.id;
	}

	@Override
	public int hashCode() {
		return id;
	}
}
