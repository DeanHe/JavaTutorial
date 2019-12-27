package Pluralsight.LambdaExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CollectionsAPIdemo {
    public static void main(String[] args) {
        Person p1 = new Person("Alice", "Tom", 23);
        Person p2 = new Person("Brian", "Tom", 56);
        Person p3 = new Person("Chelsea", "Tom", 46);
        Person p4 = new Person("David", "Tom", 28);
        Person p5 = new Person("Erica", "Tom", 37);
        Person p6 = new Person("Franc", "Tom", 18);

        List<Person> people = new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6));
        //people.removeIf(p -> p.getAge() < 30);
        people.replaceAll(p -> new Person(p.getFirstName(), p.getLastName().toUpperCase(), p.getAge()));
        people.sort((a1, a2) -> a2.getAge() - a1.getAge());
        people.forEach(p -> System.out.println(p));
    }
}
