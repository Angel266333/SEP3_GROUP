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

	public static String buildArray(String... s) {
		StringBuilder a = new StringBuilder();
		a.append('[');
		for(String str : s) {
			a.append('"');
			a.append(s);
			a.append("\",");
		}
		a.append(']');
		return a.toString();
	}

	public static String buildArray(int... s) {
		StringBuilder a = new StringBuilder();
		a.append('[');
		for(int str : s) {
			a.append(s);
			a.append(',');
		}
		a.append(']');
		return a.toString();
	}

	public static String buildArray(long... s) {
		StringBuilder a = new StringBuilder();
		a.append('[');
		for(long str : s) {
			a.append(s);
			a.append(',');
		}
		a.append(']');
		return a.toString();
	}

	public static String buildArray(boolean... s) {
		StringBuilder a = new StringBuilder();
		a.append('[');
		for(boolean str : s) {
			a.append(s);
			a.append(',');
		}
		a.append(']');
		return a.toString();
	}
}
