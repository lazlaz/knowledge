package com.laz.knowledge.sixtytwo;

import org.junit.Test;

public class TestMain {
	@Test
    public void testOffheap() throws NoSuchFieldException, IllegalAccessException {
        OffheapIntArray offheapArray = new OffheapIntArray(10);
        offheapArray.set(0,11111);
        offheapArray.set(1,1112);
        offheapArray.set(2,1113);
        offheapArray.set(3,1114);
        System.out.println(offheapArray.get(0));
        System.out.println(offheapArray.get(1));
        System.out.println(offheapArray.get(2));
        System.out.println(offheapArray.get(3));
        offheapArray.free();
    }
}
