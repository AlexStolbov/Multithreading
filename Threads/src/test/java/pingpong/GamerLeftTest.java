package pingpong;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GamerLeftTest {

    @Test
    public void whenBallOnLeftBorderThenMoveItOnDelta() {
        int limitX = 30;
        int limitY = 30;
        double delta = 1;
        Ball ball = new Ball(limitY);
        assertThat(ball.getRect().getX(), is((double) 0));
        GamerLeft gamer = new GamerLeft(ball, limitX, limitY);
        Thread go = new Thread(gamer);
        go.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            return;
        }
        assertThat(ball.getRect().getX(), is(delta));
        go.interrupt();
    }
}