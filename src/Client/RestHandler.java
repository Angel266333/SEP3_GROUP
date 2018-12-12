package Client;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

public class RestHandler implements IRestHandler {
	private CloseableHttpClient client;
	private String server;
	private String token = "";

	public RestHandler(String server) {
		try {
			SSLContextBuilder scb = new SSLContextBuilder();
			scb.loadTrustMaterial(null, new TrustAllStrategy());
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(scb.build(), new NoopHostnameVerifier());
			client = HttpClients.custom().setSSLSocketFactory(sf).build();

		} catch(Exception e) {
			e.printStackTrace();
		}
		this.server = server;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public String get(String uri) {
		try {
			HttpGet req = new HttpGet(server + uri);
			req.setHeader("token", token);
			System.out.println(req.toString());
			CloseableHttpResponse response = client.execute(req);

			if(response.getStatusLine().getStatusCode() != 200) {
				response.close();
				return null;
			}

			String result = EntityUtils.toString(response.getEntity());
			response.close();
			return result;
		} catch(IOException e) {
			System.out.println("Connection error!");
			return null;
		}
	}

	@Override
	public boolean set(String uri, byte[] value) {
		HttpPut req = new HttpPut(server + uri);
		req.setHeader("token", token);
		req.setEntity(new ByteArrayEntity(value));
		try {
			CloseableHttpResponse response = client.execute(req);
			if(response.getStatusLine().getStatusCode() != 200) {
				response.close();
				return false;
			}
			response.close();
			return true;
		} catch(IOException e) {
			System.out.println("Connection error!");
			return false;
		}
	}

	public int setAndReturnKey(String uri, byte[] value) {
		HttpPut req = new HttpPut(server + uri);
		req.setHeader("token", token);
		req.setEntity(new ByteArrayEntity(value));
		try {
			CloseableHttpResponse response = client.execute(req);
			if(response.getStatusLine().getStatusCode() != 200) {
				response.close();
				return -1;
			}
			String s = EntityUtils.toString(response.getEntity());
			int key;
			key = Integer.parseInt(s);
			return key;
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}

	}
}
