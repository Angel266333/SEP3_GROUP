package SslDraft;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsServer;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.KeyStore;
import java.util.Scanner;

import static Server.REST.Response.OK;

public class Server {
	public HttpsServer hServer;
	private int count;

	public Server() throws Exception {
		count = 0;
		SSLContext ssc = SSLContext.getInstance("TLS");
		KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(new FileInputStream(System.getProperty("user.home") + "/kstore.jks"), "kashikoi".toCharArray());

		KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
		kmf.init(keyStore, "kashikoi".toCharArray());
		KeyManager km[] = kmf.getKeyManagers();

		TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
		tmf.init(keyStore);
		TrustManager tm[] = tmf.getTrustManagers();

		ssc.init(km, tm, null);
		hServer = HttpsServer.create(new InetSocketAddress(8001), 0);
		hServer.setHttpsConfigurator(new HttpsConfigurator(ssc));

		hServer.createContext("/", new HttpHandler() {
			@Override
			public void handle(HttpExchange httpExchange) throws IOException {
				count++;
				OK(httpExchange, (count + " requests sent since last restart").getBytes());
			}
		});

		hServer.start();
	}

	public static void main(String[] args) {
		try {
			Server s = new Server();
			Scanner sc = new Scanner(System.in);
			sc.nextLine();
			s.hServer.stop(0);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
