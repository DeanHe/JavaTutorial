package Pluralsight.LambdaExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author José Paumard
 */
public class CollectionMapAPIDemo {

    public static void main(String[] args) {

        Person p1 = new Person("Alice", "Tom", 23);
        Person p2 = new Person("Brian", "Tom", 56);
        Person p3 = new Person("Chelsea", "Tom", 46);
        Person p4 = new Person("David", "Tom", 28);
        Person p5 = new Person("Erica", "Tom", 37);
        Person p6 = new Person("Franc", "Tom", 18);

        City newYork = new City("New York");
        City shanghai = new City("Shanghai");
        City paris = new City("Paris");

        Map<City, List<Person>> map = new HashMap<>();

        map.putIfAbsent(paris, new ArrayList<>());
        map.get(paris).add(p1);

        map.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p2);
        map.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p3);

        System.out.println("People from Paris: " + map.getOrDefault(paris, Collections.EMPTY_LIST));
        System.out.println("People from New York: " + map.getOrDefault(newYork, Collections.EMPTY_LIST));


        Map<City, List<Person>> map1 = new HashMap<>();
        map1.computeIfAbsent(newYork, city -> new ArrayList<>()).add(p1);
        map1.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p2);
        map1.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p3);

        Map<City, List<Person>> map2 = new HashMap<>();
        map2.computeIfAbsent(shanghai, city -> new ArrayList<>()).add(p4);
        map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p5);
        map2.computeIfAbsent(paris, city -> new ArrayList<>()).add(p6);

        map2.forEach((city, people) -> {
            map1.merge(city, people, (people1, people2) -> {
                people1.addAll(people2);
                return people1;
            });
        });

        map1.forEach((city, people) -> {
            System.out.println(city + " : " + people);
        });
    }
}
