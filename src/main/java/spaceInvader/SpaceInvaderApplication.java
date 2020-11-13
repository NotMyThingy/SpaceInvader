package spaceInvader;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

		Polygon spaceship = new Polygon(-5, -5, 10, 0, -5, 5);
		spaceship.setFill(Color.RED);
		spaceship.setTranslateX(320);
		spaceship.setTranslateY(240);

		screen.getChildren().add(spaceship);

		Map<KeyCode, Boolean> keyPressed = new HashMap<>();

		Scene scene = new Scene(screen);
		scene.setOnKeyPressed(event -> keyPressed.put(event.getCode(), true));
		scene.setOnKeyReleased(event -> keyPressed.put(event.getCode(), false));

		new AnimationTimer() {
			@Override
			public void handle(long present) {
				if (keyPressed.getOrDefault(KeyCode.LEFT, false)) {
					spaceship.setRotate(spaceship.getRotate() - 5);
				}

				if (keyPressed.getOrDefault(KeyCode.RIGHT, false)) {
					spaceship.setRotate(spaceship.getRotate() + 5);
				}
			}
		}.start();

		stage.setTitle("<< SPACE INVADER >>");
		stage.setScene(scene);
		stage.show();
	}
}
