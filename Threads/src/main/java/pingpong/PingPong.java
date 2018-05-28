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
        Thread move = new Thread(new RectangleMove(ball, limitX, limitY));
        Thread gamerLeft = new Thread(new GamerLeft(ball, limitX, limitY));
        Thread gamerRight = new Thread(new GamerRight(ball, limitX, limitY));
        move.start();
        gamerLeft.start();
        gamerRight.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
        stage.setOnCloseRequest(event -> gameOver(move, gamerLeft, gamerRight));
    }

    private void gameOver(Thread... threads) {
        for (Thread th : threads) {
            th.interrupt();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
