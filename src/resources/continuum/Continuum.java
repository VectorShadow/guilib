package resources.continuum;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

/**
 * A continuum of elements, which each have a certain probability of occurring.
 * To resolve a specific element, call getValue and pass in an existing Random object.
 *
 * @param <E> the class of the element
 */
public class Continuum<E> implements Serializable {
    E base;
    ArrayList<Pair<E>> pairList;

    public Continuum(E baseValue) {
        this(baseValue, new ArrayList<>());
    }

    public Continuum(E baseValue, ArrayList<Pair<E>> pairs) {
        base = baseValue;
        pairList = pairs;
        pairList.sort(new PairComparator());
    }
    public E getValue(Random r) {
        double roll = r.nextDouble();
        for (Pair<E> p : pairList) {
            if (roll < p.probability) return p.element;
        }
        return base;
    }
    private class PairComparator implements Comparator<Pair<E>> {

        @Override
        public int compare(Pair<E> o1, Pair<E> o2) {
            return Double.compare(o1.probability, o2.probability);
        }
    }

    public E getBase() {
        return base;
    }

    /**
     * Is e equivalent to the base of this continuum, or does one of the pairs belonging to this continuum contain e?
     */
    public boolean contains(E e) {
        if (base.equals(e)) return true;
        for (Pair<E> p : pairList) {
            if (p.contains(e))
                return true;
        }
        return false;
    }
}
