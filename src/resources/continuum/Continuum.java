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

    public Continuum(ArrayList<E> balancedValues) {
        base = balancedValues.get(0);
        pairList = balanceElements(balancedValues);
        //automatically sorts
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

    public ArrayList<Pair<E>> getPairList() {
        return pairList;
    }

    /**
     * Is e equivalent to the base of this continuum, or does one of the pairs belonging to this continuum contain e?
     */
    public boolean contains(E e) {
        if (base != null && base.equals(e)) return true;
        for (Pair<E> p : pairList) {
            if (p.contains(e))
                return true;
        }
        return false;
    }

    public static double balancedPercent(int total) {
        return 1.0 / (double)total;
    }
    public ArrayList<Pair<E>> balanceElements(ArrayList<E> elements) {
        ArrayList<Pair<E>> balancedElements = new ArrayList<>();
        double pctEach;
        double pctNext = pctEach = balancedPercent(elements.size());
        for (int i = 1; i < elements.size(); ++i) {
            balancedElements.add(new Pair<>(pctNext, elements.get(i)));
            pctNext += pctEach;
        }
        return balancedElements;
    }

    /**
     * Return a new continuum with all values besides the base multiplied by the threshold.
     * Threshold values < 1.0 will result in a compression, with the base expanding by the total difference.
     * Threshold values > 1.0 will result in an expansion, which can possibly increase multiple values over 1.0.
     * This will result in those values becoming inaccessible to getValue().
     */
    public Continuum adjust(double threshold) {
        if (threshold < 0.0)
            throw new IllegalArgumentException("threshold must be non-negative");
        ArrayList<Pair<E>> compressedList = new ArrayList<>();
        for (Pair<E> p : pairList)
            compressedList.add(new Pair<E>(p.probability * threshold, p.element));
        return new Continuum(base, compressedList);
    }
}
