package Pluralsight.LambdaExpression;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BuildingStreamDemo {
    public static void main(String[] args) {

        List<Integer> ints = Arrays.asList(0, 1, 2, 3, 4);

        Stream<Integer> stream1 = ints.stream();
        Optional<Integer> opt = ints.stream().findFirst();
        Optional<Integer> opt2 = ints.stream().findAny();
        System.out.println(opt.get() + " and " + opt2.get());
        Stream<Integer> stream2 = Stream.of(0, 1, 2, 3, 4, 5, 6, 7);

        stream2.skip(2).filter(i -> i < 7).forEach(System.out::println);

        Stream<String> streamStr = Stream.generate(() -> "one");
        streamStr.limit(3).forEach(System.out::println);
        boolean b = streamStr.allMatch(s -> s.equals("one"));
        System.out.println("streamStr all match one is " + b);

        Stream<String> streamStr2 = Stream.iterate("$", s -> s + "&" + s);
        streamStr2.limit(3).forEach(System.out::println);

        IntStream streamInt = ThreadLocalRandom.current().ints();
        streamInt.limit(3).forEach(System.out::println);

    }
}
