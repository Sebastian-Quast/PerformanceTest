//package org.sample;
//
//
//import java.util.concurrent.ThreadLocalRandom;
//import java.util.concurrent.TimeUnit;
//import org.openjdk.jmh.annotations.Benchmark;
//import org.openjdk.jmh.annotations.BenchmarkMode;
//import org.openjdk.jmh.annotations.Fork;
//import org.openjdk.jmh.annotations.Level;
//import org.openjdk.jmh.annotations.Measurement;
//import org.openjdk.jmh.annotations.Mode;
//import org.openjdk.jmh.annotations.OutputTimeUnit;
//import org.openjdk.jmh.annotations.Param;
//import org.openjdk.jmh.annotations.Scope;
//import org.openjdk.jmh.annotations.Setup;
//import org.openjdk.jmh.annotations.State;
//import org.openjdk.jmh.annotations.TearDown;
//import org.openjdk.jmh.annotations.Threads;
//import org.openjdk.jmh.annotations.Warmup;
//
///**
// * @author Sebastian Quast
// * @since 1.0.0
// */
//@State(Scope.Benchmark)
////measures the raw throughput by continuously calling the benchmark method in a time-bound iteration
//@BenchmarkMode(Mode.Throughput)
////TimeUnit
//@OutputTimeUnit(TimeUnit.SECONDS)
////Forked JMVs
//@Fork(1)
////Warmup iteration - Just-In-Time compiler (JIT)
//@Warmup(iterations = 1)
////How many iterations should be measured
//@Measurement(iterations = 1)
//public class CacheBenchmark2 implements BasicBenchmark {
//
//    private final int cacheSize = 10000;
//    private static ThreadLocalRandom random = ThreadLocalRandom.current();
//
//    private AbstractCache<Integer, String> abstractCache;
//
//    @Param({"Caffein", "Cache2K", "EhCache"})
//    protected String current;
//
//    @Setup
//    public void setup() {
//        switch (current) {
//            case "Caffein":
//                setUpCaffein();
//                break;
//            case "Cache2K":
//                setUpCache2K();
//                break;
//            case "EhCache":
//                setUpEhCache();
//                break;
//            default:
//                throw new AssertionError("Unknown computingType: " + current);
//        }
//    }
//
//    @Override
//    @Benchmark
//    @Threads(8)
//    public String measureGetPerformance() {
//        return abstractCache.get(random.nextInt(0, cacheSize));
//    }
//
//    protected void populateCache(AbstractCache<Integer,String> cache) {
//        for (int k = 0; k < cacheSize; k++) {
//            cache.set(k, "user:"+k);
//        }
//    }
//
//    @TearDown(Level.Iteration)
//    public void tearDown() {
//        System.out.println("tearDown()!");
//        abstractCache.shutdown();
//    }
//
//    private void setUpCache2K() {
//        System.out.println("setUpCache2K()");
//        abstractCache = new Cache2K(cacheSize);
//        populateCache(abstractCache);
//    }
//
//    private void setUpCaffein() {
//        System.out.println("setUpCaffein()");
//        abstractCache = new CaffeinCache(cacheSize);
//        populateCache(abstractCache);
//        System.out.println(abstractCache.size());
//    }
//
//    private void setUpEhCache() {
//        System.out.println("setUpEhCache()");
//        abstractCache = new EhCache(cacheSize);
//        populateCache(abstractCache);
//    }
//}
