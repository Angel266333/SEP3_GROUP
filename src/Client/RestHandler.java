package Client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class RestHandler implements IRestHandler {
	private CloseableHttpClient client;
	private String server;
	private String session = "";

	public RestHandler(String server) {
		client = HttpClients.createDefault();
		this.server = server;
	}

	public void setSession(String session) {
		this.session = session;
	}

	@Override
	public String get(String uri) {
		try {
			HttpGet req = new HttpGet(server + uri);
			req.setHeader("session", session);
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
		req.setHeader("session", session);
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
}
