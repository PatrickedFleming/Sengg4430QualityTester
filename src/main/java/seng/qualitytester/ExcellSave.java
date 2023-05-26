package seng.qualitytester;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Exports the table of results from tests as a CSV file
public class ExcellSave {

    //Gets location of save through file choose then writes results to file
    public static void exportToExcel(TableView<Results> table, Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Excel File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File file = fileChooser.showSaveDialog(primaryStage);
        if (file != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

                // Write header row
                StringBuilder header = new StringBuilder();
                for (TableColumn<Results, ?> column : table.getColumns()) {
                    header.append(column.getText()).append(",");
                }
                writer.write(header.toString());
                writer.newLine();

                // Write data rows
                for (Results result : table.getItems()) {
                    StringBuilder row = new StringBuilder();
                    for (TableColumn<Results, ?> column : table.getColumns()) {
                        Object cellValue = column.getCellData(result);
                        row.append(cellValue != null ? cellValue.toString() : "").append(",");
                    }
                    writer.write(row.toString());
                    writer.newLine();
                }

                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}