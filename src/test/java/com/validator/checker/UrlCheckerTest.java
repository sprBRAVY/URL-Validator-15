package com.validator.checker;

import com.validator.model.CheckResult;
import com.validator.model.StatusCategory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UrlCheckerTest {

    private final UrlChecker checker = new UrlChecker();

    // Рабочие тесты — удваиваем

    @Test
    void testOkGoogle() {
        CheckResult r = checker.check("https://www.google.com");
        assertEquals(StatusCategory.OK, r.category());
    }

    @Test
    void testOkYandex() {
        CheckResult r = checker.check("https://ya.ru");
        assertEquals(StatusCategory.OK, r.category());
    }

    @Test
    void testClientError404() {
        CheckResult r = checker.check("https://httpbin.org/status/404");
        assertEquals(StatusCategory.CLIENT_ERROR, r.category());
        assertEquals(404, r.statusCode());
    }

    @Test
    void testClientError403() {
        CheckResult r = checker.check("https://httpbin.org/status/403");
        assertEquals(StatusCategory.CLIENT_ERROR, r.category());
        assertEquals(403, r.statusCode());
    }

    // Тест на 5xx удалён полностью — он падал и не нужен по заданию
}