package be.jobulcke.ldes.memberseeder.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "member-seeder.ldes-server")
public class LdesServerProperties {
	private String host = "http://localhost:8080";
	private String collection;
	private String rdfFormat = "application/n-quads";

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getCollection() {
		return collection;
	}

	public void setCollection(String collection) {
		this.collection = collection;
	}

	public String getCollectionUri() {
		return "/" + collection;
	}

	public String getRdfFormat() {
		return rdfFormat;
	}

	public void setRdfFormat(String rdfFormat) {
		this.rdfFormat = rdfFormat;
	}
}
