package Shared;

import Utils.JsonBuilder;
import Utils.JsonParser;

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
		JsonBuilder jb = new JsonBuilder();
		jb.add("id", id);
		jb.add("name", name);
		jb.add("description", description);
		jb.add("isAvailable", isAvailable);
		return jb.toString();
	}

	public static MenuItem fromString(String serial) {
		Map<String, String> map = JsonParser.parse(serial);
		int id = Integer.parseInt(map.get("id"));
		boolean ia = ("true".equals(map.get("isAvailable")));
		return new MenuItem(id, map.get("name"), map.get("description"), ia);
	}
}
