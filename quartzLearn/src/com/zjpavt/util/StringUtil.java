package com.zjpavt.util;


public class StringUtil {


	/**
	 * @param str String 数组
	 * @return 只要有一个为空或者为空字符串就返回true ，全部通过返回false。
	 */
	public static boolean isNullOrEmpty(String... str) {
		for (String s : str) {
			if (s == null || s.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static int[] commandStr2Int(String command) {
		char[] chars = command.toCharArray();
		int[] result = new int[chars.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Character.getNumericValue(chars[i]);
		}
		return result;
	}

	public static String commandInt2String(int[] command) {
		StringBuilder sb = new StringBuilder();
		for (int i : command) {
			sb.append(i);
		}
		return sb.toString();
	}



	/**
	 * 十六进制字符数组转字节数组
	 *
	 * @param data 十六进制char[]
	 * @return byte[]
	 * @throws IllegalArgumentException
	 */
	public static byte[] decodeHex(char[] data) throws IllegalArgumentException {
		int len = data.length;
		if ((len & 1) != 0) {
			throw new IllegalArgumentException("Odd number of characters.");
		}
		byte[] out = new byte[len >> 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; j < len; i++) {
			int f = toDigit(data[j], j) << 4;
			j++;
			f = f | toDigit(data[j], j);
			j++;
			out[i] = (byte) (f & 0xFF);
		}
		return out;
	}


	/**
	 * 十六进制字符转整数
	 *
	 * @param ch    十六进制char
	 * @param index 十六进制字符在字符数组中的位置
	 * @return 一个整数
	 * @throws IllegalArgumentException
	 */
	private static int toDigit(char ch, int index) throws IllegalArgumentException {
		int digit = Character.digit(ch, 16);
		if (digit == -1) {
			throw new IllegalArgumentException("illegal hexadecimal character " + ch
					+ " at index " + index);
		}
		return digit;
	}
}
