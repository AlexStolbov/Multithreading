package visibility;

import org.junit.Test;

/**
 * Запускает 2 потока.
 * Поток А увеличивает поле общего объекта shared на 1.
 * Поток B считает количество этих увеличений.
 * Дожидаемся завершения потока А и останавливаем поток В.
 * Ожидаем, что поле общего объекта и счетчик увеличений будут одинаковыми.
 * Но т.к. поток B может не увидеть изменения, производимые потоком А, счетчик
 * увеличений будет меньше, чем поле общего объекта.
 */
public class VisTest {

    @Test
    public void test() {
        Shared shared = new Shared();
        Thread a = new Thread(new ThreadA(shared));
        ThreadB thb = new ThreadB(shared);
        Thread b = new Thread(thb);
        a.start();
        b.start();
        try {
            a.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        b.interrupt();
        try {
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("shared.a: %d \n", shared.a);
        System.out.printf("count of change: %d \n", thb.countChange);

    }

}