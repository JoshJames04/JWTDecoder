package application.util;

import java.util.Base64;
import java.util.Map;
import java.util.HashMap;

/**
 * Decodes the JSON Web Token to plain english
 * 
 * @author John McNeil
 * @author Josh James
 * @author Klaus
 * @author Jorid
 * @version 1.0.0
 *
 */
public class Jwt_Decoder {

	private Jwt_Decoder() {
		throw new IllegalStateException("Jwt_Decoder Utility Class");
	}

	/**
	 * Passed a JSON Web Token and returns the decoded values. Method is static as
	 * there is no need persist data and the method should be fast
	 * 
	 * @param jwt <code>String</code> JSON Web Token
	 * @return <code>Map<String, String></code> decoded JSON Web Token
	 */
	public static Map<String, String> decodeJwt(String jwt) {
		String[] sections = jwt.split("[.]");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		HashMap<String, String> decodedJWT = new HashMap<>();

		String header = new String(decoder.decode(sections[0]));
		String payload = new String(decoder.decode(sections[1]));

		decodedJWT.put(header, payload);

		return decodedJWT;
	}
}