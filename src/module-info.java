module app.jwt {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	requires javafx.base;

	exports application.view;

	opens application;
	opens application.view;
}