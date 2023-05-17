package seng.qualitytester.Metrics;

import java.util.HashMap;
import java.util.Map;

public class RFC {
    private final Map<String, String> fileCodeMap;

    //constructor
    public RFC(Map<String, String> fileCodeMap) {
        this.fileCodeMap = fileCodeMap;
    }

    //calculates the direct response for class, parser needed for inclusion of indirect response.
    //implementation assumes that all methods for a class are within the same file.
    public Map<String, Integer> calculateResponseForClass() {
        Map<String, Integer> responseMap = new HashMap<>();
        for (Map.Entry<String, String> entry : fileCodeMap.entrySet()) {
            String fileName = entry.getKey();
            String code = entry.getValue();

            String[] lines = code.split("\\r?\\n");

            String parentClassName = null;
            int response = 0;

            for (String line : lines) {
                if (line.startsWith("public class")) {
                    int idx = line.indexOf("extends");
                    if (idx != -1) {
                        parentClassName = line.substring(idx + 8).split("\\s")[0];
                        response = 1;
                    } else {
                        parentClassName = line.substring(12).trim();
                        response = 0;
                    }
                } else if (parentClassName != null && line.contains("extends " + parentClassName)) {
                    parentClassName = line.substring(line.indexOf("extends") + 8).split("\\s")[0];
                } else if (parentClassName != null && line.trim().startsWith("public") && line.trim().endsWith(") {") && !line.contains("override")) {
                    response++;
                }
            }

            if (parentClassName != null) {
                responseMap.put(parentClassName, response);
            }
        }

        return responseMap;
    }
    public static int getMaxConditionalNestingDepth(String code) {
        int depth = 0;
        int maxDepth = 0;
        boolean inConditional = false;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '{') {
                if (inConditional) {
                    depth++;
                    if (depth > maxDepth) {
                        maxDepth = depth;
                    }
                }
            } else if (c == '}') {
                if (inConditional) {
                    depth--;
                }
                if (depth == 0) {
                    inConditional = false;
                }
            } else if (c == 'i' && i + 1 < code.length() && code.substring(i, i + 2).equals("if")) {
                inConditional = true;
            }
        }
        return maxDepth;
    }

    public static int getDepthOverAmount(String code, int depthlimit) {
        int depthTemp = 0;
        int depthCount = 0;
        boolean inConditional = false;

        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '{') {
                if (inConditional) {
                    depthTemp++;
                    if (depthTemp == depthlimit) {
                        depthCount++;
                    }
                }
            } else if (c == '}') {
                if (inConditional) {
                    depthTemp--;
                }
                if (depthTemp == 0) {
                    inConditional = false;
                }
            } else if (c == 'i' && i + 1 < code.length() && code.substring(i, i + 2).equals("if")) {
                inConditional = true;
            }
        }
        return depthCount;
    }

    public static int calculateRFC(Map<String, String> fileCodeMap, String classname) {
        int responseCount = 0;

        String fileContent = fileCodeMap.get(classname);

        String[] lines = fileContent.split("\n");

        for (String line : lines) {
            if (line.trim().startsWith("public") && line.trim().endsWith(") {")) {
                responseCount++;
            }
        }

        return responseCount;
    }
}
