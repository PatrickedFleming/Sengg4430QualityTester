package seng.qualitytester;

import java.util.List;

//Class used to hold all data needed to run tests on new scene
public class UserData {
    private List<String> MetList;
    private String directory;

    //Constructor
    UserData(List<String> met, String dir){
        MetList = met;
        directory = dir;
    }

    //Getters and Setters
    public void setMetList(List<String> in){
        MetList = in;
    }

    public void setDirectory(String in){
        directory = in;
    }

    public List<String> getMetList(){
        return MetList;
    }

    public String getDirectory() {
        return directory;
    }
}
