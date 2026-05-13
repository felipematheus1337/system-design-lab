package delivery_api.v1.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Service
public class CacheService {

    private static final String CART_KEY = "carts";
    private static final Duration CART_TTL = Duration.ofSeconds(60);
    private static final Logger log = LoggerFactory.getLogger(CacheService.class);

    private final RedisTemplate<String, Object> redisTemplate;

    public CacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Optional<Object> findById(Long id) {
        String key = buildKey(id);

        Object cachedObject = redisTemplate.opsForValue().get(key);

        if (Objects.isNull(cachedObject)) {
            log.info("REDIS CACHE MISS - key {}", key);
            return Optional.empty();
        }

        log.info("REDIS CACHE HIT - key {}", key);
        return Optional.of(cachedObject);
    }

    public void save(Long id, Object obj) {
        String key = buildKey(id);

        redisTemplate.opsForValue().set(key, obj, CART_TTL);

        log.info("REDIS CACHE PUT - key={}, ttl={}s", key, CART_TTL.toSeconds());
    }

    public void evict(Long id) {
        String key = buildKey(id);

        Boolean deleted = redisTemplate.delete(key);

        log.info("REDIS CACHE EVICT - key={}, deleted={}", key, deleted);
    }


    private String buildKey(Long id) {
        return CART_KEY + id;
    }
}
