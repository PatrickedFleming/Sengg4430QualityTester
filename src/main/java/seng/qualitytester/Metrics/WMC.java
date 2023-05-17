package seng.qualitytester.Metrics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WMC {

    // Define the weight of a method based on its cyclomatic complexity

    public static int calculate(String classCode) {
        int wmc = 0;

        // Split the class code into separate method codes using regex
        String[] methodCodes = classCode.split("(?<=\\})[\\s\n]*?(?=\\bpublic\\b)");

        // Iterate over all method codes
        for (String methodCode : methodCodes) {
            int complexity = calculateCyclomaticComplexity(methodCode);
            wmc += complexity;
        }

        return wmc;
    }

    private static int calculateCyclomaticComplexity(String methodCode) {
        int complexity = 1;
        String[] lines = methodCode.split("\n");
        for (String line : lines) {
            if (line.contains("if") ||line.startsWith("else if") ||
            		line.contains("for") || line.contains("while") ||
            		line.contains("case") || line.contains("catch")) {
                complexity++;
            }
        }
        return complexity;
    }

}
