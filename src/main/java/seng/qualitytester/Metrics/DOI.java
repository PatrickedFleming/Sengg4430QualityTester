package seng.qualitytester.Metrics;
//Created By Patrick Fleming
//Email: C3253586@uon.edu.au
import java.util.HashMap;
import java.util.Map;


//DOI short for Depth of Inheritance
//Calculates the depth of inheritance for each class when given a map of file names and code
public class DOI {
    private final Map<String, String> fileCodeMap;

    //constructor
    public DOI(Map<String, String> fileCodeMap) {
        this.fileCodeMap = fileCodeMap;
    }

    //calculates the depth of inheritance for each class
    public Map<String, Integer> calculateDepthOfInheritance() {
        Map<String, Integer> inheritanceDepthMap = new HashMap<>();
        for (Map.Entry<String, String> entry : fileCodeMap.entrySet()) {
            String fileName = entry.getKey();
            String code = entry.getValue();
    
            String[] lines = code.split("\\r?\\n");
    
            String parentClassName = null;
            int depth = 0;
    
            for (String line : lines) {
                if (line.startsWith("public class")) {
                    int idx = line.indexOf("extends");
                    if (idx != -1) {
                        parentClassName = line.substring(idx + 8).split("\\s")[0];
                        depth = 1;
                    } else {
                        parentClassName = line.substring(12).trim();
                        depth = 0;
                    }
                } else if (parentClassName != null && line.contains("extends " + parentClassName)) {
                    depth++;
                    parentClassName = line.substring(line.indexOf("extends") + 8).split("\\s")[0];
                } else if (line.contains("implements")) {
                    parentClassName = null; // clear parent class name if "implements" keyword is found
                }
            }
    
            if (parentClassName != null) {
                inheritanceDepthMap.put(parentClassName, depth);
            }
        }
    
        return inheritanceDepthMap;
    }
}