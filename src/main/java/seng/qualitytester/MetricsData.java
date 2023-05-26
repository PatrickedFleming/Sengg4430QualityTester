package seng.qualitytester;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

//Class used hold metrics data, so it can be sent to new scene
public class MetricsData {
    private final SimpleStringProperty metric;
    private final SimpleStringProperty name;
    private final SimpleStringProperty code;
    private CheckBox remark;

    //Constructor
    MetricsData (String mName, String pName,String cName){
        this.metric = new SimpleStringProperty(mName);
        this.name = new SimpleStringProperty(pName);
        this.code = new SimpleStringProperty(cName);
        this.remark = new CheckBox();
    }

    //Getters and Setters
    public String getMetric(){
        return metric.get();
    }

    public void setMetric(String in){
        metric.set(in);
    }

    public String getName(){
        return name.get();
    }

    public void setName(String in){
        name.set(in);
    }

    public CheckBox getRemark(){
        return remark;
    }

    public void setRemark(CheckBox in){
        this.remark = in;
    }

    public String getCode(){
        return code.get();
    }

    public void setCode(String in){
        code.set(in);
    }
}
