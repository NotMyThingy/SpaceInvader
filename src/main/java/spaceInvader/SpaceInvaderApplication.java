package spaceInvader;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class SpaceInvaderApplication extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Pane screen = new Pane();
		screen.setPrefSize(640, 480);

		Spaceship spaceship = new Spaceship(320, 240);

		screen.getChildren().add(spaceship.getShip());

		Map<KeyCode, Boolean> keyPressed = new HashMap<>();

		Scene scene = new Scene(screen);
		scene.setOnKeyPressed(event -> keyPressed.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keyPressed.put(event.getCode(), false));

		Point2D movement = new Point2D(1, 0);

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
			}
		}.start();

		stage.setTitle("<< SPACE INVADER >>");
		stage.setScene(scene);
		stage.show();
	}
}
