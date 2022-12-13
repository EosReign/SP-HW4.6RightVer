package prosky.sphw46rightver;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Task45_4 {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            long time1 = sum();
            long time2 = parallelSum();
            long time3 = sum2();
            long time4 = parallelSum2();

            System.out.println(time1 + " " + time2 + " " + time3 + " " + time4);
        }
    }

    public static long sum() {
        long time = System.currentTimeMillis();
        long sum = Stream.iterate(1L, a -> a + 1)
                .limit(1_000_000)
                .reduce(0L, (a, b) -> a + b);
        return System.currentTimeMillis() - time;
    }

    public static long parallelSum() {
        long time = System.currentTimeMillis();
        long sum = Stream.iterate(1L, a -> a + 1)
                .limit(1_000_000)
                .parallel()
                .reduce(0L, (a, b) -> a + b);
        return System.currentTimeMillis() - time;
    }

    public static long sum2() {
        long time = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, 1_000_000)
                .reduce(0L, Long::sum);
        return System.currentTimeMillis() - time;
    }

    public static long parallelSum2() {
        long time = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(1, 1_000_000)
                .parallel()
                .reduce(0L, Long::sum);
        return System.currentTimeMillis() - time;
    }


}
