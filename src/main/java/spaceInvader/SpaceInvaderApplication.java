package spaceInvader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

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

		Scene scene = new Scene(screen);

		scene.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.LEFT)) {
				spaceship.setRotate(spaceship.getRotate() - 5);
			}

			if (event.getCode().equals(KeyCode.RIGHT)) {
				spaceship.setRotate(spaceship.getRotate() + 5);
			}
		});

		stage.setTitle("<< SPACE INVADER >>");
		stage.setScene(scene);
		stage.show();
	}
}
