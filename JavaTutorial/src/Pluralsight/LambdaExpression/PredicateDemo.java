package Pluralsight.LambdaExpression;

public class PredicateDemo {
    public static void main(String[] args) {
        Predicate<String> p1 = s -> s.length() < 20;
        Predicate<String> p2 = s -> s.length() > 5;

        boolean res = p1.test("Hello");
        System.out.println("Hello is shorter than 20 chars : " + res);

        Predicate<String> p3 = p1.and(p2);
        System.out.println("p3 for Yes :" + p3.test("Yes"));
        System.out.println("p3 for Good Afternoon :" + p3.test("Good Afternoon"));
        System.out.println("p3 for Good Afternoon gentlemen :" + p3.test("Good Afternoon gentlemen"));

        Predicate<String> p4 = p1.or(p2);
        System.out.println("p4 for Yes :" + p4.test("Yes"));
        System.out.println("p4 for Good Afternoon :" + p4.test("Good Afternoon"));
        System.out.println("p4 for Good Afternoon gentlemen :" + p4.test("Good Afternoon gentlemen"));

        Predicate<String> p5 = Predicate.equalsTo("good");
        System.out.println("p5 for good :" + p5.test("good"));
        System.out.println("p5 for bad :" + p5.test("bad"));

        Predicate<Integer> p6 = Predicate.equalsTo(1);
        System.out.println("p6 for 1 :" + p6.test(1));
        System.out.println("p6 for 2 :" + p6.test(2));
    }
}
