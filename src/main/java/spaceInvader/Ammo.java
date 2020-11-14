package spaceInvader;

import javafx.scene.shape.Polygon;

public class Ammo extends Sprite {

	public Ammo(int x, int y) {
		super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
	}

}
