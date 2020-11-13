package spaceInvader;

import javafx.scene.shape.Polygon;

import java.util.Random;

public class AsteroidFactory {

	public Polygon createNewAsteroid() {
		Random rnd = new Random();

		double size = 10 + rnd.nextInt(10);

		Polygon asteroid = new Polygon();
		double c1 = Math.cos(Math.PI * 2 / 5);
		double c2 = Math.cos(Math.PI / 5);
		double s1 = Math.sin(Math.PI * 2 / 5);
		double s2 = Math.sin(Math.PI * 4 / 5);

		asteroid.getPoints().addAll(
				size, 0.0,
				size * c1, -1 * size * s1,
				-1 * size * c2, -1 * size * c2,
				-1 * size * c2, size * s2,
				size * c1, size * s1
		);

		for (int i = 0; i < asteroid.getPoints().size(); i++) {
			int diff = rnd.nextInt(5) - 2;
			asteroid.getPoints().set(i, asteroid.getPoints().get(i) + diff);
		}

		return asteroid;
	}
}
