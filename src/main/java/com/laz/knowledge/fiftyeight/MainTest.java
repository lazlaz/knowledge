package com.laz.knowledge.fiftyeight;

public class MainTest {
	 public static void main(String[] args) throws Exception {
//	        System.out.println("====== 测试计数器限流算法 ======");
//	        System.out.println();
//	        BaseLimitTest test1 = new BaseLimitTest(LimiterEnum.COUNT_LIMITER, 10);
//	        test1.test(false);
//	        System.out.println();
//	        System.out.println("====== 测试漏桶限流算法 ======");
//	        System.out.println();
//	        BaseLimitTest test2 = new BaseLimitTest(LimiterEnum.LEAKY_BUCKET_LIMITER, 10);
//	        test2.test(false);
	        System.out.println();
	        System.out.println("====== 测试令牌桶限流算法 ======");
	        System.out.println();
	        BaseLimitTest test3 = new BaseLimitTest(LimiterEnum.MYRATE_LIMITER, 10);
	        test3.test(false);
	    }
}
