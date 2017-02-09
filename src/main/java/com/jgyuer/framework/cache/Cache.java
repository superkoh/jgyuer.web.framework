package com.jgyuer.framework.cache;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KOH on 2017/1/16.
 * <p>
 * goodtalk-server
 */
public interface Cache {
    void set(final String key, Serializable obj);

    void set(final String key, Serializable obj, int seconds);

    <T> T get(final String key);

    <T> T getWithEstablish(final String key, ValueGenerator<T> generator);

    <T> T getWithEstablish(final String key, ValueGenerator<T> generator, int seconds);

    void del(final String key);

    void delBatch(final List<String> keys);

    public static interface ValueGenerator<T> {
        T generate();
    }
}
