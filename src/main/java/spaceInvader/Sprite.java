package spaceInvader;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Sprite {

	private final Polygon sprite;
	private Point2D velocity;
	private boolean isAlive;

	public Sprite(Polygon sprite, int x, int y) {
		this.sprite = sprite;
		this.sprite.setTranslateX(x);
		this.sprite.setTranslateY(y);
		isAlive = true;

		velocity = new Point2D(0, 0);
	}

	public Polygon getSprite() {
		return sprite;
	}

	public Point2D getVelocity() {
		return velocity;
	}

	public void setVelocity(Point2D velocity) {
		this.velocity = velocity;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isDead() {
		return !isAlive;
	}

	public void setAlive(boolean alive) {
		isAlive = alive;
	}

	public void turnLeft() {
		sprite.setRotate(sprite.getRotate() - 5);
	}

	public void turnRight() {
		sprite.setRotate(sprite.getRotate() + 5);
	}

	public void move() {
		sprite.setTranslateX(sprite.getTranslateX() + velocity.getX());
		sprite.setTranslateY(sprite.getTranslateY() + velocity.getY());

		if (sprite.getBoundsInParent().getMinX() < 0) {
			sprite.setTranslateX(sprite.getTranslateX() + SpaceInvaderApplication.WIDTH);
		}

		if (sprite.getBoundsInParent().getMaxX() > SpaceInvaderApplication.WIDTH) {
			sprite.setTranslateX(sprite.getTranslateX() % SpaceInvaderApplication.WIDTH);
		}

		if (sprite.getBoundsInParent().getMinY() < 0) {
			sprite.setTranslateY(sprite.getTranslateY() + SpaceInvaderApplication.HEIGHT);
		}

		if (sprite.getBoundsInParent().getMaxY() > SpaceInvaderApplication.HEIGHT) {
			sprite.setTranslateY(sprite.getTranslateY() % SpaceInvaderApplication.HEIGHT);
		}
	}

	public void accelerate() {
		double accelX = Math.cos(Math.toRadians(sprite.getRotate())) * 0.05;
		double accelY = Math.sin(Math.toRadians(sprite.getRotate())) * 0.05;

		velocity = velocity.add(accelX, accelY);
	}

	public boolean isColliding(Sprite other) {
		Shape crashZone = Shape.intersect(this.sprite, other.getSprite());
		return crashZone.getBoundsInLocal().getWidth() != -1;
	}

}
