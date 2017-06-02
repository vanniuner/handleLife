package com.gpagers.cn.handle.util;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlobalCache {

    static Logger logger = LoggerFactory.getLogger(GlobalCache.class);

    private static volatile CacheManager CACHE_MANAGER=CacheManager.create();


    private static Cache tryGetCache(String cacheName){
        Cache cache = CACHE_MANAGER.getCache(cacheName);
        synchronized (CACHE_MANAGER) {
            if(cache==null){
                cache = new Cache(cacheName, 100000, false, false, 7200 , 7200);
                CACHE_MANAGER.addCache(cache);
            }
        }
        return cache;
    }

    public static void removalAll(){
        CACHE_MANAGER.removalAll();
    }

    /**
     * 添加缓存
     * @param cacheName 缓存名称，相当于group的概念，填写业务分类名称
     * @param key 缓存键
     * @param value 值
     */
    public static void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        tryGetCache(cacheName).put(element);
    }
  
    /**
     * 获取缓存
     * @param cacheName 缓存名称，相当于group的概念，填写业务分类名称
     * @param key 缓存键
     */
    public static Object get(String cacheName, String key) {
        Element element = tryGetCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    static String LoginDataCN = "default";
    public static void putLoginData(String key, String value){
        put(LoginDataCN,key,value);
    }

    public static String getLoginData(String key){
        return (String)get(LoginDataCN,key);
    }


}