package com.laz.knowledge.sixtytwo;

import java.lang.reflect.Field;
import sun.misc.Unsafe;


public class OffheapIntArray {
	  /**
     * 此list分配的地址
     */
    private long address;

    /**
     * 默认分配空间大小
     */
    private static final int defaultSize = 1024;

    /**
     * 带参构造
     * 由于Integer类型在java中占用4个字节，所以在分配地址的时候，一个integer，需要分配 4*8 = 32 bytes的空间
     * @param size
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public OffheapIntArray(Integer size) throws NoSuchFieldException, IllegalAccessException {
        if (size == null) {
            address = alloc(defaultSize * 4 * 8);
        } else {
            address = alloc(size * 4 * 8);
        }
    }

    public int get(int index) throws NoSuchFieldException, IllegalAccessException {
        return getUnsafe().getInt(address + index * 4 * 8);
    }

    public void set(int index, int value) throws NoSuchFieldException, IllegalAccessException {
        getUnsafe().putInt(address + index * 4 * 8, value);
    }

    private Unsafe getUnsafe() throws IllegalAccessException, NoSuchFieldException {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        return (Unsafe) f.get(null);
    }

    private long alloc(int size) throws NoSuchFieldException, IllegalAccessException {
        long address = getUnsafe().allocateMemory(size);
        return address;
    }

    public void free() throws NoSuchFieldException, IllegalAccessException {
        if (address == 0) {
            return;
        }
        getUnsafe().freeMemory(address);
    }
}
