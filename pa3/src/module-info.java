module pa3 {

	requires javafx.fxml;
	requires javafx.controls;

	exports Models;
	exports Controllers;
	exports Resources;

	opens Controllers to javafx.fxml;
}