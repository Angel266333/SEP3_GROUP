package Utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.nio.charset.Charset;

public class BodyReader {
	public static byte[] read(InputStream is) {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			int i;
			while(true) {
				i = bis.read();
				if(i == -1) {
					break;
				}
				baos.write(i);
			}
			is.close();
			bis.close();
			return baos.toByteArray();
		} catch(IOException e) {
			return null;
		}
	}

	public static String readString(InputStream is) {
		return new String(read(is), Charset.forName("utf-8"));
	}
}
