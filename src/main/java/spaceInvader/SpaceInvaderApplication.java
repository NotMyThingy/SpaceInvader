package spaceInvader;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SpaceInvaderApplication extends Application {

	public final static int WIDTH = 640;
	public final static int HEIGHT = 480;

	private AtomicInteger score;

	@Override
	public void start(Stage stage) {
		Pane screen = new Pane();
		screen.setPrefSize(WIDTH, HEIGHT);

		score = new AtomicInteger();

		Text status = new Text(10, 20, "Points: 0");
		screen.getChildren().add(status);

		Spaceship spaceship = new Spaceship(WIDTH / 2, HEIGHT / 2);

		List<Bullet> ammunition = new ArrayList<>();
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

				if (keyPressed.getOrDefault(KeyCode.SPACE, false)) {
					Bullet bullet = new Bullet(
							(int) spaceship.getSprite().getTranslateX(),
							(int) spaceship.getSprite().getTranslateY());
					bullet.getSprite()
							.setRotate(spaceship
									.getSprite()
									.getRotate());
					bullet.accelerate();
					bullet.setVelocity(bullet.getVelocity().normalize().multiply(3));

					ammunition.add(bullet);

					screen.getChildren().add(bullet.getSprite());
				}

				spaceship.move();
				asteroids.forEach(Asteroid::move);
				ammunition.forEach(Bullet::move);

				asteroids.forEach(asteroid -> {
					if (spaceship.isColliding(asteroid)) {
						stop();
					}
				});

				ammunition.forEach(bullet -> {
					asteroids.forEach(asteroid -> {
						if (bullet.isColliding(asteroid)) {
							bullet.setAlive(false);
							asteroid.setAlive(false);
							status.setText("Points: " + score.addAndGet(1000));
						}
					});
				});

				ammunition.stream()
						.filter(Bullet::isDead)
						.forEach(bullet -> screen.getChildren().remove(bullet.getSprite()));
				ammunition.removeAll(ammunition.stream()
						.filter(Bullet::isDead)
						.collect(Collectors.toList()));

				asteroids.stream()
						.filter(Asteroid::isDead)
						.forEach(asteroid -> screen.getChildren().remove(asteroid.getSprite()));
				asteroids.removeAll(asteroids.stream()
						.filter(Asteroid::isDead)
						.collect(Collectors.toList()));

				if (Math.random() < 0.005) {
					Asteroid asteroid = new Asteroid(WIDTH, HEIGHT);
					if (!asteroid.isColliding(spaceship)) {
						asteroids.add(asteroid);
						screen.getChildren().add(asteroid.getSprite());
					}
				}
			}
		}.start();

		stage.setTitle("<< SPACE INVADER >>");
		stage.setScene(scene);
		stage.show();
	}
}
