package seng.qualitytester;

import java.util.List;

public class UserData {
    private List<String> MetList;
    private String directory;

    UserData(List<String> met, String dir){
        MetList = met;
        directory = dir;
    }

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
