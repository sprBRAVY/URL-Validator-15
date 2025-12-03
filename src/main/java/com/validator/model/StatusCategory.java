package com.validator.model;

public enum StatusCategory {
    OK("OK"),
    REDIRECTION("REDIRECTION"),
    CLIENT_ERROR("CLIENT_ERROR"),
    SERVER_ERROR("SERVER_ERROR"),
    TIMEOUT("TIMEOUT"),
    OTHER_ERROR("OTHER_ERROR");

    private final String label;

    StatusCategory(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static StatusCategory fromCode(int code) {
        return switch (code / 100) {
            case 2 -> OK;
            case 3 -> REDIRECTION;
            case 4 -> CLIENT_ERROR;
            case 5 -> SERVER_ERROR;
            default -> OTHER_ERROR;
        };
    }
}