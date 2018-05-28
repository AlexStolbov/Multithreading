package pingpong;

public class RectangleMove implements Runnable {

    private final Ball ball;
    private final int limitX;
    private final int limitY;

    public RectangleMove(Ball ball, int limitX, int limitY) {
        this.ball = ball;
        this.limitX = limitX;
        this.limitY = limitY;
    }

    /**
     * Двигает мяч от одной границы поля к другой после отбива мяча игроком.
     * Мяч, находящийся у границы не двигает.
     */
    @Override
    public void run() {
        boolean exit = false;
        while (true) {
            if (Thread.interrupted()) {
                exit = true;
            }
            if (!(ball.getRect().getX() == 0.0 || (ball.getRect().getX() == this.limitX - ball.getHeight()))) {
                newPos(this.ball);
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

    /**
     * Определяет новые координаты мяча
     * @param ball - мяч с новыми координатами
     * @return new position
     */
    private Ball newPos(Ball ball) {
        int delta = 1;
        if (!ball.isToRight()) {
            delta = -1;
        }
        double newX = ball.getRect().getX() + delta;
        ball.getRect().setY(ball.getRect().getY() - ball.getK() * (ball.getRect().getX() - newX));
        ball.getRect().setX(newX);
        return ball;
    }

}
