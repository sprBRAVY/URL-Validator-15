package com.validator.checker;
import com.validator.config.ConfigLoader;
import com.validator.model.*;
import java.net.URI;
import java.net.http.*;
import java.time.Duration;

public final class UrlChecker {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofMillis(ConfigLoader.get().getTimeoutMs()))
            .followRedirects(HttpClient.Redirect.NORMAL)  // ← обязательно!
            .build();

    public CheckResult check(String raw) {
        String url = raw.strip();
        if (url.isBlank()) return new CheckResult(url, StatusCategory.OTHER_ERROR, null, "empty line");

        try {
            var request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .method(ConfigLoader.get().getHttpMethod(), HttpRequest.BodyPublishers.noBody())
                    .timeout(Duration.ofMillis(ConfigLoader.get().getTimeoutMs()))
                    .build();

            int code = client.send(request, HttpResponse.BodyHandlers.discarding()).statusCode();
            return new CheckResult(url, StatusCategory.fromCode(code), code, null);

        } catch (Exception e) {
            var cat = (e instanceof java.net.ConnectException ||
                    e instanceof java.net.SocketTimeoutException ||
                    e instanceof java.util.concurrent.TimeoutException)
                    ? StatusCategory.TIMEOUT : StatusCategory.OTHER_ERROR;
            return new CheckResult(url, cat, null, e.getClass().getSimpleName());
        }
    }
}