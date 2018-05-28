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
        boolean exit = false;
        while (true) {
            if (Thread.interrupted()) {
                exit = true;
            }
            if (ball.getRect().getX() == 0) {
                ball.move(true);
            }
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                exit = true;
            }
            if (exit) {
                return;
            }
        }
    }
}
