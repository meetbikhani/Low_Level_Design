
import java.util.concurrent.*;

class CacheEntry {
    String value;
    long expiryTime;

    CacheEntry(String value, long ttlMillis) {
        this.value = value;
        this.expiryTime = System.currentTimeMillis() + ttlMillis;
    }

    boolean isExpired() {
        return System.currentTimeMillis() >= expiryTime;
    }
}

class ThreadSafeCache {

    private final ConcurrentHashMap<String, CacheEntry> cache =
            new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public ThreadSafeCache() {

        scheduler.scheduleAtFixedRate(
            this::cleanup,
            1,
            1,
            TimeUnit.SECONDS
        );
    }

    public void put(String key, String value, long ttlMillis) {
        cache.put(key, new CacheEntry(value, ttlMillis));
        System.out.println("Put: " + key + " = " + value);
    }

    public String get(String key) {
        CacheEntry entry = cache.get(key);

        if (entry == null) {
            return null;
        }

        if (entry.isExpired()) {
            cache.remove(key, entry);
            return null;
        }

        return entry.value;
    }

    private void cleanup() {
        for (String key : cache.keySet()) {

            CacheEntry entry = cache.get(key);

            if (entry != null && entry.isExpired()) {
                cache.remove(key, entry);
                System.out.println("Removed expired key: " + key);
            }
        }
    }

    public void shutdown() {
        scheduler.shutdown();
    }
}

public class Main {

    public static void main(String[] args) {

        ThreadSafeCache cache = new ThreadSafeCache();

        Thread workerThread = new Thread(() -> {

            cache.put("user1", "Meet", 3000);
            cache.put("user2", "Rahul", 6000);

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("user1: " + cache.get("user1"));
                    System.out.println("user2: " + cache.get("user2"));

                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        workerThread.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            workerThread.interrupt();
            cache.shutdown();
        }
    }
}
