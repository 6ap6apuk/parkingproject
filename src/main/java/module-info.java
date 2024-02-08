module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires org.postgresql.jdbc;

    opens com.example.project to javafx.fxml;
    exports com.example.project;
}