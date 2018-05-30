package visibility;

class ThreadB implements Runnable {

    /**
     * Ссылка на общий объект.
     */
    Shared m;

    /**
     * Счетчик увеличений
     */
    int countChange;

    ThreadB(Shared m) {
        this.m = m;
    }

    /**
     *Через каждые 3мс, если поле a общего объекта увеличилось, увеличивает счетчик.
     */
    @Override
    public void run() {
        int a = 0;
        int i = 0;
        while (true) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                return;
            }
            if (a < m.a) {
                a = m.a;
                countChange++;
            }
        }
    }
}
