package application.view;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import application.util.Jwt_Decoder;
import application.view.util.Input_Validation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

/**
 * The controller for the FXML. Implements the JavaFX Initializable class
 * 
 * @author John McNeil
 * @author Josh James
 * @author Klaus
 * @author Jorid
 * @version 1.0.0
 *
 */
public class Jwt_Decoder_View_Controller implements Initializable {

	/**
	 * @param arg0
	 * @param arg1
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		// Use this method to set any objects in the UI on opening
		// Don't think we need it but just in case.
	}

	@FXML
	private TextArea textJwt;
	@FXML
	private TextArea payloadJwt;
	@FXML
	private TextArea headerJwt;
	@FXML
	private Button buttonJwt;
	@FXML
	private Button clearButtonJwt;

	/**
	 * Handles the decoding when the decode button is pressed
	 */
	@FXML
	private void handleDecode() {
		// System.out.println(textJwt.getText());
		if (Input_Validation.checkValidJwt(textJwt.getText()).length() < 1) {
			Map<String, String> decodedJwt = Jwt_Decoder.decodeJwt(textJwt.getText());
			decodedJwt.forEach((key, value) -> {
				// System.out.println(key);
				String headerHead = key.substring(0, 1);
				String headerTail = key.substring(key.length() - 1, key.length());
				String[] headerItem = key.substring(1, key.length() - 1).split(",");

				StringBuilder sb = new StringBuilder();
				sb.append(headerHead + "\n");
				for (String s : headerItem) {
					sb.append("\t" + s + "\n");
				}
				sb.append(headerTail + "\n");
				headerJwt.setText(sb.toString());

				// System.out.println(value);
				String payloadHead = value.substring(0, 1);
				String payloadTail = value.substring(value.length() - 1, value.length());
				String[] payloadItem = value.substring(1, value.length() - 1).split(",");

				sb = new StringBuilder();
				sb.append(payloadHead + "\n");
				for (String s : payloadItem) {
					sb.append("\t" + s + "\n");
				}
				sb.append(payloadTail + "\n");
				payloadJwt.setText(sb.toString());
			});
		}

		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error with JWT");
			alert.setHeaderText("Unable to decode JWT");
			alert.setContentText(Input_Validation.checkValidJwt(textJwt.getText()));
			alert.showAndWait();
		}
	}

	/**
	 * Handles the clearing of all text areas when the clear button is pressed
	 */
	@FXML
	private void handleClear() {
		payloadJwt.setText("");
		textJwt.setText("");
		headerJwt.setText("");
	}

}