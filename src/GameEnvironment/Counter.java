/**
 * @author Nadav Mor 206492233 <\>.
 * @version JDK 19.
 * @since 2022-09-20.
 */
package GameEnvironment;

/**
 * The type Counter.
 */
public class Counter {

    /**
     * primitive type for counting.
     */
    private int counter;


    /**
     * Method constructs instantiates of new Counter.
     *
     * @param number starting number for counting.
     */
    public Counter(int number) {
        this.counter = number;
    }

    /**
     * Method increasing counter by given number.
     *
     * @param number number for adding to count.
     */
    void increase(int number) {
        this.counter = this.counter + number;
    }


    /**
     * Method decreasing counter by give number.
     *
     * @param number number for decreasing from count.
     */
    void decrease(int number) {
        this.counter = this.counter - number;
    }


    /**
     * Method Sets counter.
     *
     * @param counter the counter for setting.
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    /**
     * Method current number in counting.
     *
     * @return current number in counting.
     */
    int getValue() {
        return this.counter;
    }
}

