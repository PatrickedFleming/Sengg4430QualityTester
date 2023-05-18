package seng.qualitytester;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Parent root = null;
        try {
            root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TesterHome.fxml")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Quality Tester!");
        primaryStage.show();

        primaryStage.sizeToScene();
    }

}
