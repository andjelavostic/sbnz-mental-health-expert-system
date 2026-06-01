package com.ftn.sbnz.kjar;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.drools.template.DataProvider;
import org.drools.template.DataProviderCompiler;
import org.drools.template.objects.ArrayDataProvider;

public class GenerateFinalDecisionRules {

    public static void main(String[] args) throws Exception {
        Path resources = Path.of("src/main/resources");
        if (!Files.exists(resources.resolve("templates/final_decision.csv"))) {
            resources = Path.of("kjar/src/main/resources");
        }

        Path templatePath = resources.resolve("templates/final_decision_template.drt");
        Path csvPath = resources.resolve("templates/final_decision.csv");
        Path outputPath = resources.resolve("rules/generated_final_decisions.drl");

        List<String[]> rows = Files.readAllLines(csvPath, StandardCharsets.UTF_8)
                .stream()
                .skip(1)
                .filter(line -> !line.isBlank())
                .map(line -> line.split(",", -1))
                .toList();

        DataProvider dataProvider = new ArrayDataProvider(rows.toArray(new String[0][]));
        DataProviderCompiler compiler = new DataProviderCompiler();

        try (InputStream template = Files.newInputStream(templatePath)) {
            String drl = compiler.compile(dataProvider, template);
            Files.writeString(outputPath, drl, StandardCharsets.UTF_8);
        }

        System.out.println("Generated " + outputPath);
    }
}
