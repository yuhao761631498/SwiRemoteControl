package com.swzn.util;

import java.util.List;

/**
 * <ul>
 * 	 <li>byte与 int,short之间的转化的工具类</li>
 *   <li>数组排序 都是 低位在前</li>
 * </ul>
 */
public class ByteUtilsLowBefore {



	/**
	 * <p>List 转化为 数组</p>
	 * @param list  in
	 * @param bytes out
	 */
	public static void bytes2List(List<Byte> list, byte[] bytes )
	{
		for (int i = 0; i < bytes.length; i++) {
			list.add(bytes[i]);
		}
	}

	/**
	 * <p>byte数组转化为int</p>
	 * @param source byte数组 数组低字节在前
	 * @param  begin
	 * @return
	 */
	public static int byte2Int(byte[] source,int begin) {
	    int iOutcome = 0;
		iOutcome |= (source[begin++] & 0xFF);
		iOutcome |= ( (source[begin++] & 0xFF) << 8);
		iOutcome |= ( (source[begin++] & 0xFF) << 16);
		iOutcome |= ( (source[begin++] & 0xFF) << 24);
	    return iOutcome;
	}

	/**
	 * <p>byte数组转化为long</p>
	 * @param source byte数组 数组低字节在前
	 * @return
	 */
	public static long byte2long(byte[] source,int begin) {
	    long iOutcome = 0;
		iOutcome |= (source[begin++] & 0xFF);
		iOutcome |= ( (source[begin++] & 0xFF) << 8);
		iOutcome |= ( (source[begin++] & 0xFF) << 16);
		iOutcome |= ( (source[begin++] & 0xFF) << 24);
		iOutcome |= ( (source[begin++] & 0xFF) << 32);
		iOutcome |= ( (source[begin++] & 0xFF) << 40);
		iOutcome |= ( (source[begin++] & 0xFF) << 48);
		iOutcome |= ( (source[begin++] & 0xFF) << 56);
	    return iOutcome;
	}


	/**
	 * byte数组转化为long   余浩
	 * @param b
	 * @return
	 */
	public  static long byteToLong(byte[] b,int begin) {
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
	 * @param source
	 * @return  数组低字节在前
	 */
	public static byte[] int2byte(int source)
	{
		byte[] bLocalArr = new byte[4];
		long lSource = source & 0xFFFFFFFFL;
	    int i = 0;
		bLocalArr[i++] = (byte)((lSource >> 0) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 8) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 16) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 24) & 0xFF);
	    return bLocalArr;
	}

	/**
	 * <p>long转化为byte</p>
	 * @param source
	 * @return  数组低字节在前
	 */
	public static byte[] long2byte(long source)
	{
		byte[] bLocalArr = new byte[8];
		long lSource = source;
	    int i = 0;
		bLocalArr[i++] = (byte)((lSource >> 0) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 8) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 16) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 24) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 32) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 40) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 48) & 0xFF);
		bLocalArr[i++] = (byte)((lSource >> 56) & 0xFF);
	    return bLocalArr;
	}

	/**
	 * <p>short转化为字节数组</p>
	 * @param source
	 * @return 数组 低字节在前
	 */
	public static byte[] short2byte(short source)
	{
		int iSource = source & 0xFFFF;
		byte[] bb = new byte[2];
		bb[0] =(byte)((iSource >> 0) & 0xFF);
		bb[1] = (byte)((iSource >> 8) & 0xFF);
		return bb;
	}

	/**
	 * <p>byte数组 转化为 short</p>
	 * @param bb  数组低字节在前
	 * @param index
	 * @return
	 */
	public static short byte2short(byte[] bb,int index)
	{
		short sOutcome = 0;
		sOutcome |= ( (bb[index++] & 0xFF) << 0);
		sOutcome |= ( (bb[index++] & 0xFF) << 8);

		return sOutcome;
	}

	/**
	 * <h3>把String转化为byte类型</h3>
	 * <ul>
	 *   <li>由于str.getBytes 不包含结尾符号0</li>
	 * </ul>
	 * @param str
	 * @return
	 */
	public static byte[] Str2Byte(String str)
	{
		byte[] bb = str.getBytes();
		byte [] ids = new byte[bb.length + 1];
		System.arraycopy(bb, 0, ids, 0, bb.length);
		return ids;
	}

	public static char byte2Char(byte b){
		char c = (char) (((b & 0xFF) << 8));
		return c;
	}
}
