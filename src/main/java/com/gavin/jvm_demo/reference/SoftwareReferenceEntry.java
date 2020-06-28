package com.gavin.jvm_demo.reference;

import java.lang.ref.SoftReference;
import java.util.concurrent.TimeUnit;

/**
 * SoftReference 作用：内存空间足够，垃圾回收器就不会回收它，不足时回收 (可应用在缓存方面)
 * SoftReference 案例方案：
 * 1. 申请 10M 对象
 * 2. 调用GC
 * 3. 再申请 11M 对象
 * 4. 设置 VM Option = -Xms20M -Xmx20M  => 指定 jvm 内存最小和最大值
 *
 * 遗留问题：
 * 1. 有时正常，随机性会报 OOM 的 Exception (Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
 * 	at com.gavin.jvm_demo.reference.SoftwareReferenceEntry.main(SoftwareReferenceEntry.java:28)
 *
 */
public class SoftwareReferenceEntry {
    public static void main(String[] args) {
        SoftReference<byte[]> soft = new SoftReference<>(new byte[1024 * 1024 * 10]); //    create 10M object
        System.out.println(soft.get());

        System.gc();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(soft.get());
        byte[] btyeStore = new byte[1024 * 1024 * 11]; //    create 11M object
        System.out.println(soft.get());
    }
}
