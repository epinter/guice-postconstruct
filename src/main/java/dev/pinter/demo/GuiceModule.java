package dev.pinter.demo;

import com.google.inject.AbstractModule;
import dev.pinter.guice.PostConstructModule;

public class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new PostConstructModule());
    }
}