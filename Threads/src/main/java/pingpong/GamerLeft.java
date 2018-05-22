package pingpong;

import javafx.scene.shape.Rectangle;

public class GamerLeft implements Runnable {
    private final Ball ball;
    private final int limitX;
    private final int limitY;

    public GamerLeft(Ball ball, int limitX, int limitY) {
        this.ball = ball;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Отбивает мяч от левой границы.
     */
    @Override
    public void run() {
        while (true) {
            if (ball.isGameOver()) {
                return;
            }
            if (ball.getRect().getX() == 0) {
                ball.move(true);
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
