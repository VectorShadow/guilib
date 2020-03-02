package resources.continuum;

import java.io.Serializable;

/**
 * A pair consisting of an element and the probability for that element
 * @param <E> the class of the element
 */
public class Pair<E> implements Serializable {
    public double probability;
    public E element;

    private Pair(Pair<E> pair) {
        this(pair.probability, pair.element);
    }

    public Pair(double prob, E elem) {
        probability = prob;
        element = elem;
    }

    /**
     * Create a new Pair with a probability adjusted to accommodate insertion of a new probability.
     * @param insertedProbability
     * @return
     */
    public Pair<E> compress(double insertedProbability) {
        if (insertedProbability <= 0.0 || insertedProbability > 1.0)
            throw new IllegalArgumentException("Invalid probability value: " + insertedProbability);
        double compressTo = 1.0 - insertedProbability;
        return new Pair<>((compressTo * probability) + insertedProbability, element);
    }

    @Override
    public Pair<E> clone() {
        return new Pair<E>(this);
    }

}
