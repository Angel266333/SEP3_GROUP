package Client;

import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

public interface IRestHandler {
	public String get(String uri);
	public boolean set(String uri, byte[] value);
}
