package com.swzn.util;

import java.util.List;

/**
 * <ul>
 * 	 <li>byte与 int,short之间的转化的工具类</li>
 *   <li>数组排序 都是 高位在前</li>
 * </ul> 
 */
public class ByteUtils {

	/**
	 * <p>List 转化为 数组</p>
	 * @param list  in
	 * @param bytes out
	 */
	public static void bytes2List(List <Byte>list,byte[] bytes )
	{
		for (int i = 0; i < bytes.length; i++) {
			list.add(bytes[i]);
		}
	}
	
	/**
	 * <p>byte数组转化为int</p>
	 * @param source byte数组
	 * @param  begin
	 * @return
	 */
	public static int byte2Int(byte[] source,int begin) {
	    int iOutcome = 0;
	    iOutcome |= ( (source[begin++] & 0xFF) << 24);
	    iOutcome |= ( (source[begin++] & 0xFF) << 16);
	    iOutcome |= ( (source[begin++] & 0xFF) << 8);
	    iOutcome |= (source[begin++] & 0xFF);
	    return iOutcome;
	}
	
	/**
	 * <p>byte数组转化为long</p>
	 * @param source byte数组
	 * @return
	 */
	public static long byte2long(byte[] source,int begin) {
		long iOutcome = 0;
	    iOutcome |= ( (source[begin++] & 0xFF) << 56);
		iOutcome |= ( (source[begin++] & 0xFF) << 48);
		iOutcome |= ( (source[begin++] & 0xFF) << 40);
		iOutcome |= ( (source[begin++] & 0xFF) << 32);
		iOutcome |= ( (source[begin++] & 0xFF) << 24);
		iOutcome |= ( (source[begin++] & 0xFF) << 16);
		iOutcome |= ( (source[begin++] & 0xFF) << 8);
		iOutcome |= (source[begin++] & 0xFF);
	    return iOutcome;
	}
	
	/**
	 * <p>int转化为byte</p>
	 * @param source
	 * @return
	 */
	public static byte[] int2byte(int source)
	{
		byte[] bLocalArr = new byte[4];
		long lSource = source & 0xFFFFFFFFL;
	    int i = 0;
	    bLocalArr[i++] = (byte)((lSource >> 24) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 16) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 8) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 0) & 0xFF);
	    return bLocalArr;
	}
	
	/**
	 * <p>int转化为byte</p>
	 * @param source
	 * @return
	 */
	public static byte[] long2byte(long source)
	{
		byte[] bLocalArr = new byte[8];
		long lSource = source & 0xFFFFFFFFL;
	    int i = 0;
	    bLocalArr[i++] = (byte)((lSource >> 56) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 48) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 40) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 32) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 24) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 16) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 8) & 0xFF);
	    bLocalArr[i++] = (byte)((lSource >> 0) & 0xFF);
	    return bLocalArr;
	}
	
	/**
	 * <p>short转化为字节数组</p>
	 * @param source
	 * @return
	 */
	public static byte[] short2byte(short source)
	{
		int iSource = source & 0xFFFF;
		byte[] bb = new byte[2];
		bb[0] = (byte)((iSource >> 8) & 0xFF);
		bb[1] =(byte)((iSource >> 0) & 0xFF);
		return bb;
	} 
	
	/**
	 * <p>byte数组 转化为 short</p>
	 * @param bb
	 * @param index
	 * @return
	 */
	public static short byte2short(byte[] bb,int index)
	{
		short sOutcome = 0;
		sOutcome |= ( (bb[index++] & 0xFF) << 8);
		sOutcome |= ( (bb[index++] & 0xFF) << 0);
		
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

	/**
	 * @author xx
	 *  能用byte的 就不要用int,节约内存，从小事做起 ---xx
	 * 取出byte的每一位
	*/
	public static byte[] getBit(byte byData){
		byte[] ints = new byte[8];
		byte one = (byte)0x01;
		byte zero = (byte)0x00;
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


	public static byte[] int2Bit(int source)
	{
		byte[] bits =new byte[32];
		byte one =1;
		byte zero = 0;
		byte [] bytes = ByteUtilsLowBefore.int2byte(source);
		for (int i = 0 ; i< bytes.length; i ++)
		{
			bits[i*8+0] = (bytes[i]&1)==1?one:zero;
			bits[i*8+1] = (bytes[i]&2)==2?one:zero;
			bits[i*8+2] = (bytes[i]&4)==4?one:zero;
			bits[i*8+3] = (bytes[i]&8)==8?one:zero;
			bits[i*8+4] = (bytes[i]&16)==16?one:zero;
			bits[i*8+5] = (bytes[i]&32)==32?one:zero;
			bits[i*8+6] = (bytes[i]&64)==64?one:zero;
			bits[i*8+7] = (bytes[i]&128)==128?one:zero;
		}
		return bits;
	}

	/**
	 * <P>byte数组转化为byte----xx</P>
	 * @param b
	 * @param index  低字节在前
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

	/*********************8
	 * float转化为byte数组----xx
	 * @param source
	 * @return
     */
	public static byte[] float2byte(float source)
	{
		int n = Float.floatToIntBits(source);
		 return ByteUtilsLowBefore.int2byte(n);
	}

	public static String BytesToHexString(byte[] b, int len) {
		String ret = "";
		for (int i = 0; i < len; i++) {
			String hex = Integer.toHexString(b[i] & 0x0FF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}

			ret +=  hex.toUpperCase() + " ";

		}
		return ret;
	}


	/**
	 * 从byte中获取bit
	 * @param b
	 * @param start
	 * @param length
	 * @return
	 */
	public static int getBits(byte b, int start, int length) {
		int bit = (int) ((b >> start) & (0xFF >> (8 - length)));
		return bit;
	}

	/**
	 * 从short中获取bit的内容
	 * @param b
	 * @param start
	 * @param length
	 * @return
	 */
	public static int getBits(short b, int start, int length) {
		int bit = (int) ((b >> start) & (0xFFFF >> (16 - length)));
		return bit;
	}

	/**
	 * 从int中获取bit的内容
	 * @param b
	 * @param start
	 * @param length
	 * @return
	 */
	public static int getBits(int b, int start, int length) {
		int bit = (int) ((b >> start) & (0xFFFFFFFF >>> (32 - length)));
		return bit;
	}

}
