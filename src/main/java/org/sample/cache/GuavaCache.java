package org.sample.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * @author Sebastian Quast
 * @since 1.0.0
 */
public class GuavaCache<K,V> implements AbstractCache<K,V> {

    private Cache<K,V> cache;

    public GuavaCache(int size) {
        cache =CacheBuilder.newBuilder()
            .initialCapacity(size)
            .maximumSize(size)
            .build();
    }

    @Override
    public V get(K key) {
        return cache.getIfPresent(key);
    }

    @Override
    public void set(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public void remove(K key) {
        cache.invalidate(key);
    }

    @Override
    public int size() {
        return (int) cache.size();
    }

    @Override
    public void shutdown() {
        cache.cleanUp();
    }
}
