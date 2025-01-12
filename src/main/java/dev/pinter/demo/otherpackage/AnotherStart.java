package dev.pinter.demo.otherpackage;

import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnotherStart {
    private static final Logger logger = LoggerFactory.getLogger(AnotherStart.class.getName());

    @Inject
    private AnotherHello anotherHello;

    public void run() {
        logger.info("AnotherStart.run()");
        logger.warn("AnotherMessage: '{}'", anotherHello.getMessage());
    }
}
