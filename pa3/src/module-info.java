module pa3 {

    requires javafx.fxml;
    requires javafx.controls;

    exports Controllers;
    exports Models;
    opens Controllers to javafx.fxml;
}