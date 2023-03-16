package com.msa.book.constants;

public enum BookClassification {
    Arts("Arts"),
    Children("Children"),
    Travel("Travel"),
    Programming("Programming");

    private final String type;

    BookClassification(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
