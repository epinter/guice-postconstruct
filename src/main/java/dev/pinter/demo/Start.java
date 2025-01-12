package dev.pinter.demo;

import dev.pinter.demo.otherpackage.AnotherStart;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Start {
    @Inject
    private Hello hello;

    @Inject
    private AnotherStart anotherStart;

    private static final Logger logger = LoggerFactory.getLogger(Start.class.getName());

    public void run() {
        logger.info("Start.run()");
        logger.warn("Message: '{}'", hello.getMessage());
        anotherStart.run();
    }
}
