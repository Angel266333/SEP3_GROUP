package Shared;

import Utils.JsonMapper;

import javax.json.Json;
import java.util.Map;

public class MenuItem {
	public int id;
	public String name;
	public String description;
	public boolean isAvailable;

	public MenuItem() {}

	public MenuItem(int id, String name, String description, boolean isAvailable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return Json.createObjectBuilder()
				.add("id", id)
				.add("name", name)
				.add("description", description)
				.add("isAvailable", isAvailable)
				.build().toString();
	}

	public static MenuItem fromString(String serial) {
		Map<String, String> map = JsonMapper.parse(serial);
		int i = Integer.parseInt(map.get("id"));
		boolean ia = map.get("isAvailable").equals("true");
		return new MenuItem(i, map.get("name"), map.get("description"), ia);
	}
}
