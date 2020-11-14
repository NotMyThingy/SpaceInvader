package spaceInvader;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import java.util.*;

public class SpaceInvaderApplication extends Application {

	public final static int WIDTH = 640;
	public final static int HEIGHT = 480;

	@Override
	public void start(Stage stage) {
		Pane screen = new Pane();
		screen.setPrefSize(WIDTH, HEIGHT);

		Spaceship spaceship = new Spaceship(WIDTH / 2, HEIGHT / 2);

		List<Ammo> ammunition = new ArrayList<>();
		List<Asteroid> asteroids = new ArrayList<>();

		while (asteroids.size() <= 5) {
			Random rnd = new Random();
			Asteroid asteroid = new Asteroid(rnd.nextInt(WIDTH / 3), rnd.nextInt(HEIGHT));
			asteroids.add(asteroid);
		}

		screen.getChildren().add(spaceship.getSprite());
		asteroids.forEach(asteroid -> screen.getChildren().add(asteroid.getSprite()));

		Map<KeyCode, Boolean> keyPressed = new HashMap<>();

		Scene scene = new Scene(screen);
		scene.setOnKeyPressed(event -> keyPressed.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keyPressed.put(event.getCode(), false));

		new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (keyPressed.getOrDefault(KeyCode.LEFT, false)) {
					spaceship.turnLeft();
				}

				if (keyPressed.getOrDefault(KeyCode.RIGHT, false)) {
					spaceship.turnRight();
				}

				if (keyPressed.getOrDefault(KeyCode.UP, false)) {
					spaceship.accelerate();
				}

				if (keyPressed.getOrDefault(KeyCode.SPACE, false) && ammunition.size() < 5) {
					Ammo ammo = new Ammo(
							(int) spaceship.getSprite().getTranslateX(),
							(int) spaceship.getSprite().getTranslateY());
					ammo.getSprite()
							.setRotate(spaceship
									.getSprite()
									.getRotate());
					ammo.accelerate();
					ammo.setMovement(ammo.getMovement().normalize().multiply(3));

					ammunition.add(ammo);

					screen.getChildren().add(ammo.getSprite());
				}

				spaceship.move();
				asteroids.forEach(Asteroid::move);
				ammunition.forEach(Ammo::move);

				asteroids.forEach(asteroid -> {
					if (spaceship.crashed(asteroid)) {
						stop();
					}
				});

			}
		}.start();

		stage.setTitle("<< SPACE INVADER >>");
		stage.setScene(scene);
		stage.show();
	}
}
