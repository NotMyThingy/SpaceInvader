package spaceInvader;

import javafx.geometry.Point2D;
import javafx.scene.shape.Polygon;

public class Spaceship {

	private final Polygon ship;
	private Point2D movement;

	public Spaceship(int x, int y) {
		ship = new Polygon(-5, -5, 10, 0, -5, 5);
		ship.setTranslateX(x);
		ship.setTranslateY(y);

		movement = new Point2D(0, 0);
	}

	public Polygon getShip() {
		return ship;
	}

	public void turnLeft() {
		ship.setRotate(ship.getRotate() - 5);
	}

	public void turnRight() {
		ship.setRotate(ship.getRotate() + 5);
	}

	public void move() {
		ship.setTranslateX(ship.getTranslateX() + movement.getX());
		ship.setTranslateY(ship.getTranslateY() + movement.getY());
	}

	public void accelerate() {
		double accelX = Math.cos(Math.toRadians(ship.getRotate()));
		double accelY = Math.cos(Math.toRadians(ship.getRotate()));

		movement = movement.add(accelX, accelY);
	}
}
