// 323013276 Ruth Ehrmann
package game;
/**
 * A game.Counter class that keeps track of a count.
 * Provides methods to increase, decrease, and retrieve the current count.
 */
public class Counter {
    // The current count value
    private int counter;
    /**
     * Constructs a new game.Counter initialized to zero.
     */
    public Counter() {
        this.counter = 0;
    }
    /**
     * Constructs a new game.Counter initialized to the given value.
     *
     * @param initialValue the initial value of the counter
     */
    public Counter(int initialValue) {
        this.counter = initialValue;
    }
    /**
     * Adds the specified number to the current count.
     *
     * @param number the number to add to the current count
     */
    public void increase(int number) {
        this.counter += number;
    }
    /**
     * Subtracts the specified number from the current count.
     *
     * @param number the number to subtract from the current count
     */
    public void decrease(int number) {
        this.counter -= number;
    }
    /**
     * Returns the current count value.
     *
     * @return the current count value
     */
    public int getValue() {
        return this.counter;
    }
}
