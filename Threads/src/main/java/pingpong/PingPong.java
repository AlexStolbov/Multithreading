package pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    @Override
    public void start(Stage stage) {
        int limitX = 100;
        int limitY = 100;
        Group group = new Group();
        Ball ball = new Ball(limitY);
        group.getChildren().add(ball.getRect());
        new Thread(new RectangleMove(ball, limitX, limitY)).start();
        new Thread(new GamerLeft(ball, limitX, limitY)).start();
        new Thread(new GamerRight(ball, limitX, limitY)).start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
