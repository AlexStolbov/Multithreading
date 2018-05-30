package race;

//Поток
public class Runner implements Runnable {
    /**
     * Флаг, увеличивать(false) или уменьшать(true) поле cnt
     */

    private boolean decrement;
    /**
     *Общий объект в куче для потоков
     */
    private Counter cnt;

    public Runner(boolean decrement, Counter cnt) {
        this.decrement = decrement;
        this.cnt = cnt;
    }

    /**
     * Добавляет или вычитает 1 из контрольного счетчика (cnt)
     * Добавляет символ в строку индикатор выполнения потоков
     */
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            if (this.decrement) {
                this.cnt.decrement();
                addChar('0');
            } else {
                this.cnt.increment();
                addChar('|');
            }
        }
    }

    private void addChar(char ch) {
        if (this.cnt.getSt().length() < 650) {
            this.cnt.addChar(ch);
        }
    }
}
