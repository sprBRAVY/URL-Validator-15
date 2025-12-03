package com.validator.model;

public record CheckResult(String url, StatusCategory category, Integer statusCode, String errorMessage) {
    public String format() {
        if (statusCode != null) {
            return "[%s: %d] %s".formatted(category.getLabel(), statusCode, url);
        } else {
            return "[%s] %s%s".formatted(category.getLabel(), url,
                    errorMessage != null ? " - " + errorMessage : "");
        }
    }
}