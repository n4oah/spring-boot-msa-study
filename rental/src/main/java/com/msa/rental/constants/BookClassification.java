package com.msa.rental.constants;

public enum BookClassification {
    Travel("Travel"),
    Programming("Programming"),
    Hobby("Hobby");

    private final String type;

    BookClassification(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
