package org.sample.cache;

/**
 * @author Sebastian Quast
 * @since 1.0.0
 */
public interface AbstractCache<K,V> {

    V get(K key);

    void set(K key, V value);

    void remove(K key);

    int size();

    void shutdown();

}
