package Utils;

import Shared.MenuItem;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.nio.charset.Charset;

public class ArrayTest {
	public static void main(String[] args) {
		File f = new File(System.getProperty("user.home") + "/Desktop/json.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int c;
			while(true) {
				c = reader.read();
				if(c == -1) {
					break;
				}
				baos.write(c);
			}
			String s = new String(baos.toByteArray(), Charset.forName("UTF-8"));
			ObjectMapper mapper = new ObjectMapper();
			MenuItem mi = mapper.readValue(s, MenuItem.class);
			System.out.println(mapper.writeValueAsString(mi));
			System.out.println(mi.id + mi.name + mi.description);
			if(mi.isAvailable) {
				System.out.println("true");
			}
			else {
				System.out.println("false");
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
