package com.validator.reporter;

import com.validator.model.*;
import java.util.*;

public final class ReportPrinter {
    public void print(List<CheckResult> results) {
        Map<StatusCategory, List<CheckResult>> map = new EnumMap<>(StatusCategory.class);
        for (CheckResult r : results) {
            map.computeIfAbsent(r.category(), k -> new ArrayList<>()).add(r);
        }

        boolean hasFailed = map.keySet().stream()
                .anyMatch(c -> c != StatusCategory.OK && c != StatusCategory.REDIRECTION);

        if (hasFailed) {
            System.out.println("--- FAILED URLS ---");
            printCategory(map, StatusCategory.CLIENT_ERROR);
            printCategory(map, StatusCategory.SERVER_ERROR);
            printCategory(map, StatusCategory.TIMEOUT);
            printCategory(map, StatusCategory.OTHER_ERROR);
            printCategory(map, StatusCategory.REDIRECTION);
        }

        var okList = map.get(StatusCategory.OK);
        if (okList != null && !okList.isEmpty()) {
            if (hasFailed) System.out.println();
            System.out.println("--- OK URLS ---");
            okList.stream()
                    .sorted(Comparator.comparing(CheckResult::url))
                    .forEach(r -> System.out.println(r.format()));
        }
    }

    private void printCategory(Map<StatusCategory, List<CheckResult>> map, StatusCategory cat) {
        var list = map.get(cat);
        if (list != null) {
            list.stream()
                    .sorted(Comparator.comparing(CheckResult::url))
                    .forEach(r -> System.out.println(r.format()));
        }
    }
}