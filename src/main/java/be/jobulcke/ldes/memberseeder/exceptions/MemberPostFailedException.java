package be.jobulcke.ldes.memberseeder.exceptions;

public class MemberPostFailedException extends RuntimeException {
	private final int memberId;
	private final int httpStatusCode;

	public MemberPostFailedException(int memberId, int httpStatusCode) {
		this.memberId = memberId;
		this.httpStatusCode = httpStatusCode;
	}

	@Override
	public String getMessage() {
		return "Failed to post member with id %d: received status code %d from client".formatted(memberId, httpStatusCode);
	}
}
