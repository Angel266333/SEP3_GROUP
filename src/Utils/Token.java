package Utils;

import java.io.*;
import java.util.UUID;

public class Token {
	private static String token = "";

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
			String t = UUID.randomUUID().toString();
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
}
