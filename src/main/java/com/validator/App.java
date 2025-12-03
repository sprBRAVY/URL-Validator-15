package com.validator;

import com.validator.checker.UrlChecker;
import com.validator.model.CheckResult;
import com.validator.reporter.ReportPrinter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // Получаем путь и немедленно убираем пробелы в начале и конце
        String rawPath = com.validator.config.ConfigLoader.get().getUrlsFilePath();
        String cleanPath = rawPath.trim();

        if (cleanPath.isEmpty()) {
            throw new IllegalStateException("URL file path is empty in configuration");
        }

        var urls = Files.lines(Paths.get(cleanPath))
                .map(String::strip)
                .filter(line -> !line.isEmpty() && !line.startsWith("#"))
                .toList();

        var checker = new UrlChecker();
        var results = urls.parallelStream()
                .map(checker::check)
                .toList();

        new ReportPrinter().print(results);
    }
}
