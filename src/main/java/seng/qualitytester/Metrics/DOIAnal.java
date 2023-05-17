package seng.qualitytester.Metrics;
//Created By Patrick Fleming
//Email: C3253586@uon.edu.au
import java.util.Map;

//DOI short for Depth of Inheritance Analayses
//Analyses the depth of inheritance for each class when given a map of Parent classes and depth of inheritance
public class DOIAnal {
    private final Map<String, Integer> inheritanceDepthMap;

    //constructor
    public DOIAnal(Map<String, Integer> inheritanceDepthMap) {
        this.inheritanceDepthMap = inheritanceDepthMap;
    }

    //calculates the average depth of inheritance for each class
    public double calculateAverageDepthOfInheritance() {
        if (inheritanceDepthMap.isEmpty()) {
            return 0.0;
        }

        double totalDepth = 0.0;
        for (int depth : inheritanceDepthMap.values()) {
            totalDepth += depth;
        }

        return totalDepth / inheritanceDepthMap.size();
    }

    //calculates the biggest depth of inheritance for each class
    public int calculateBiggestDepthOfInheritance() {
        if (inheritanceDepthMap.isEmpty()) {
            return 0;
        }

        int maxDepth = Integer.MIN_VALUE;
        for (int depth : inheritanceDepthMap.values()) {
            if (depth > maxDepth) {
                maxDepth = depth;
            }
        }

        return maxDepth;
    }

    //calculates the total number of classes
    public int calculateTotalJavaClasses() {
        return inheritanceDepthMap.size();
    }
}