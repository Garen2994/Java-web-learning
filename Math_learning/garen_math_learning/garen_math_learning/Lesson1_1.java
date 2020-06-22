package garen_math_learning;

import java.math.BigInteger;


public class Lesson1_1 {
	/**
	 * @Description : ʮ����ת��Ϊ������
	 * @param decimalSource
	 * @return String
	 */
	public static String decimalToBinary(int decimalSource) {
		
		BigInteger bi = new BigInteger(String.valueOf(decimalSource));//ת��ΪBigInteger���ͣ�Ĭ��ʮ����
		return bi.toString(2); //ת���ɶ�����
		
	}
	/**
	 * @Description : ������ת��Ϊʮ����
	 * @param binarySource
	 * @return int
	 */
	public static int binaryToDecimal(String binarySource) {
		
		BigInteger bi = new BigInteger(binarySource,2); //ת��ΪBigInteger���ͣ�����2ָ��������
		return Integer.parseInt(bi.toString()); //Ĭ��ת��Ϊʮ����
	}
	public static void main(String[] args) {
	
		int a = 53;
		String b = "110101";
		System.out.println(String.format(" ���� %d�Ķ������� %s",a,Lesson1_1.decimalToBinary(a)));
		System.out.println(String.format(" ���� %s��ʮ������ %s",b,Lesson1_1.binaryToDecimal(b)));
		
	}

}
