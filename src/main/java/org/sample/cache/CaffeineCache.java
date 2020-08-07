package org.sample.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author Sebastian Quast
 * @since 1.0.0
 */
public class CaffeineCache<K,V> implements AbstractCache<K,V> {

    private Cache<K,V> cache;

    public CaffeineCache(int size){
        cache = Caffeine.newBuilder()
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
    }

    @Override
    public int size() {
        return (int)cache.estimatedSize();
    }

    @Override
    public void shutdown() {
        cache.cleanUp();
    }
}
