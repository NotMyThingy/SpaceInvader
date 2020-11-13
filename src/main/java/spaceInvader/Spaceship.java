package spaceInvader;

import javafx.scene.shape.Polygon;

public class Spaceship extends Sprite {

	public Spaceship(int x, int y) {
		super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
	}

}
