// token manager class that provide support for getting access tokens
public class TokenManager {

	static synchronized long getToken() {

		return java.lang.System.currentTimeMillis();
	}
}