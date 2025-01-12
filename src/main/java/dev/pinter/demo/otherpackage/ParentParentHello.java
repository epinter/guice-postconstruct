package dev.pinter.demo.otherpackage;

import jakarta.annotation.PostConstruct;

public class ParentParentHello extends ParentParentParentHello {

    @PostConstruct
    @Override
    protected void init4() {
        System.err.println("test PostConstruct 4-override");
    }

    @PostConstruct
    private void init3() {
        System.err.println("test PostConstruct 3");
    }
}
