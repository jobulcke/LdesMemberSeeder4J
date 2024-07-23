package be.vlaanderen.informatievlaanderen.ldes.memberseeder.config;

import be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.valueobjects.LdesServerConfig;
import be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.valueobjects.SeederBoundaries;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "member-seeder")
public class MemberSeederConfig {
	@NestedConfigurationProperty
	private SeederBoundaries boundaries = new SeederBoundaries();
	@NestedConfigurationProperty
	private LdesServerConfig ldesServer = new LdesServerConfig();
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

	public LdesServerConfig getLdesServer() {
		return ldesServer;
	}

	public void setLdesServer(LdesServerConfig ldesServer) {
		this.ldesServer = ldesServer;
	}
}
