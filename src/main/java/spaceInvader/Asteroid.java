package spaceInvader;

import javafx.scene.shape.Polygon;

import java.util.Random;

public class Asteroid extends Sprite {

	private double rotation;

	public Asteroid(int x, int y) {
		super(new AsteroidFactory().createNewAsteroid(), x, y);

		Random rnd = new Random();

		super.getSprite().setRotate(rnd.nextInt(360));

		int speed = 1 + rnd.nextInt(10);
		for (int i = 0; i < speed; i++) {
			accelerate();
		}

		rotation = 0.5 - rnd.nextDouble();
	}

	@Override
	public void move() {
		super.move();
		super.getSprite().setRotate(super.getSprite().getRotate() + rotation);
	}
}
