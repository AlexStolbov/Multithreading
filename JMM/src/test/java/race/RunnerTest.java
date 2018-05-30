package race;

import org.junit.Test;

public class RunnerTest {

    /**
     * Два потока одновременно производят запись в объект, находящийся в куче.
     * Один поток прибавляет 10000 к контрольному счетчику, другой вычитат.
     * Ожидаем результатом 0. Но т.к. возникают гонки, результат может отличаться от нуля.
     * По строке общего объекта можно судить, в какой последовательности потоки обращались к нему.
     */
    @Test
    public void whenRunTwoThreadThenRace() {
        Counter cnt = new Counter();
        Thread th1 = new Thread(new Runner(true, cnt));
        Thread th2 = new Thread(new Runner(false, cnt));
        th1.start();
        th2.start();
        try {
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("finish value: %d \n", cnt.value());
        System.out.println(cnt.getSt().toString());
        //assertTrue(cnt.value() != 0);
    }
}