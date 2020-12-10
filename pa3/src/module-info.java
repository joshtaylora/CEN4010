module pa3 {

	requires javafx.fxml;
	requires javafx.controls;

	exports Models;
	exports Controllers;

	opens Controllers to javafx.fxml;
}