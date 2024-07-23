package be.vlaanderen.informatievlaanderen.ldes.memberseeder.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

public class MemberSeederLogger {
	private static final String FREQUENT_LOGGING_TEMPLATE = "Ingested\t{} \tin\t{}\tseconds. Throughput:\t{}";
	private static final String FINAL_LOGGING_TEMPLATE = "Ingested all\t\tin\t {} \tseconds.";
	private static final Logger log = LoggerFactory.getLogger(MemberSeederLogger.class);
	private final AtomicInteger counter = new AtomicInteger();
	private long start;
	private long previousFinish;

	public void start() {
		start = System.currentTimeMillis();
		previousFinish = System.currentTimeMillis();
	}

	public void incrementAndLog() {
		long finish = System.currentTimeMillis();
		if (counter.incrementAndGet() % 100 == 0) {
			frequentLog(counter.get(), finish - previousFinish);
			previousFinish = finish;
		}
	}

	public void finalLogAndClear() {
		log.info(FINAL_LOGGING_TEMPLATE, (System.currentTimeMillis() - start) / 1000.);
		start = 0;
		previousFinish = 0;
	}

	private void frequentLog(int index, long delta) {
		final double seconds = delta / 1000.;
		final double throughput = 100 / seconds;
		log.info(FREQUENT_LOGGING_TEMPLATE, index, seconds, throughput);
	}
}
