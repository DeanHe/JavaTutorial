package Pluralsight.LambdaExpression;

import java.util.function.Function;

/**
 *
 * @author José Paumard
 */
@FunctionalInterface
public interface CustomComparator<T> {

    public int compare(T t1, T t2);
    
    public default CustomComparator<T> thenComparing(CustomComparator<T> cmp) {
        
        return (p1, p2) -> compare(p1, p2) == 0 ? cmp.compare(p1, p2) : compare(p1, p2) ;
    }
    
    public default CustomComparator<T> thenComparing(Function<T, Comparable> f) {
        
        return thenComparing(comparing(f)) ;
    }
    
    public static <U> CustomComparator<U> comparing(Function<U, Comparable> f) {
        
        return (p1, p2) ->  f.apply(p1).compareTo(f.apply(p2));
    }

    static CustomComparator<Person> comparingInt(Function<Person, Integer> f) {
        return (p1, p2) -> f.apply(p1) - f.apply(p2);
    }
}
