package com.cl.code.util;

/**
 * 生成码工具类
 *
 * @author raojing
 * @date 2020/4/8 17:13
 */
public class FlowIdUtils {

    private static int node = 1;
    public static final int NODE_SHIFT = 5;
    public static final int SEQ_SHIFT = 17;

    public static final int MAX_SEQUENCE = 131071;

    private static volatile int sequence;
    private static volatile long referenceTime;


    private static synchronized long next() {

        long currentTime = System.currentTimeMillis();
        long counter;

        synchronized (FlowIdUtils.class) {

            if (currentTime < referenceTime) {
                throw new RuntimeException(String.format("Last referenceTime %s is after reference time %s", referenceTime, currentTime));
            } else if (currentTime > referenceTime) {
                sequence = 0;
            } else {
                if (sequence < MAX_SEQUENCE) {
                    sequence++;
                } else {
                    throw new RuntimeException("Sequence exhausted at " + sequence);
                }
            }
            counter = sequence;
            referenceTime = currentTime;
        }

        return currentTime << NODE_SHIFT << SEQ_SHIFT | node << SEQ_SHIFT | counter;
    }

    /**
     * 生成分布式id
     * @return
     */
    public static Long getTwiterId() {

        try {
            return next();
        }catch (Exception e){
            try {
                Thread.sleep(1);
                return next();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                return next();
            }
        }
    }


    public static void main(String[] args) {
        int time = 5000000;
        for (int i = 0; i < time; i++) {
            Long twiterId = getTwiterId();
        }

    }


    /**
     * 随机生成指定数
     * @param len
     * @return
     */
    public static String getRandomCode(Integer len) {
        //字符源，可以根据需要删减
        //去掉1和i ，0和o
        String generateSource = "23456789abcdefghgklmnpqrstuvwxyz";
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            //循环随机获得当次字符，并移走选出的字符
            String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr.toUpperCase();
    }

}
