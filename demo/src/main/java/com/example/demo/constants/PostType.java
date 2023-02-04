package com.example.demo.constants;

public enum PostType {

    FULL_TIME("full time") ,
    INTERNSHIP("internship"),
    EVENT("event"),
    COURSE("course");

    private final String label;

    PostType(String label) {
        this.label = label;
    }
}
