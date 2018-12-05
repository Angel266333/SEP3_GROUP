package Utils;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.UUID;

public class Token {
	private static String token = "";
	private static char[] alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890".toCharArray();

	public static void readToken() {
		String home = System.getProperty("user.home");
		File f = new File(home + "/kashikoi.token");
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			token = br.readLine();
			br.close();
		} catch(Exception e) {
			token = "";
		}
	}

	public static void generateToken() {
		String home = System.getProperty("user.home");
		try {
			File f = new File(home + "/kashikoi.token");
			if(!f.exists()) {
				f.createNewFile();
			}
			byte[] bt = new byte[64];
			new SecureRandom().nextBytes(bt);
			StringBuilder sb = new StringBuilder(64);
			int i;
			for(byte b : bt) {
				i = (int) b & 0b01111111;
				sb.append(alpha[i % alpha.length]);
			}
			String t = sb.toString();
			FileWriter fw = new FileWriter(f);
			fw.write(t);
			fw.flush();
			fw.close();
			token = t;
		} catch(IOException e) {
			System.err.println("Failed to generate token!");
		}
	}

	public static String getToken() {
		return token;
	}

	public static boolean validate(String token) {
		return getToken().equals(token);
	}

	public static boolean validate(HttpExchange httpExchange) {
		return validate(httpExchange.getRequestHeaders().getFirst("token"));
	}
}
