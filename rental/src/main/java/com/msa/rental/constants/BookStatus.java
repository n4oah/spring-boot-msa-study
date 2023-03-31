package com.msa.rental.constants;

public enum BookStatus {
    Rent("Rent"),
    Available("Available");

    private String type;

    BookStatus(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
