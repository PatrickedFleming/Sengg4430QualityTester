package seng.qualitytester;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TesterHomeController {
    //references to objects in gui
    @FXML
    private TableView<MetricsData> TableViewID;
    @FXML
    private ObservableList<MetricsData> items;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane AnchorHomeID;
    @FXML
    private TextField DirTxtBox;
    @FXML
    private CheckBox AllTickBox;
    @FXML
    private TableColumn<MetricsData, String> metrics;
    @FXML
    private TableColumn<MetricsData, String> names;
    @FXML
    private TableColumn<MetricsData, String> selects;

    //handling the select directory event
    @FXML
    private void SelectDirectory() {
        final DirectoryChooser dirchoose = new DirectoryChooser();

        Stage stage = (Stage) AnchorHomeID.getScene().getWindow();

        File file = dirchoose.showDialog(stage);

        if (file != null) {
            DirTxtBox.setText(file.getAbsolutePath());
        }
    }

    //controlling the population of table and the
    @FXML
    public void initialize() {

        AllTickBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            items = TableViewID.getItems();

            for (MetricsData item : items) {
                item.getRemark().setSelected(AllTickBox.isSelected());
            }
        });

        List<MetricsData> tableD = new ArrayList<>();

        tableD.add(new MetricsData("The Gunning Fog Index", "Sina", "FogIndexCalculator"));
        tableD.add(new MetricsData("Number of Comments", "Sina", "CommentCounter"));
        tableD.add(new MetricsData("Weighted Methods per Class", "Asad", "WMC"));
        tableD.add(new MetricsData("Cyclomatic Complexity", "Asad", "CC"));
        tableD.add(new MetricsData("Depth of Inheritance", "Patrick", "DOI"));
        tableD.add(new MetricsData("Number of Children", "Patrick", "NOC"));
        tableD.add(new MetricsData("Depth of Conditional Nesting", "Harrison", ""));
        tableD.add(new MetricsData("Response for a Class (RFC)", "Harrison", "RFC"));

        ObservableList<MetricsData> data = FXCollections.observableArrayList(tableD);

        metrics.setCellValueFactory(new PropertyValueFactory<>("metric"));
        names.setCellValueFactory(new PropertyValueFactory<>("name"));
        selects.setCellValueFactory(new PropertyValueFactory<>("remark"));

        TableViewID.setItems(data);

        AnchorPane.setRightAnchor(gridPane, 0.0);
        AnchorPane.setTopAnchor(gridPane, 0.0);
        AnchorPane.setLeftAnchor(gridPane, 0.0);
        AnchorPane.setBottomAnchor(gridPane, 0.0);

    }

    @FXML
    public void RunTests() {
        items = TableViewID.getItems();
        List<String> mets = new ArrayList<>();
        for(MetricsData item : items){
            if(item.getRemark().isSelected()) {
                mets.add(item.getCode());
            }
        }
        String inDir = DirTxtBox.getText();

        if (!isDirectoryValid(inDir)) {
            showAlert();
            return; // Stop the program from sending the data
        }
        UserData send = new UserData(mets, inDir);



        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TesterRun.fxml"));
            Parent root = loader.load();

            TesterRunController runTestCon = loader.getController();
            runTestCon.setData(send);

            Stage stage = (Stage) AnchorHomeID.getScene().getWindow();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isDirectoryValid(String directory) {
        File file = new File(directory);
        return file.exists() && file.isDirectory();
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Directory");
        alert.setHeaderText(null);
        alert.setContentText("Please select a valid directory.");
        alert.showAndWait();
    }
}



