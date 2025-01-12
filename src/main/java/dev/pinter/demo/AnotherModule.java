package dev.pinter.demo;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import jakarta.inject.Named;

public class AnotherModule extends AbstractModule {
    @Override
    protected void configure() {
    }

    @Provides
    @Named("anObject")
    public String anObject() {
        return "An information";
    }

    @Provides
    @Named("otherObject")
    public String otherObject() {
        return "Another information";
    }
}
