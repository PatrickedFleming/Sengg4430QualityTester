package seng.qualitytester.Metrics;
//Created By Harrison King
//Email: C3304992@uon.edu.au

import java.util.HashMap;
import java.util.Map;

//DOCN short for Depth of Conditional Nesting
//Calculates the depth of conditional nesting for each class when given a map of file names and code
public class DOCN {
    private final Map<String, String> fileCodeMap;

    //constructor
    public DOCN(Map<String, String> fileCodeMap) {
        this.fileCodeMap = fileCodeMap;
    }
    private int getMaxConditionalNestingDepth(String code) {
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

//notneeded
//    public int getDepthOverAmount(String code, int depthlimit) {
//        int depthTemp = 0;
//        int depthCount = 0;
//        boolean inConditional = false;
//
//        for (int i = 0; i < code.length(); i++) {
//            char c = code.charAt(i);
//            if (c == '{') {
//                if (inConditional) {
//                    depthTemp++;
//                    if (depthTemp == depthlimit) {
//                        depthCount++;
//                    }
//                }
//            } else if (c == '}') {
//                if (inConditional) {
//                    depthTemp--;
//                }
//                if (depthTemp == 0) {
//                    inConditional = false;
//                }
//            } else if (c == 'i' && i + 1 < code.length() && code.substring(i, i + 2).equals("if")) {
//                inConditional = true;
//            }
//        }
//        return depthCount;
//    }

    //calculates the depth of conditional nesting for each class
    public Map<String, Integer> calculateDepthOfConditionalNesting() {
        Map<String, Integer> conditionalDepthMap = new HashMap<>();
        for (Map.Entry<String, String> entry : fileCodeMap.entrySet()) {
            String fileName = entry.getKey();
            String code = entry.getValue();

            conditionalDepthMap.put(fileName, getMaxConditionalNestingDepth(code));
        }

        return conditionalDepthMap;
    }
}