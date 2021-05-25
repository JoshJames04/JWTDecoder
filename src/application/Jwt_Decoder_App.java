package application;

import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;

/**
 * JWTDecoder Application
 * 
 * @author John McNeil and Josh James
 */
public class Jwt_Decoder_App extends Application {
	@Override
	public void start(Stage primaryStage) {
		// This just has default values for now.
		try {
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/application/view/Jwt_Decoder_View.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/application/view/application.css").toExternalForm());
			primaryStage.getIcons()
					.add(new Image(this.getClass().getResourceAsStream("/application/resources/img/icon.png")));
			primaryStage.setTitle("JSON Web Token Decoder");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);

			centerOnScreen(primaryStage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private static void centerOnScreen(Stage primaryStage) {
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		double centerX = (screenBounds.getWidth() - primaryStage.getWidth()) / 2;
		double centerY = (screenBounds.getHeight() - primaryStage.getHeight()) / 2;

		primaryStage.setX(centerX);
		primaryStage.setY(centerY);
	}

}
