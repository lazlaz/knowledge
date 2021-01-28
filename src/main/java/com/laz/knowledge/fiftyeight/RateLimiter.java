package com.laz.knowledge.fiftyeight;

//令牌桶
public class RateLimiter extends Limiter {

    private final int capacity;                             // 桶内能装多少令牌
    private double curTokenNum;                             // 现在桶内令牌数量(用double存)
    private long lastTime;                                  // 时间戳

    RateLimiter(int qps) {
        super(qps);
        capacity = qps;
        curTokenNum = 0;
        lastTime = 0;
    }

    @Override
    public synchronized boolean tryAcquire() {
        long now = System.currentTimeMillis();
        double intoToken = (now - lastTime)/1000.0 * capacity;
        lastTime = now;
        if (intoToken + curTokenNum > capacity) {
            // 令牌已放满
            curTokenNum = capacity - 1;
            return true;
        } else if (intoToken + curTokenNum >= 1) {
            // 还有令牌
            curTokenNum += intoToken - 1;
            return true;
        } else {
            curTokenNum += intoToken;
            return false;
        }
    }

}
