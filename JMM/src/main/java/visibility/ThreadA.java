package visibility;

class ThreadA implements Runnable {

    /**
     * Ссылка на общий объект.
     */
    Shared m;

    ThreadA(Shared m) {
        this.m = m;
    }

    /**
     * Через кажые 10мс увеличивает поле a общего объекта на единицу.
     */
    @Override
    public void run() {
        int i = 0;
        while (i < 600) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                return;
            }
            m.a++;
            i++;
        }
    }
}