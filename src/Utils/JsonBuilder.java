package Utils;

public class JsonBuilder {
	StringBuilder sb;

	public JsonBuilder() {
		sb = new StringBuilder();
		sb.append('{');
	}

	public void add(String name, String value) {
		sb.append('"');
		sb.append(name);
		sb.append("\":\"");
		sb.append(value);
		sb.append("\",");
	}

	public void add(String name, int value) {
		sb.append('"');
		sb.append(name);
		sb.append("\":");
		sb.append(value);
		sb.append(',');
	}

	public void add(String name, long value) {
		sb.append('"');
		sb.append(name);
		sb.append("\":");
		sb.append(value);
		sb.append(',');
	}

	public void add(String name, boolean value) {
		sb.append('"');
		sb.append(name);
		sb.append("\":");
		sb.append(value);
		sb.append(',');
	}
}
