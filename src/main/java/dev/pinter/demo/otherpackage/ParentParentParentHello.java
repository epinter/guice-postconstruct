package dev.pinter.demo.otherpackage;

import jakarta.annotation.PostConstruct;

public class ParentParentParentHello {
    @PostConstruct
    protected void init4() {
        System.err.println("test PostConstruct 4");
    }
}
