import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Random random = new Random();
        List<Integer> list = Stream.iterate(random.nextInt(100), i -> i <= 100, i -> random.nextInt(100))
                .limit(15)
                .collect(Collectors.toList());
        System.out.println(list);
        Stream<Integer> stream = list.stream();

        Comparator<Integer> comparator = Integer::compareTo;

        BiConsumer<Integer, Integer> biConsumer = (integer, integer2) -> {
            System.out.println("Минимальное число " + integer);
            System.out.println("Максимальное число " + integer2);
        };

        findMinMax(stream, comparator, biConsumer);

        evenNumbers(list);
    }

    public static <T> void findMinMax(Stream<? extends T> stream, Comparator<? super T> order, BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<T> list = stream.sorted(order).collect(Collectors.toList());
        T min = list.get(0);
        T max = list.get(list.size() - 1);
        if (!list.isEmpty()) {
            minMaxConsumer.accept(min, max);
        } else {
            minMaxConsumer.accept(null, null);
        }
    }

    public static void evenNumbers(List<Integer> list) {
        System.out.println("Количество четных чмсел в списке: " + list.stream()
                .filter(i -> i % 2 == 0)
                .count());
        System.out.println(list.stream()
                .filter(i -> i % 2 == 0)
                .collect(Collectors.toList()));


    }
}