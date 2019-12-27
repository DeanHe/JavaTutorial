package Pluralsight.LambdaExpression;



import java.util.function.Function;

/**
 *
 * @author José Paumard
 */
public class ComparatorDemo {

    public static void main(String... args) {

        CustomComparator<Person> cmpAge = (p1, p2) -> p2.getAge() - p1.getAge() ;
        CustomComparator<Person> cmpFirstName = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName()) ;
        CustomComparator<Person> cmpLastName = (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()) ;
        
        Function<Person, Integer> f1 = p -> p.getAge();
        Function<Person, String> f2 = p -> p.getLastName();
        Function<Person, String> f3 = p -> p.getFirstName();

        CustomComparator<Person> cmpPerson = CustomComparator.comparingInt(f1);

        CustomComparator<Person> cmpPersonAge = CustomComparator.comparing(Person::getAge);
        CustomComparator<Person> cmpPersonLastName = CustomComparator.comparing(Person::getLastName);
        
        
        CustomComparator<Person> cmp = CustomComparator.comparing(Person::getLastName)
                                           .thenComparing(Person::getFirstName)
                                           .thenComparing(Person::getAge);
    }
}
