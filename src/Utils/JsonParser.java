package Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonParser {
	public static Map<String, String> parse(String json) {
		HashMap<String, String> res = new HashMap<>();

		char[] ca = json.substring(1, json.length() - 1).toCharArray();
		StringBuilder sb = new StringBuilder();
		ArrayList<String> props = new ArrayList<>();
		boolean inArray = false;
		for(char c : ca) {
			if(c == ',' && !inArray) {
				props.add(sb.toString());
				sb.delete(0, sb.length());
			}
			else {
				sb.append(c);
			}
			if(c == '[') {
				inArray = true;
			}
			else if(c == ']') {
				inArray = false;
			}
		}
		props.add(sb.toString());
		String[] ss;
		for(String s : props) {
			ss = s.split(":");
			ss[0] = ss[0].substring(1, ss[0].length() - 1);
			if(ss[1].charAt(0) == '"') {
				ss[1] = ss[1].substring(1, ss[1].length() - 1);
			}
			res.put(ss[0], ss[1]);
		}
		return res;
	}
}
