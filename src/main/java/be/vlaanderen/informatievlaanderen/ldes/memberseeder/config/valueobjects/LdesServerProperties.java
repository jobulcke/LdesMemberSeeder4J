package be.vlaanderen.informatievlaanderen.ldes.memberseeder.config.valueobjects;

public class LdesServerProperties {
	private String host = "http://localhost:8080";
	private String collection;

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
}
