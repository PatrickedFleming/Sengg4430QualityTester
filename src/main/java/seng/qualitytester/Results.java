package seng.qualitytester;


public class Results {
    private final String fName;
    private final String mName;
    private int result;
    private double dResult;
    private final int doubleorInt;

    Results(String file, String metric, int in){
        fName = file;
        mName = metric;
        result = in;
        doubleorInt = 1;
    }

    Results(String file, String metric, double in){
        fName = file;
        mName = metric;
        dResult = in;
        doubleorInt = 0;
    }

    public String getFName(){
        return fName;
    }

    public String getMName(){
        return mName;
    }

    public int getResult(){
        return result;
    }

    public double getDoubleResult(){
        return dResult;
    }

    public int getDoubleorInt(){
        return doubleorInt;
    }

    @Override
    public String toString(){
        if(doubleorInt == 1) {
            return "File Name: " + fName + " Metric Name: " + mName + " Result: " + result;
        }
        else if(doubleorInt == 0){
            return "File Name: " + fName + " Metric Name: " + mName + " Result: " + dResult;
        }
        else{
            return "No results";
        }
    }
}
