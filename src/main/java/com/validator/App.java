package com.validator;

import com.validator.checker.UrlChecker;
import com.validator.model.CheckResult;
import com.validator.reporter.ReportPrinter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) throws Exception {
        var path = com.validator.config.ConfigLoader.get().getUrlsFilePath();

        var urls = Files.lines(Paths.get(path))
                .map(String::strip)
                .filter(line -> !line.isBlank() && !line.startsWith("#"))
                .toList();

        var checker = new UrlChecker();
        var results = urls.parallelStream()
                .map(checker::check)
                .toList();

        new ReportPrinter().print(results);
    }
}