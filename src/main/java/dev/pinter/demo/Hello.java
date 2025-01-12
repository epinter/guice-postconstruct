package dev.pinter.demo;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;

public class Hello {
    @Inject
    @Named("anObject") private String n;

    @PostConstruct
    public void init() {
        System.err.println("test PostConstruct 1: "+ n);
    }

    public String getMessage() {
        return "Hello from class Hello";
    }
}
