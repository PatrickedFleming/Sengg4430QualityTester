package seng.qualitytester.Metrics;
//Created By Patrick Fleming
//Email: C3253586@uon.edu.au
import java.util.HashMap;
import java.util.Map;

//NOC short for Number of Children
//Calculates the number of children for each class when given a map of file names and code
public class NOC {
    private final Map<String, String> fileCodeMap;

    //constructor
    public NOC(Map<String, String> fileCodeMap) {
        this.fileCodeMap = fileCodeMap;
    }

    //calculates the number of children for each class
    public Map<String, Integer> calculateNumberOfChildren() {
        Map<String, Integer> numberOfChildrenMap = new HashMap<>();

        for (Map.Entry<String, String> entry : fileCodeMap.entrySet()) {
            String code = entry.getValue();
            String parentClass = null;

            for (String line : code.split("\\r?\\n")) {
                if (line.startsWith("public class")) {
                    int idx = line.indexOf("extends");
                    if (idx != -1) {
                        parentClass = line.substring(idx + 8).trim();
                    }
                }
            }

            if (parentClass != null) {
                if (numberOfChildrenMap.containsKey(parentClass)) {
                    numberOfChildrenMap.put(parentClass, numberOfChildrenMap.get(parentClass) + 1);
                } else {
                    numberOfChildrenMap.put(parentClass, 1);
                }
            }
        }

        return numberOfChildrenMap;
    }
}