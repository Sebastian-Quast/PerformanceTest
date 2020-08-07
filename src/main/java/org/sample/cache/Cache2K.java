package org.sample.cache;

import org.cache2k.Cache;
import org.cache2k.Cache2kBuilder;

/**
 * @author Sebastian Quast
 * @since 1.0.0
 */
public class Cache2K<K,V> implements AbstractCache<K,V> {

    private Cache<K,V> cache;

    @SuppressWarnings("unchecked")
    public Cache2K(int size){
        cache = Cache2kBuilder.forUnknownTypes()
            .entryCapacity(size)
            .eternal(true)
            .build();
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public void set(K key, V value) {

    }

    @Override
    public void remove(K key) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void shutdown() {
        cache.clearAndClose();
    }
}
