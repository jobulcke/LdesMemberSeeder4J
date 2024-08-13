package be.jobulcke.ldes.memberseeder.services;

import be.jobulcke.ldes.memberseeder.valueobjects.MemberTemplate;

public interface MemberTemplateReader {
	MemberTemplate readMemberTemplate(String fileName);
}
