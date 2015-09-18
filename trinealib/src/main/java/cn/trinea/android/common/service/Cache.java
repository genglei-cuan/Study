package cn.trinea.android.common.service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import cn.trinea.android.common.entity.CacheObject;

/**
 * Cache interface
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2011-12-23
 */
public interface Cache<K, V> {

    /**
     * get object in cache
     */
    public int getSize();

    /**
     * get object
     */
    public CacheObject<V> get(K key);

    /**
     * put object
     *
     * @param key   key
     * @param value data in object, {@link CacheObject#getData()}
     */
    public CacheObject<V> put(K key, V value);

    /**
     * put object
     *
     * @param key   key
     * @param value object
     */
    public CacheObject<V> put(K key, CacheObject<V> value);

    /**
     * put all object in cache2
     */
    public void putAll(Cache<K, V> cache2);

    /**
     * whether key is in cache
     */
    public boolean containsKey(K key);

    /**
     * remove object
     *
     * @return the object be removed
     */
    public CacheObject<V> remove(K key);

    /**
     * clear cache
     */
    public void clear();

    /**
     * get hit rate
     */
    public double getHitRate();

    /**
     * key set
     */
    public Set<K> keySet();

    /**
     * key value set
     */
    public Set<Map.Entry<K, CacheObject<V>>> entrySet();

    /**
     * value set
     */
    public Collection<CacheObject<V>> values();
}
