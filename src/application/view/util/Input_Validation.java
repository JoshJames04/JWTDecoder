package application.view.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validating user input
 * 
 * @author John McNeil
 * @author Josh James
 * @author Klaus
 * @author Jorid
 * @version 1.0.0
 *
 */
public class Input_Validation {

	private Input_Validation() {
		// Input Validation utility class
	}

	/**
	 * Check the JSON Web Token to ensure it is a valid token Method is static as
	 * there is no need to persist data
	 * 
	 * @param jwt <code>String</code> JSON Wed Token for validation
	 * @return <code>String</code> error msg or empty string
	 */
	public static String checkValidJwt(String jwt) {
		String[] jwtParts = jwt.split("[.]");

		// Valid Base64URI encoding characters
		String regex = "^([A-Za-z0-9_-]+)";
		String[] results = { "Invalid character in Header", "Invalid character in Payload",
				"Invalid character in Signature" };
		String result = "";

		if (jwtParts.length != 3) {
			result = "JWT does not contain a Header, Payload and Signature element.";
		} else {
			for (int i = 0; i < jwtParts.length; i++) {
				// Load the Regex pattern for testing
				Pattern p = Pattern.compile(regex);
				// Test the String against the regex pattern
				Matcher matcher = p.matcher(jwtParts[i]);
				// If no match found set reason
				if (!matcher.matches()) {
					result = results[i];
				}
			}
		}
		return result;
	}

}
