package com.msa.rental.constants;

public enum BookLibraryLocation {
    Naver("Naver"),
    Pangyo("Pangyo");

    private String type;

    BookLibraryLocation(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
