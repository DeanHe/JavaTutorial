package Pluralsight.StreamsCollectorsOptionals;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapDemo {
    public static void main(String[] args) throws Exception{
        Integer[] arr = {4,3,2,1};
        Arrays.sort(arr, Comparator.comparingInt(Integer::intValue));
        String url = "";
        Stream<String> s1 = Files.lines(Paths.get(url));
        Stream<String> s2 = Files.lines(Paths.get(url));
        Stream<String> s3 = Files.lines(Paths.get(url));
        Stream<String> s4 = Files.lines(Paths.get(url));

        Stream<Stream<String>> streamOfStreams = Stream.of(s1, s2, s3, s4);
        Stream<String> streamOfLines = streamOfStreams.flatMap(s -> s);
        System.out.println("# of Lines " + streamOfLines.count());

        Function<String, Stream<String>> lineSpliter = line -> Pattern.compile(" ").splitAsStream(line);
        Stream<String> streamOfWords = streamOfLines.flatMap(lineSpliter).map(word -> word.toUpperCase()).distinct();
        System.out.println("# of Words " + streamOfWords.count());

    }
}
