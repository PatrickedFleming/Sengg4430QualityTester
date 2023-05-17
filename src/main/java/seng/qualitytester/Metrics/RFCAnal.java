package seng.qualitytester.Metrics;

import java.util.Map;

public class RFCAnal {
    private final Map<String, Integer> responseMap;

    //constructor
    public RFCAnal(Map<String, Integer> responseMap) {
        this.responseMap = responseMap;
    }

    //calculates the average response
    public double calculateAverageResponse() {
        if (responseMap.isEmpty()) {
            return 0.0;
        }

        double totalresponse = 0.0;
        for (int response : responseMap.values()) {
            totalresponse += response;
        }

        return totalresponse / responseMap.size();
    }

    //calculates the biggest response
    public int calculateLargestResponse() {
        if (responseMap.isEmpty()) {
            return 0;
        }

        int maxResponse = 0;
        for (int response : responseMap.values()) {
            if (response > maxResponse) {
                maxResponse = response;
            }
        }

        return maxResponse;
    }

    //calculates the classes with over a provided number of response
    public int checkResponse(int i) {
        if (responseMap.isEmpty()) {
            return 0;
        }

        int responseCount = 0;
        for (int response : responseMap.values()) {
            if (response > i) {
                responseCount++;
            }
        }

        return responseCount;
    }
}
