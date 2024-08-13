package be.jobulcke.ldes.memberseeder.config.valueobjects;

public class StateObjectProperties {
	private boolean enabled = false;
	private int numberOfMembers = 10;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getNumberOfMembers() {
		return numberOfMembers;
	}

	public void setNumberOfMembers(int numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}
}
