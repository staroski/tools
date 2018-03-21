package br.com.staroski.tools.collections;

import java.util.Comparator;

/**
 * This class exists with the intent to adapts the existing {@link Comparator} in a way to avoid the following exception:<br>
 * 
 * <pre>
 * java.lang.IllegalArgumentException: Comparison method violates its general contract!
 * </pre>
 * 
 * This exception is common on legacy systems migrating from old Java versions to Java 7 or grater.<br>
 * It happens because in Java 7 the default sorting algorithm was changed from <b>Merge Sort</b> to <b>Tim Sort</b>.<br>
 * <br>
 * Example of use:
 * 
 * <pre>
 * &frasl;&frasl; some existent Comparator
 * Comparator myComparator = &lt;initializartion&gt;;
 * 
 * &frasl;&frasl; the existent Comparator adapted to notwork with TimSort
 * Comparator timSortSafe=ComparatorAdapter.timSortSafe(myComparator);
 * </pre>
 *
 * @author Ricardo Artur Staroski
 */
public final class ComparatorAdapter<T> implements Comparator<T> {

    /**
     * Adapts the existing {@link Comparator} in a way to avoid the following exception:
     * 
     * <pre>
     * java.lang.IllegalArgumentException: Comparison method violates its general contract!
     * </pre>
     * 
     * @param comparator
     *            The original {@link Comparator}.
     * @return The adapted {@link Comparator}
     */
    public static <T> Comparator<T> timSortSafe(Comparator<T> comparator) {
        if (comparator == null) {
            return null;
        }
        if (comparator instanceof ComparatorAdapter) {
            return (ComparatorAdapter<T>) comparator;
        }
        return new ComparatorAdapter<T>(comparator);
    }

    private final Comparator<T> comparator;

    private ComparatorAdapter(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int compare(T a, T b) {
        return comparator.compare(a, b) - comparator.compare(b, a);
    }
}
