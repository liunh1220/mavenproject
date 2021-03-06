package com.example.memcached.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import com.example.memcached.constant.SpyMemcachedConstants;

import net.spy.memcached.ConnectionObserver;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.transcoders.Transcoder;

/**
 * Memcached Utils
 * 
 * @author Administrator
 *
 */
public class MemcachedUtils {
	
	private MemcachedClient memcachedClient;

    public void addObserver(ConnectionObserver obs) {
        memcachedClient.addObserver(obs);
    }

    public void removeObserver(ConnectionObserver obs) {
        memcachedClient.removeObserver(obs);
    }

    // ---- Basic Operation Start ----//
    public boolean set(String key, Object value, int expire) {
        Future<Boolean> f = memcachedClient.set(key, expire, value);
        return getBooleanValue(f);
    }

    public Object get(String key) {
        return memcachedClient.get(key);
    }

    public Object asyncGet(String key) {
        Object obj = null;
        Future<Object> f = memcachedClient.asyncGet(key);
        try {
            obj = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT, SpyMemcachedConstants.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return obj;
    }

    public boolean add(String key, Object value, int expire) {
        Future<Boolean> f = memcachedClient.add(key, expire, value);
        return getBooleanValue(f);
    }

    public boolean replace(String key, Object value, int expire) {
        Future<Boolean> f = memcachedClient.replace(key, expire, value);
        return getBooleanValue(f);
    }

    public boolean delete(String key) {
        Future<Boolean> f = memcachedClient.delete(key);
        return getBooleanValue(f);
    }

    public boolean flush() {
        Future<Boolean> f = memcachedClient.flush();
        return getBooleanValue(f);
    }

    public Map<String, Object> getMulti(Collection<String> keys) {
        return memcachedClient.getBulk(keys);
    }

    public Map<String, Object> getMulti(String[] keys) {
        return memcachedClient.getBulk(keys);
    }

    public Map<String, Object> asyncGetMulti(Collection<String> keys) {
        Map map = null;
        Future<Map<String, Object>> f = memcachedClient.asyncGetBulk(keys);
        try {
            map = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT, SpyMemcachedConstants.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }

    public Map<String, Object> asyncGetMulti(String keys[]) {
        Map map = null;
        Future<Map<String, Object>> f = memcachedClient.asyncGetBulk(keys);
        try {
            map = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT, SpyMemcachedConstants.DEFAULT_TIMEUNIT);
        } catch (Exception e) {
            f.cancel(false);
        }
        return map;
    }

    // ---- Basic Operation End ----//

    // ---- increment & decrement Start ----//
    public long increment(String key, int by, long defaultValue, int expire) {
        return memcachedClient.incr(key, by, defaultValue, expire);
    }

    public long increment(String key, int by) {
        return memcachedClient.incr(key, by);
    }

    public long decrement(String key, int by, long defaultValue, int expire) {
        return memcachedClient.decr(key, by, defaultValue, expire);
    }

    public long decrement(String key, int by) {
        return memcachedClient.decr(key, by);
    }

    public long asyncIncrement(String key, int by) {
        Future<Long> f = memcachedClient.asyncIncr(key, by);
        return getLongValue(f);
    }

    public long asyncDecrement(String key, int by) {
        Future<Long> f = memcachedClient.asyncDecr(key, by);
        return getLongValue(f);
    }

    // ---- increment & decrement End ----//

    public void printStats() throws IOException {
        printStats(null);
    }

    public void printStats(OutputStream stream) throws IOException {
        Map<SocketAddress, Map<String, String>> statMap = memcachedClient
                .getStats();
        if (stream == null) {
            stream = System.out;
        }
        StringBuffer buf = new StringBuffer();
        Set<SocketAddress> addrSet = statMap.keySet();
        Iterator<SocketAddress> iter = addrSet.iterator();
        while (iter.hasNext()) {
            SocketAddress addr = iter.next();
            buf.append(addr.toString() + "/n");
            Map<String, String> stat = statMap.get(addr);
            Set<String> keys = stat.keySet();
            Iterator<String> keyIter = keys.iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                String value = stat.get(key);
                buf.append("  key=" + key + ";value=" + value + "/n");
            }
            buf.append("/n");
        }
        stream.write(buf.toString().getBytes());
        stream.flush();
    }

    public Transcoder getTranscoder() {
        return memcachedClient.getTranscoder();
    }

    private long getLongValue(Future<Long> f) {
        try {
            Long l = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT, SpyMemcachedConstants.DEFAULT_TIMEUNIT);
            return l.longValue();
        } catch (Exception e) {
            f.cancel(false);
        }
        return -1;
    }

    private boolean getBooleanValue(Future<Boolean> f) {
        try {
            Boolean bool = f.get(SpyMemcachedConstants.DEFAULT_TIMEOUT, SpyMemcachedConstants.DEFAULT_TIMEUNIT);
            return bool.booleanValue();
        } catch (Exception e) {
            f.cancel(false);
            return false;
        }
    }

    public MemcachedClient getMemcachedClient() {
        return memcachedClient;
    }

    public void setMemcachedClient(MemcachedClient memcachedClient) {
        this.memcachedClient = memcachedClient;
    }
    
}
