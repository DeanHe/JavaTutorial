package Pluralsight.LambdaExpression;

@FunctionalInterface
public interface Predicate<T> {
    public boolean test(T t);

    default Predicate<T> and(Predicate<T> other){
       return x -> test(x) && other.test(x);
    }

    default Predicate<T> or(Predicate<T> other){
        return x -> test(x) || other.test(x);
    }

    static  <U> Predicate<U> equalsTo(U u){
        return x -> x.equals(u);
    }
}
