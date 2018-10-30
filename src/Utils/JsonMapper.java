package Utils;

import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class JsonMapper {
	public static Map<String, String> parse(String serial) {
		JsonParser parser = Json.createParser(new StringReader(serial));
		String key = null;
		Map<String, String> map = new HashMap<>();
		while (parser.hasNext()) {
			JsonParser.Event event = parser.next();
			switch(event) {
				case KEY_NAME:
					key = parser.getString();
					break;
				case VALUE_STRING:
					map.put(key, parser.getString());
					break;
				case VALUE_NUMBER:
					map.put(key, parser.getBigDecimal().toString());
					break;
				case VALUE_TRUE:
					map.put(key, "true");
					break;
				case VALUE_FALSE:
					map.put(key, "false");
					break;
			}
		}
		parser.close();
		return map;
	}
}
