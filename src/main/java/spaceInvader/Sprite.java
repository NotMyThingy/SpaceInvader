package spaceInvader;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public abstract class Sprite {

	private final Polygon sprite;
	private Point2D movement;

	public Sprite(Polygon sprite, int x, int y) {
		this.sprite = sprite;
		this.sprite.setTranslateX(x);
		this.sprite.setTranslateY(y);

		movement = new Point2D(0, 0);
	}

	public Polygon getSprite() {
		return sprite;
	}

	public void turnLeft() {
		sprite.setRotate(sprite.getRotate() - 5);
	}

	public void turnRight() {
		sprite.setRotate(sprite.getRotate() + 5);
	}

	public void move() {
		sprite.setTranslateX(sprite.getTranslateX() + movement.getX());
		sprite.setTranslateY(sprite.getTranslateY() + movement.getY());
	}

	public void accelerate() {
		double accelX = Math.cos(Math.toRadians(sprite.getRotate())) * 0.05;
		double accelY = Math.cos(Math.toRadians(sprite.getRotate())) * 0.05;

		movement = movement.add(accelX, accelY);
	}
}
