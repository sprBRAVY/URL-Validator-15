package com.validator.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigLoader {
    private final Properties p = new Properties();

    private ConfigLoader() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("app.properties")) {
            if (is == null) throw new IllegalStateException("app.properties not found in classpath");
            p.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load app.properties", e);
        }
    }

    private static class Holder {
        static final ConfigLoader INSTANCE = new ConfigLoader();
    }

    public static ConfigLoader get() {
        return Holder.INSTANCE;
    }

    public String getUrlsFilePath() { return p.getProperty("urls.file.path", "config/urls.txt"); }
    public long getTimeoutMs() { return Long.parseLong(p.getProperty("http.timeout.ms", "5000")); }
    public String getHttpMethod() { return p.getProperty("http.method", "HEAD").toUpperCase(); }
}