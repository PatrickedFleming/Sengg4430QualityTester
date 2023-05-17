package seng.qualitytester.Metrics;
//Created By Patrick Fleming
//Email: C3253586@uon.edu.au
import java.util.Map;

//NOC short for Number of Children Analyses
//Analyses the number of children for each class when given a map of Parent classes and number of children
public class NOCAnal {
    private final Map<String, Integer> parentChildMap;

    //constructor
    public NOCAnal(Map<String, Integer> parentChildMap) {
        this.parentChildMap = parentChildMap;
    }

    //calculates the average number of children for each class
    public double calculateAverageNumberOfChildren() {
        if (parentChildMap.isEmpty()) {
            return 0;
        }

        int totalChildren = 0;
        for (int childCount : parentChildMap.values()) {
            totalChildren += childCount;
        }

        return (double) totalChildren / parentChildMap.size();
    }

    //calculates the biggest number of children for each class
    public int findBiggestNumberOfChildren() {
        int maxChildren = 0;

        for (int childCount : parentChildMap.values()) {
            if (childCount > maxChildren) {
                maxChildren = childCount;
            }
        }

        return maxChildren;
    }
}
