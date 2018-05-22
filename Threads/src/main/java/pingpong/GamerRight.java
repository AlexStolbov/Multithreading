package pingpong;

import javafx.scene.shape.Rectangle;

public class GamerRight implements Runnable {

    private final Ball ball;
    private final int limitX;
    private final int limitY;

    public GamerRight(Ball ball, int limitX, int limitY) {
        this.ball = ball;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Отбивает мяч от левой границы.
     */
    @Override
    public void run() {
        Rectangle curr = ball.getRect();
        while (true) {
            if (ball.isGameOver()) {
                return;
            }
            if (curr.getX() == this.limitX - ball.getHeight()) {
                ball.move(false);
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
