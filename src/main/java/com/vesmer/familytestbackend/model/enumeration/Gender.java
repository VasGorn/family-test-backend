package com.vesmer.familytestbackend.model.enumeration;

public enum Gender {
    FEMALE("FEMALE"),
    MALE("MALE");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getString() {
        return gender;
    }
}
