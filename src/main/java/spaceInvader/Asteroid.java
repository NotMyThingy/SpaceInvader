package spaceInvader;

import javafx.scene.shape.Polygon;

public class Asteroid extends Sprite {

	public Asteroid(int x, int y) {
		super(new Polygon(20, -20, 20, 20, -20, 20, -20, -20), x, y);
	}
}
