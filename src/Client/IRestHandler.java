package Client;

public interface IRestHandler {
	public String get(String uri);
	public boolean set(String uri, byte[] value);
}
