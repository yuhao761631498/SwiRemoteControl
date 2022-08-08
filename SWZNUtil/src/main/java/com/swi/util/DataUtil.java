package com.swi.util;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by xx on 2017/12/22.
 */

public class DataUtil {
    /**
     * <p>List 转化为 数组</p>
     *
     * @param list  in
     * @param bytes out
     */
    public static void bytes2List(List<Byte> list, byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            list.add(bytes[i]);
        }
    }

    /**
     * <p>byte数组转化为int</p>
     *
     * @param source byte数组 数组低字节在前
     * @param begin
     * @return
     */
    public static int byte2Int(byte[] source, int begin) {
        int iOutcome = 0;
        iOutcome |= (source[begin++] & 0xFF);
        iOutcome |= ((source[begin++] & 0xFF) << 8);
        iOutcome |= ((source[begin++] & 0xFF) << 16);
        iOutcome |= ((source[begin++] & 0xFF) << 24);
        return iOutcome;
    }

    /**
     * <p>byte数组转化为int</p>
     *
     * @param source byte数组 数组低字节在前
     * @param begin
     * @return
     */
    public static int byte2IntLowBefore(byte[] source, int begin) {
        return byte2Int(source, begin);
    }


    /**
     * <p>byte数组转化为int</p>
     *
     * @param source byte数组 数组高字节在前
     * @param begin
     * @return
     */
    public static int byte2IntHighBefore(byte[] source, int begin) {
        int iOutcome = 0;
        iOutcome |= ((source[begin++] & 0xFF) << 24);
        iOutcome |= ((source[begin++] & 0xFF) << 16);
        iOutcome |= ((source[begin++] & 0xFF) << 8);
        iOutcome |= (source[begin++] & 0xFF);
        return iOutcome;
    }

    /**
     * 2个byte转int
     *
     * @param data
     * @return
     */
    public static int twoByteToInt(byte[] data) {
        return twoByteToInt(data[0], data[1]);
    }

    /**
     * <P>byte数组转化为byte----xx</P>
     *
     * @param b
     * @param index 低字节在前
     * @return
     */
    public static float byte2float(byte[] b, int index) {
        int l;
        l = b[index + 0];
        l &= 0xff;
        l |= ((long) b[index + 1] << 8);
        l &= 0xffff;
        l |= ((long) b[index + 2] << 16);
        l &= 0xffffff;
        l |= ((long) b[index + 3] << 24);
        return Float.intBitsToFloat(l);
    }

    /**
     * 2个byte转int
     *
     * @param
     * @return
     */
    public static int twoByteToInt(byte data0, byte data1) {
        int var = data1 << 8 | byte2UnsignedByte(data0);
        if (var < 0) {
            var += 65536;
        }
        return var;
    }

    /**
     * 获取无符号byte
     *
     * @param var0
     * @return
     */
    public static int byte2UnsignedByte(byte var0) {
        int var1 = var0;
        if (var0 < 0) {
            var1 = var0 + 256;
        }
        return var1;
    }

    /**
     * <p>byte数组转化为long</p>
     *
     * @param source byte数组 数组低字节在前
     * @return
     */
    public static long byte2long(byte[] source, int begin) {
        long iOutcome = 0;
        iOutcome |= (source[begin++] & 0xFF);
        iOutcome |= ((source[begin++] & 0xFF) << 8);
        iOutcome |= ((source[begin++] & 0xFF) << 16);
        iOutcome |= ((source[begin++] & 0xFF) << 24);
        iOutcome |= ((source[begin++] & 0xFF) << 32);
        iOutcome |= ((source[begin++] & 0xFF) << 40);
        iOutcome |= ((source[begin++] & 0xFF) << 48);
        iOutcome |= ((source[begin++] & 0xFF) << 56);
        return iOutcome;
    }


    /**
     * byte数组转化为long   余浩
     *
     * @param b
     * @return
     */
    public static long byteToLong(byte[] b, int begin) {
        long s = 0;
        long s0 = b[begin++] & 0xff;// 最低位
        long s1 = b[begin++] & 0xff;
        long s2 = b[begin++] & 0xff;
        long s3 = b[begin++] & 0xff;
        long s4 = b[begin++] & 0xff;// 最低位
        long s5 = b[begin++] & 0xff;
        long s6 = b[begin++] & 0xff;
        long s7 = b[begin++] & 0xff;

        // s0不变
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }

    /**
     * <p>int转化为byte</p>
     *
     * @param source
     * @return 数组低字节在前
     */
    public static byte[] int2byte(int source) {
        byte[] bLocalArr = new byte[4];
        long lSource = source & 0xFFFFFFFFL;
        int i = 0;
        bLocalArr[i++] = (byte) ((lSource >> 0) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 8) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 16) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 24) & 0xFF);
        return bLocalArr;
    }

    /**
     * <p>long转化为byte</p>
     *
     * @param source
     * @return 数组低字节在前
     */
    public static byte[] long2byte(long source) {
        byte[] bLocalArr = new byte[8];
        long lSource = source & 0xFFFFFFFFL;
        int i = 0;
        bLocalArr[i++] = (byte) ((lSource >> 0) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 8) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 16) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 24) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 32) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 40) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 48) & 0xFF);
        bLocalArr[i++] = (byte) ((lSource >> 56) & 0xFF);
        return bLocalArr;
    }

    /**
     * <p>short转化为字节数组</p>
     *
     * @param source
     * @return 数组 低字节在前
     */
    public static byte[] short2byte(short source) {
        int iSource = source & 0xFFFF;
        byte[] bb = new byte[2];
        bb[0] = (byte) ((iSource >> 0) & 0xFF);
        bb[1] = (byte) ((iSource >> 8) & 0xFF);
        return bb;
    }

    /**
     * <p>byte数组 转化为 short</p>
     *
     * @param bb    数组低字节在前
     * @param index
     * @return
     */
    public static short byte2short(byte[] bb, int index) {
        short sOutcome = 0;
        sOutcome |= ((bb[index++] & 0xFF) << 0);
        sOutcome |= ((bb[index++] & 0xFF) << 8);

        return sOutcome;
    }


    /**
     * <p>byte数组 转化为 short</p>
     *
     * @param bb    数组低字节在前
     * @param index
     * @return
     */
    public static short byte2shortLowBefore(byte[] bb, int index) {
        return byte2short(bb, index);
    }

    /**
     * <p>byte数组 转化为 short</p>
     *
     * @param bb    数组低字节在前
     * @param index
     * @return
     */
    public static short byte2shortHighBefore(byte[] bb, int index) {
        short sOutcome = 0;
        sOutcome |= ((bb[index++] & 0xFF) << 8);
        sOutcome |= ((bb[index++] & 0xFF) << 0);

        return sOutcome;
    }

    /**
     * <h3>把String转化为byte类型</h3>
     * <ul>
     * <li>由于str.getBytes 不包含结尾符号0</li>
     * </ul>
     *
     * @param str
     * @return
     */
    public static byte[] str2Byte(String str) {
        byte[] bb = str.getBytes();
        byte[] ids = new byte[bb.length + 1];
        System.arraycopy(bb, 0, ids, 0, bb.length);
        return ids;
    }

    public static char byte2Char(byte b) {
        char c = (char) b;
        return c;
    }

    /**
     * 将byte数组转为16进制数
     *
     * @param b
     * @return
     */
    public static String bytes2Hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 将byte转为16进制数
     *
     * @param b
     * @return
     */
    public static String byte2Hex(byte b) {
        byte[] bytes = new byte[1];
        bytes[0] = b;
        return bytes2Hex(bytes);
    }

    /**
     * <P>shang</P>
     * <P>安全无公害 数据类型格式化</P>
     *
     * @param values
     * @return
     */
    public static float BigDecimal(double values) {
        BigDecimal b = new BigDecimal(values);
        float onedecimals = b.setScale(1, BigDecimal.ROUND_HALF_UP).floatValue();
        return onedecimals;
    }

    public static float BigDecimal(double values, int i) {
        BigDecimal b = new BigDecimal(values);
        float onedecimals = b.setScale(i, BigDecimal.ROUND_HALF_UP).floatValue();
        return onedecimals;
    }

    /**
     * @author xx
     * 取出byte的每一位
     */
    public static byte[] getBit(byte byData) {
        byte[] ints = new byte[8];
        byte one = (byte) 0x01;
        byte zero = (byte) 0x00;
        ints[0] = (byData & 0x01) == 0x01 ? one : zero;
        ints[1] = (byData & 0x02) == 0x02 ? one : zero;
        ints[2] = (byData & 0x04) == 0x04 ? one : zero;
        ints[3] = (byData & 0x08) == 0x08 ? one : zero;
        ints[4] = (byData & 0x10) == 0x10 ? one : zero;
        ints[5] = (byData & 0x20) == 0x20 ? one : zero;
        ints[6] = (byData & 0x40) == 0x40 ? one : zero;
        ints[7] = (byData & 0x80) == 0x80 ? one : zero;
        return ints;
    }

    /**
     * int 类型转为32位 bit
     * @param source
     * @return
     */
    public static byte[] int2Bit(int source) {
        byte[] bits = new byte[32];
        byte one = 1;
        byte zero = 0;
        byte[] bytes = int2byte(source);
        for (int i = 0; i < bytes.length; i++) {
            bits[i * 8 + 0] = (bytes[i] & 1) == 1 ? one : zero;
            bits[i * 8 + 1] = (bytes[i] & 2) == 2 ? one : zero;
            bits[i * 8 + 2] = (bytes[i] & 4) == 4 ? one : zero;
            bits[i * 8 + 3] = (bytes[i] & 8) == 8 ? one : zero;
            bits[i * 8 + 4] = (bytes[i] & 16) == 16 ? one : zero;
            bits[i * 8 + 5] = (bytes[i] & 32) == 32 ? one : zero;
            bits[i * 8 + 6] = (bytes[i] & 64) == 64 ? one : zero;
            bits[i * 8 + 7] = (bytes[i] & 128) == 128 ? one : zero;
        }
        return bits;
    }
}
