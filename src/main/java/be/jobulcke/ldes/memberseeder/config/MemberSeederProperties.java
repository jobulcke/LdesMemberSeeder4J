package be.jobulcke.ldes.memberseeder.config;

import be.jobulcke.ldes.memberseeder.config.valueobjects.SeederBoundaries;
import be.jobulcke.ldes.memberseeder.config.valueobjects.StateObjectProperties;
import be.jobulcke.ldes.memberseeder.valueobjects.StateObjectMemberRange;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.util.stream.IntStream;
import java.util.stream.Stream;

@Configuration
@ConfigurationProperties(prefix = "member-seeder")
public class MemberSeederProperties {
	@NestedConfigurationProperty
	private SeederBoundaries boundaries = new SeederBoundaries();
	@NestedConfigurationProperty
	private StateObjectProperties stateObjectSeeding = new StateObjectProperties();
	private String memberTemplate;

	public SeederBoundaries getBoundaries() {
		return boundaries;
	}

	public void setBoundaries(SeederBoundaries boundaries) {
		this.boundaries = boundaries;
	}

	public String getMemberTemplate() {
		return memberTemplate;
	}

	public void setMemberTemplate(String memberTemplate) {
		this.memberTemplate = memberTemplate;
	}

	public IntStream getMemberRange() {
		return IntStream.range(boundaries.getInclusiveStart(), boundaries.getExclusiveEnd());
	}

	public Stream<IntStream> getStateObjectRanges() {
		return StateObjectMemberRange
				.ranges(boundaries.getInclusiveStart(), boundaries.getExclusiveEnd(), stateObjectSeeding.getNumberOfMembers());
	}

	public StateObjectProperties getStateObjectSeeding() {
		return stateObjectSeeding;
	}

	public void setStateObjectSeeding(StateObjectProperties stateObjectSeeding) {
		this.stateObjectSeeding = stateObjectSeeding;
	}
}
