package dev.pinter.demo.otherpackage;

import jakarta.inject.Inject;
import jakarta.inject.Named;

class AnotherHello extends ParentHello {
    @Inject
    @Named("otherObject")
    private String n;

    public String getMessage() {
        if (!initialized) {
            throw new IllegalStateException("PostConstruct not called");
        }

        return "AnotherHello from class AnotherHello";
    }
}
