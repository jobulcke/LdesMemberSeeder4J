package be.vlaanderen.informatievlaanderen.ldes.memberseeder.config;

import be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.valueobjects.LdesServerProperties;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.valueobjects.SeederBoundaries;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.valueobjects.StateObjectProperties;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.valueobjects.StateObjectMemberRange;
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
	private LdesServerProperties ldesServer = new LdesServerProperties();
	@NestedConfigurationProperty
	private StateObjectProperties stateObjectSeeding = new StateObjectProperties();
	private String memberTemplate;
	private String rdfFormat = "application/n-quads";

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

	public String getRdfFormat() {
		return rdfFormat;
	}

	public void setRdfFormat(String rdfFormat) {
		this.rdfFormat = rdfFormat;
	}

	public LdesServerProperties getLdesServer() {
		return ldesServer;
	}

	public void setLdesServer(LdesServerProperties ldesServer) {
		this.ldesServer = ldesServer;
	}

	public IntStream getMemberRange() {
		return IntStream.range(boundaries.getInclusiveStart(), boundaries.getExclusiveEnd());
	}

	public Stream<IntStream> getStateObjectRange() {
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
