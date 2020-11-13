package spaceInvader;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Spaceship extends Sprite {

	private Point2D movement;

	public Spaceship(int x, int y) {
		super(new Polygon(-5, -5, 10, 0, -5, 5), x, y);
	}

	public Polygon getShip() {
		return super.getSprite();
	}

}
