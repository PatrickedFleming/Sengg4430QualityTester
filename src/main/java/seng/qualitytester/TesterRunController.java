package seng.qualitytester;


import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import seng.qualitytester.Metrics.*;

import java.io.IOException;
import java.util.*;

public class TesterRunController{
    private UserData uData;

    @FXML
    private GridPane gridResults;

    @FXML
    private TableView<Results> ResultsTable;
    @FXML
    private AnchorPane AnchorPaneResultsID;

    @FXML
    private TableColumn<Results, String> fileNameColumn;
    @FXML
    private TableColumn<Results, String> metricNameColumn;
    @FXML
    private TableColumn<Results, Object> resultColumn;
    private final  ObservableList<Results> table = FXCollections.observableArrayList();


    public void setData(UserData in){
        uData = in;
        runMetricTests();
        populateTable();
    }

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

        Map<String, String> javaFiles = read.getJavaFiles();
        Map<String, String> filesDir = read.getFilesDir();



        for(String mets: chosenMets){
            //Cyclomatic Complexity
            switch (mets) {
                case "CC" -> {
                    for (Map.Entry<String, String> enter : javaFiles.entrySet()) {
                        int CyclomaticComplexity = CC.calculateCyclomaticComplexity(enter.getValue());
                        Results in = new Results(enter.getKey(), "Cyclomatic Complexity", CyclomaticComplexity);
                        table.add(in);
                    }
                }
                case "CommentCounter" -> {
                    for (Map.Entry<String, String> enter : filesDir.entrySet()) {
                        int commentCount = CommentCounter.countComments(enter.getValue());
                        Results in = new Results(enter.getKey(), "Comment Counter", commentCount);
                        table.add(in);
                    }
                }
                case "DOCN" -> {
                    DOCN docn = new DOCN(javaFiles);
                    Map<String, Integer> DOCNResults = docn.calculateDepthOfConditionalNesting();
                    for (Map.Entry<String, Integer> enter : DOCNResults.entrySet()) {
                        Results in = new Results(enter.getKey(), "Depth of Conditional Nesting", enter.getValue());
                        table.add(in);
                    }
                }
                case "DOI" -> {
                    DOI doi = new DOI(javaFiles);
                    Map<String, Integer> DOIResults = doi.calculateDepthOfInheritance();
                    for (Map.Entry<String, Integer> enter : DOIResults.entrySet()) {
                        Results in = new Results(enter.getKey(), "Depth of Inheritance", enter.getValue());
                        table.add(in);
                    }
                }
                case "FogIndexCalculator" -> {
                    for (Map.Entry<String, String> enter : filesDir.entrySet()) {
                        double result = FogIndexCalculator.calculateFogIndex(enter.getValue());
                        Results in = new Results(enter.getKey(), "Fog Index", result);
                        table.add(in);
                    }
                }
                case "NOC" -> {
                    NOC noc = new NOC(javaFiles);
                    Map<String, Integer> nocR = noc.calculateNumberOfChildren();
                    for (Map.Entry<String, Integer> enter : nocR.entrySet()) {
                        Results in = new Results(enter.getKey(), "Number of Children", enter.getValue());
                        table.add(in);
                    }
                }
                case "RFC" -> {
                    RFC rfc = new RFC(javaFiles);
                    Map<String, Integer> rfcR = rfc.calculateResponseForClass();
                    for (Map.Entry<String, Integer> enter : rfcR.entrySet()) {
                        Results in = new Results(enter.getKey(), "Response for Class", enter.getValue());
                        table.add(in);
                    }
                }
                case "WMC" -> {
                    for (Map.Entry<String, String> enter : javaFiles.entrySet()) {
                        int WMCResults = WMC.calculate(enter.getValue());
                        Results in = new Results(enter.getKey(), "Weighted Methods pe Class", WMCResults);
                        table.add(in);
                    }
                }
            }
        }
    }

    @FXML
    public void initialize(){
        AnchorPane.setRightAnchor(gridResults, 0.0);
        AnchorPane.setTopAnchor(gridResults, 0.0);
        AnchorPane.setLeftAnchor(gridResults, 0.0);
        AnchorPane.setBottomAnchor(gridResults, 0.0);
    }

    @FXML
    void exportToExcell() {
        ExcellSave.exportToExcel(ResultsTable, (Stage) AnchorPaneResultsID.getScene().getWindow());
    }

    @FXML
    public void backButton(){

        try {
            Parent home = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TesterHome.fxml")));
            Scene homeScene = new Scene(home);


            Stage stage = (Stage) AnchorPaneResultsID.getScene().getWindow();

            stage.setScene(homeScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateTable(){
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<>("fName"));
        metricNameColumn.setCellValueFactory(new PropertyValueFactory<>("mName"));
        resultColumn.setCellValueFactory(cellData -> {
            Results result = cellData.getValue();
            if (result.getDoubleorInt() == 1) {
                return new SimpleObjectProperty<>(result.getResult());
            }
            else{
                return new SimpleObjectProperty<>(result.getDoubleResult());
            }
        });
        ResultsTable.setItems(table);
    }
}
