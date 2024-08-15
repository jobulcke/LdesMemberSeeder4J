package be.jobulcke.ldes.memberseeder;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class MemberSeederApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
				.web(WebApplicationType.NONE)
				.sources(MemberSeederApplication.class)
				.run(args);
	}

}
