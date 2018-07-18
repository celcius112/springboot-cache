package com.springboot.cache;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Test class used to demonstrate that with the cache bean 'my-cache' in {@link CacheApplication#myCache()}
 * the cache defined in the property file is not configured.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("configure-my-cache")
public class WithCacheBean {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private CacheManager cacheManager;

    @Test
    public void should_retrieve_cache_managers() {
        // there's only one cache manager in my application context
        assertEquals(applicationContext.getBeansOfType(CacheManager.class).size(), 1);

        // only one cache was configured (the bean that I configured myself), while myOtherCache was not
        assertEquals(cacheManager.getCacheNames(), Collections.singleton("my-cache"));
    }
}
