package com.jobtracker.backend.model;

public enum NavbarPosition {
    TOP("Top"),
    BOTTOM("Bottom"),
    LEFT("Left"),
    RIGHT("Right");

    private final String displayName;

    NavbarPosition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
