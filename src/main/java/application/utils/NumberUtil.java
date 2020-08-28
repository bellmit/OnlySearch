package application.utils;

import java.math.BigDecimal;

/**
 * 
 * @author 王天龙
 * @explain NumberUtil : 处理number的tools
 * 
 */
public class NumberUtil {

	/**
	 * 获得小数的小数点后的位数
	 * 
	 * @param number
	 *            double
	 * @return Integer
	 */
	public static int obtainDecimalDigits(double number) {

		String ts = ((Object) number).toString();

		String target = ts.split("\\.")[1];
		String[] ar = target.split("");

		int length = ar.length;

		int i = (int) number;

		if (number == i) {
			length = 0;
		}

		return length;
	}
	
	/**
	 * 
	 * 以下四个函数是对双精度的加减乘除运算
	 */

	public static double doublePrecisionAdd(double a, double b) {

		BigDecimal a2 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return a2.add(b2).doubleValue();
	}

	public static double doublePrecisionSub(double a, double b) {
		BigDecimal a2 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return a2.subtract(b2).doubleValue();
	}

	public static double doublePrecisionMul(double a, double b) {
		BigDecimal a2 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return a2.multiply(b2).doubleValue();
	}

	public static double doublePrecisionDiv(double a, double b, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("error");
		}
		BigDecimal a2 = new BigDecimal(Double.toString(a));
		BigDecimal b2 = new BigDecimal(Double.toString(b));
		return a2.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

}
