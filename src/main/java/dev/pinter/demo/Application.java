package dev.pinter.demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Application.class.getName());

        logger.info("Starting");

        try {
            Injector injector = Guice.createInjector(getApplicationModules().toArray(new Module[0]));
            injector.getInstance(Start.class).run();
        } catch (Exception e) {
            logger.error("Error", e);
        }
    }

    private static List<Module> getApplicationModules() {
        return new ArrayList<>() {{
            add(new GuiceModule());
            add(new AnotherModule());
        }};
    }
}
