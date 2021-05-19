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
 * The main class used to start the JavaFX system. Extends the JavaFX
 * Application class.
 * 
 * @author John McNeil
 * @author Josh James
 * @author Klaus Capani
 * @author Jorid Spaha
 * @version 1.0.0 
 */
public class Jwt_Decoder_App extends Application {

	/**
	 * Used to start our JavaFX application
	 * 
	 * @param primaryStage The stage created by JavaFX
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			// This just has default values for now.
			AnchorPane root = (AnchorPane) FXMLLoader
					.load(getClass().getResource("/application/view/Jwt_Decoder_View.fxml"));
			Scene scene = new Scene(root);

			scene.getStylesheets().add(getClass().getResource("/application/view/application.css").toExternalForm());
			primaryStage.getIcons().add(new Image(this.getClass().getResourceAsStream("resources/img/JWTDecoder.png")));
			primaryStage.setTitle("JSON Web Token Decoder");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);

			centerOnScreen(primaryStage);
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Used as the main method of the program
	 * 
	 * @param args Runtime arguments parsed by the system
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Used to center the window on the screen
	 * 
	 * @param primaryStage The stage object to center
	 */
	private static void centerOnScreen(Stage primaryStage) {
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		double centerX = (screenBounds.getWidth() - primaryStage.getWidth()) / 2;
		double centerY = (screenBounds.getHeight() - primaryStage.getHeight()) / 2;

		primaryStage.setX(centerX);
		primaryStage.setY(centerY);
	}

}
