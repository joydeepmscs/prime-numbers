package com.rbs.assignment.config;

import net.sf.ehcache.Cache;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.MemoryUnit;
import net.sf.ehcache.config.PersistenceConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Joydeep Paul
 * Created on 1/27/2021
 */
@Configuration
@EnableCaching
public class EHCacheConfig {

    @Value("${prime.cache.maxentries}")
    private int maxEntries;

    @Value("${prime.cache.time.to.live.sec}")
    private int timeToLiveSec;

    @Value("${prime.cache.time.to.idle.sec}")
    private int timeToIdleSec;

    @Value("${prime.cache.persistence.strategy}")
    private String persistenceStrategy;

    @Value("${prime.cache.eviction.policy}")
    private String evictionPolicy;

    @Value("${prime.cache.name}")
    private String cacheName;


    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }

    @Bean
    public EhCacheCacheManager primeNumberCacheManager() {
        // testEhCache Configuration - create configuration of cache that previous required XML
        CacheConfiguration primeNumberEhCacheConfig = new CacheConfiguration()
                .eternal(false)                     // if true, timeouts are ignored
                .timeToIdleSeconds(timeToIdleSec)               // time since last accessed before item is marked for removal
                .timeToLiveSeconds(timeToLiveSec)               // time since inserted before item is marked for removal
                .maxEntriesLocalHeap(maxEntries)            // total items that can be stored in cache
                .maxEntriesLocalDisk(maxEntries)
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.fromString(evictionPolicy))   // eviction policy for when items exceed cache. LRU = Least Recently Used
                .transactionalMode("OFF")
                .persistence(new PersistenceConfiguration().strategy(PersistenceConfiguration.Strategy.valueOf(persistenceStrategy.toUpperCase())))
                .name(cacheName);

        if(ehCacheManagerFactoryBean().getObject().getCache(cacheName)==null) {
            Cache primeNumberCache = new Cache(primeNumberEhCacheConfig);
            ehCacheManagerFactoryBean().getObject().addCache(primeNumberCache);
        }

        return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
    }
}
