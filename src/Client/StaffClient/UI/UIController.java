package Client.UI;

import javafx.scene.Parent;

public abstract class UIController {
	protected UIContainer container;

	abstract String getTitle();
	abstract Parent getParent();
	public void setContainer(UIContainer container) {
		this.container = container;
	}
}
