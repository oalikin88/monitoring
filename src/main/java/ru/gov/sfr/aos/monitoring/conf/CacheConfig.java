/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ru.gov.sfr.aos.monitoring.conf;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

//import com.google.common.cache.CacheBuilder;

/**
 *
 * @author 041AlikinOS
 */
@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    private Map<String, TimeUnit> cacheTTLUnits;
    private Map<String, Long> cacheTTLDuration;

    public CacheConfig() {
        cacheTTLUnits = new HashMap<>();
        cacheTTLDuration = new HashMap<>();
        cacheTTLUnits.put("dictonaryEmployee_employees", TimeUnit.MINUTES);
        cacheTTLDuration.put("dictonaryEmployee_employees", 10l);
        cacheTTLUnits.put("organization", TimeUnit.MINUTES);
        cacheTTLDuration.put("organization", 10l);
 
    }

/*    @Override
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(name, CacheBuilder.newBuilder().expireAfterWrite(cacheTTLDuration.get(name), cacheTTLUnits.get(name))
                        .maximumSize(100).build().asMap(), true);
            }
        };*/
    
    
//}
}
