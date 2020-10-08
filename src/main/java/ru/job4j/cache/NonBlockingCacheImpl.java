package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCacheImpl implements NonBlockingCache<Base> {
    private final Map<Integer, Base> cache = new ConcurrentHashMap<>();

    @Override
    public void add(Base model) {
        cache.putIfAbsent(model.getId(), model);
    }

    @Override
    public void update(Base model) {
        cache.computeIfPresent(model.getId(), (id, base) -> {
            if (model.getVersion() == cache.get(id).getVersion()) {
                base.setName(model.getName());
                base.updateVersion();
                return base;
            } else {
                throw new OptimisticException("Version of given object not corresponds to current");
            }
        });
    }

    @Override
    public void delete(Base model) {
        cache.remove(model.getId());
    }
}
