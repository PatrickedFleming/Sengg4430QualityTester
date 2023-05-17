module seng.qualitytester {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.github.javaparser.core;


    opens seng.qualitytester to javafx.fxml;
    exports seng.qualitytester;
}