package race;

/**
 * Общий класс для потоков
 */
public class Counter {
    /**
     * Контрольный счетчик
     */
    private int c = 0;
    /**
     * Строка индикатор выполнения потоков
     */
    private StringBuilder st = new StringBuilder();

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public void addChar(char ch) {
        st.append(ch);
    }

    public int value() {
        return c;
    }

    public StringBuilder getSt() {
        return st;
    }
}
