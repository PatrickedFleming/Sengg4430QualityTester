package seng.qualitytester;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import seng.qualitytester.Metrics.*;

import java.io.FileDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesterRunController {
    private UserData uData;


    public void setData(UserData in){
        uData = in;
        runMetricTests();
    }

    @FXML
    Label labelTest;
    private void runMetricTests(){
        List<String> chosenMets = uData.getMetList();
        String dir = uData.getDirectory();

        Reader read;
        try{
            read = new Reader(dir);
        }
        catch (IOException e){
            e.printStackTrace();
            return;
        }

        labelTest = new Label();

        Map<String, String> javaFiles = read.getJavaFiles();
        Map<String, String> filesDir = read.getFilesDir();


        Map<String, Integer> CyclomaticResults = new HashMap<>();
        Map<String, Integer> CommentCounterResults = new HashMap<>();
        Map<String, Integer> DOIResults = new HashMap<>();
        Map<String, Double> FogIndexResults = new HashMap<>();
        Map<String, Integer> NOCResults = new HashMap<>();
        Map<String, Integer> RFCResults = new HashMap<>();
        Map<String, Integer> WMCResults = new HashMap<>();

        for(String mets: chosenMets){
            //Cyclomatic Complexity
            switch (mets){
                case "CC":
                    for(Map.Entry<String, String> enter : javaFiles.entrySet()){
                        String code = enter.getValue();
                        int CyclomaticComplexity = CC.calculateCyclomaticComplexity(code);
                        CyclomaticResults.put(enter.getKey(), CyclomaticComplexity);
                    }
                    break;

                case "CommentCounter":
                    for(Map.Entry<String, String> enter : filesDir.entrySet()){
                        String fName = enter.getKey();
                        String code = enter.getValue();
                        CommentCounterResults.put(fName, CommentCounter.countComments(code));
                    }
                    break;

                case "DOI":
                    DOI doi = new DOI(javaFiles);
                    DOIResults = doi.calculateDepthOfInheritance();
                    break;

                case "FogIndexCalculator":
                    for(Map.Entry<String, String> enter : filesDir.entrySet()){
                        FogIndexResults.put(enter.getKey(), FogIndexCalculator.calculateFogIndex(enter.getValue()));
                    }
                    break;

                case "NOC":
                    NOC noc = new NOC(javaFiles);
                    NOCResults = noc.calculateNumberOfChildren();
                    break;

                case "RFC":
                    RFC rfc = new RFC(javaFiles);
                    RFCResults = rfc.calculateResponseForClass();
                    break;

                case "WMC":
                    for(Map.Entry<String, String> enter : javaFiles.entrySet()){
                        WMCResults.put(enter.getKey(), WMC.calculate(enter.getValue()));
                    }
                    break;
            }
        }
        System.out.println(CyclomaticResults);
        System.out.println(CommentCounterResults);
        System.out.println(DOIResults);
        System.out.println(FogIndexResults);
        System.out.println(NOCResults);
        System.out.println(RFCResults);
        System.out.println(WMCResults);
    }
}
