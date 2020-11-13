package spaceInvader;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

import java.util.*;

public class SpaceInvaderApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane screen = new Pane();
		screen.setPrefSize(640, 480);

		Spaceship spaceship = new Spaceship(320, 240);
		List<Asteroid> asteroids = new ArrayList<>();
		while (asteroids.size() <= 5) {
			Random rnd = new Random();
			Asteroid asteroid = new Asteroid(rnd.nextInt(100), rnd.nextInt(100));
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

				spaceship.move();

				asteroids.forEach(asteroid -> asteroid.move());

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
