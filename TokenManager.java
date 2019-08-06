public class TokenManager{
	
	static synchronized long getToken(){

		return java.lang.System.currentTimeMillis();
	}
}