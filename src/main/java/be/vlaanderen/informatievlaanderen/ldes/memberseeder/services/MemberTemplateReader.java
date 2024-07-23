package be.vlaanderen.informatievlaanderen.ldes.memberseeder.services;

import be.vlaanderen.informatievlaanderen.ldes.memberseeder.valueobjects.MemberTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MemberTemplateReader {
	public MemberTemplate readMemberTemplate(String fileName) {
		try {
			final List<String> templateLines = Files.readAllLines(Paths.get(fileName));
			return new MemberTemplate(templateLines);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}
}
