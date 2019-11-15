package resources.continuum;

/**
 * A pair consisting of an element and the probability for that element
 * @param <E> the class of the element
 */
public class Pair<E> {
    public double probability;
    public E element;

    public Pair(double prob, E elem) {
        probability = prob;
        element = elem;
    }
}
