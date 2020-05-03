module org.rijnders {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.rijnders to javafx.fxml;
    exports org.rijnders;
}