package Utils;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.security.KeyStore;

public class HttpsServerCreator {
	public static HttpsServer create(InetSocketAddress insa) {
		try {
			//Load java keystore
			KeyStore keyStore = KeyStore.getInstance("JKS");
			FileInputStream fis = new FileInputStream(System.getProperty("user.home") + "/kstore.jks");
			keyStore.load(fis, "kashikoi".toCharArray());

			//Get keys
			KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(keyStore, "kashikoi".toCharArray());

			//Get trust settings (trust managers)
			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(keyStore);

			//Create SSL handler (SSLcontext)
			SSLContext ssc = SSLContext.getInstance("TLS");
			ssc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

			//Create HttpsServer and return it
			HttpsServer hs = HttpsServer.create(insa, 0);
			hs.setHttpsConfigurator(new HttpsConfigurator(ssc));
			return hs;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
