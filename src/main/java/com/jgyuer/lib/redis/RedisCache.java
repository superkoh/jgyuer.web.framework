package com.jgyuer.lib.redis;

import com.jgyuer.framework.cache.Cache;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Pipeline;

import java.io.Serializable;
import java.util.List;

/**
 * Created by KOH on 16/6/30.
 */
public class RedisCache implements Cache {
    private static Logger logger = LoggerFactory.getLogger("redisCache");

    private JedisPool pool;

    public RedisCache(JedisPool pool) {
        this.pool = pool;
    }

    public void set(final String key, Serializable obj) {
        try (Jedis jedis = pool.getResource()) {
            jedis.set(key.getBytes(), SerializationUtils.serialize(obj));
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
        }
    }

    public void set(final String key, Serializable obj, int seconds) {
        try (Jedis jedis = pool.getResource()) {
            byte[] bKey = key.getBytes();
            Pipeline p = jedis.pipelined();
            p.set(bKey, SerializationUtils.serialize(obj));
            if (seconds >= 0) {
                p.expire(bKey, seconds);
            }
            p.sync();
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
        }
    }

    public <T> T get(final String key) {
        try (Jedis jedis = pool.getResource()) {
            byte[] value = jedis.get(key.getBytes());
            if (null != value) {
                return SerializationUtils.deserialize(value);
            }
            return null;
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
            return null;
        }
    }

    public <T> T getWithEstablish(final String key, Cache.ValueGenerator<T> generator) {
        return getWithEstablish(key, generator, -1);
    }

    public <T> T getWithEstablish(final String key, Cache.ValueGenerator<T> generator, int seconds) {
        try (Jedis jedis = pool.getResource()) {
            byte[] value = jedis.get(key.getBytes());
            if (null == value) {
                T obj = generator.generate();
                if (null != obj) {
                    this.set(key, (Serializable) obj, seconds);
                    return obj;
                }
            } else {
                return SerializationUtils.deserialize(value);
            }
            return null;
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
            T obj = generator.generate();
            if (null != obj) {
                return obj;
            }
            return null;
        }
    }

    public void del(final String key) {
        try (Jedis jedis = pool.getResource()) {
            jedis.del(key.getBytes());
        } catch (Exception ignored) {
        }
    }

    public void delBatch(final List<String> keys) {
        try (Jedis jedis = pool.getResource()) {
            Pipeline p = jedis.pipelined();
            for (String key : keys) {
                p.del(key.getBytes());
            }
            p.sync();
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
        }
    }

    public void flushAll() {
        try (Jedis jedis = pool.getResource()) {
            jedis.flushAll();
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
        }
    }

    public void hset(final String key, final String field, Serializable obj) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hset(key.getBytes(), field.getBytes(), SerializationUtils.serialize(obj));
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
        }
    }

    public <T> T hget(final String key, final String field) {
        try (Jedis jedis = pool.getResource()) {
            byte[] value = jedis.hget(key.getBytes(), field.getBytes());
            if (null != value) {
                return SerializationUtils.deserialize(value);
            }
            return null;
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
            return null;
        }
    }

    public <T> T hgetWithEstablish(final String key, final String field, ValueGenerator<T> generator) {
        try (Jedis jedis = pool.getResource()) {
            byte[] value = jedis.hget(key.getBytes(), field.getBytes());
            if (null == value) {
                T obj = generator.generate();
                if (null != obj) {
                    this.hset(key, field, (Serializable) obj);
                    return obj;
                }
            } else {
                return SerializationUtils.deserialize(value);
            }
            return null;
        } catch (Exception ignored) {
            logger.error(ignored.getMessage());
            T obj = generator.generate();
            if (null != obj) {
                return obj;
            }
            return null;
        }
    }

    public void hdel(final String key, final String field) {
        try (Jedis jedis = pool.getResource()) {
            jedis.hdel(key.getBytes(), field.getBytes());
        } catch (Exception ignored) {
        }
    }

    public void destroy() {
        pool.destroy();
    }
}
