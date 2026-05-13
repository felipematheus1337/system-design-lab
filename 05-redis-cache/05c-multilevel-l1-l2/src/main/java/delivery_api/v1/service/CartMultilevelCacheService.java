package delivery_api.v1.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class CartMultilevelCacheService {

    private static final String Object_KEY_PREFIX = "Objects:";
    private static final Duration REDIS_TTL = Duration.ofSeconds(120);

    private final Cache<String, Object> ObjectLocalCache;
    private final RedisTemplate<String, Object> ObjectRedisTemplate;
    private static final Logger log = LoggerFactory.getLogger(CartMultilevelCacheService.class);

    public CartMultilevelCacheService(Cache<String, Object> objectLocalCache, RedisTemplate<String, Object> objectRedisTemplate) {
        ObjectLocalCache = objectLocalCache;
        ObjectRedisTemplate = objectRedisTemplate;
    }

    public Optional<Object> findById(Long id) {
        String key = buildKey(id);

        Object fromL1 = ObjectLocalCache.getIfPresent(key);

        if (fromL1 != null) {
            log.info("L1 CACHE HIT - Caffeine - key={}", key);
            return Optional.of(fromL1);
        }

        log.info("L1 CACHE MISS - Caffeine - key={}", key);

        Object fromL2 = ObjectRedisTemplate.opsForValue().get(key);

        if (fromL2 != null) {
            log.info("L2 CACHE HIT - Redis - key={}", key);

            ObjectLocalCache.put(key, fromL2);
            log.info("L1 CACHE PUT - Caffeine warmed from Redis - key={}", key);

            return Optional.of(fromL2);
        }

        log.info("L2 CACHE MISS - Redis - key={}", key);

        return Optional.empty();
    }

    public void save(Long id, Object ObjectResponse) {
        String key = buildKey(id);

        ObjectRedisTemplate.opsForValue().set(key, ObjectResponse, REDIS_TTL);
        log.info("L2 CACHE PUT - Redis - key={}, ttl={}s", key, REDIS_TTL.toSeconds());

        ObjectLocalCache.put(key, ObjectResponse);
        log.info("L1 CACHE PUT - Caffeine - key={}", key);
    }

    public void evict(Long id) {
        String key = buildKey(id);

        ObjectLocalCache.invalidate(key);
        log.info("L1 CACHE EVICT - Caffeine - key={}", key);

        Boolean deletedFromRedis = ObjectRedisTemplate.delete(key);
        log.info("L2 CACHE EVICT - Redis - key={}, deleted={}", key, deletedFromRedis);
    }

    private String buildKey(Long id) {
        return Object_KEY_PREFIX + id;
    }
}
