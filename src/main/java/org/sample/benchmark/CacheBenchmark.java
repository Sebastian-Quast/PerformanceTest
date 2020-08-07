package org.sample.benchmark;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Group;
import org.openjdk.jmh.annotations.GroupThreads;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.sample.cache.AbstractCache;
import org.sample.cache.Cache2K;
import org.sample.cache.CaffeineCache;
import org.sample.cache.EhCache;
import org.sample.cache.GuavaCache;

/**
 * @author Sebastian Quast
 * @since 1.0.0
 */
public class CacheBenchmark {


    @State(Scope.Group)
    //measures the raw throughput by continuously calling the benchmark method in a time-bound iteration
    @BenchmarkMode(Mode.Throughput)
    //TimeUnit
    @OutputTimeUnit(TimeUnit.SECONDS)
    //Forked JMVs
    @Fork(1)
    //Warmup iteration - Just-In-Time compiler (JIT)
    @Warmup(iterations = 2)
    //How many iterations should be measured
    @Measurement(iterations = 2)
    public static abstract class AbstractBenchmark {

        protected final int cacheSize = 10000;
        protected AbstractCache<Integer, String> abstractCache;

        @State(Scope.Thread)
        public static class ThreadState {
            static final ThreadLocalRandom random = ThreadLocalRandom.current();
            private int key;

            public int getKey() {
                return random.nextInt(10000);
            }
        }

        @Setup
        public abstract void setup();

        @Benchmark
        @Group("read_only")
        @GroupThreads(8)
        public String readValueFromCache(ThreadState state) {
            return abstractCache.get(state.getKey());
        }

        @Benchmark
        @Group("write_only")
        @GroupThreads(8)
        public void writeValueToCache(ThreadState state) {
            int key = state.getKey();
            abstractCache.set(key, "user:"+key);
        }

        @Benchmark
        @Group("read_write")
        @GroupThreads(6)
        public String readWriteReadValueFromCache(ThreadState state){
            return abstractCache.get(state.getKey());
        }

        @Benchmark
        @Group("read_write")
        @GroupThreads(2)
        public void readWriteWrite(ThreadState state) {
            int key = state.getKey();
            abstractCache.set(key, "user:"+key);
        }

        protected void populateCache(AbstractCache<Integer,String> cache) {
            for (int k = 0; k < cacheSize; k++) {
                cache.set(k, "user:"+k);
            }
        }

        @TearDown(Level.Iteration)
        public void tearDown() {
            abstractCache.shutdown();
        }
    }

    public static class CaffeineBenchmark extends AbstractBenchmark {

        @Override
        public void setup() {
            abstractCache = new CaffeineCache<>(cacheSize);
            populateCache(abstractCache);
        }
    }

//    public static class Cache2KBenchmark extends AbstractBenchmark {
//
//        @Override
//        public void setup() {
//            abstractCache = new Cache2K<>(cacheSize);
//            populateCache(abstractCache);
//        }
//    }

    public static class EhCacheBenchmark extends AbstractBenchmark {

        @Override
        public void setup() {
            abstractCache = new EhCache<>(cacheSize);
            populateCache(abstractCache);
        }
    }

    public static class GuavaBenchmark extends AbstractBenchmark {

        @Override
        public void setup() {
            abstractCache = new GuavaCache<>(cacheSize);
            populateCache(abstractCache);
        }
    }
}


