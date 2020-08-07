package org.sample.cache;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;

/**
 * @author Sebastian Quast
 * @since 1.0.0
 */
public class EhCache<K,V> implements AbstractCache<K,V> {

    private Cache<K,V> cache;

    @SuppressWarnings("unchecked")
    public EhCache(int size) {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);
        cache = (Cache<K, V>) cacheManager.createCache("benchmark",
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.newResourcePoolsBuilder()
                    .heap(size, EntryUnit.ENTRIES))
                .build());
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void set(K key, V value) {
        cache.put(key, value);
    }

    @Override
    public void remove(K key) {
        cache.remove(key);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void shutdown() {
        cache.clear();
    }
}
