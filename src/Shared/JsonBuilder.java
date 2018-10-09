package Shared;

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

	public void addArray(String name, String array) {
		sb.append('"');
		sb.append(name);
		sb.append("\":[");
		sb.append(array);
		sb.append("],");
	}

	@Override
	public String toString() {
		char[] ca = sb.toString().toCharArray();
		ca[ca.length - 1] = '}';
		return String.copyValueOf(ca);
	}
}
