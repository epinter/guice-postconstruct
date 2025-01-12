package dev.pinter.demo.otherpackage;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Named;

public class ParentHello extends ParentParentHello {
    protected boolean initialized = false;

    @Inject
    @Named("otherObject")
    private String n;

    @PostConstruct
    private void init2() {
        initialized = true;
        System.err.println("test PostConstruct 2: " + n);
    }
}
