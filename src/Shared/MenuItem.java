package Shared;

public class MenuItem {
	public int id;
	public String name;
	public String description;
	public boolean isAvailable;

	public MenuItem(int id, String name, String description, boolean isAvailable) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "";
		//TODO
		//Make actual serialisation
	}

	public static MenuItem fromString(String serial) {
		return new MenuItem(0, "","", true);
		//TODO
		//Make real de-serialisation
	}
}
