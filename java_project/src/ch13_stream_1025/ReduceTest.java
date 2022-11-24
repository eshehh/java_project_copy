package ch13_stream_1025;

import java.util.Arrays;
import java.util.function.BinaryOperator;

class CompareString implements BinaryOperator<String>{

	@Override
	public String apply(String s1, String s2) {
		if (s1.getBytes().length >= s2.getBytes().length) return s1;
		else return s2;
	}
}

public class ReduceTest {

	public static void main(String[] args) {

		String[] greetings = {"�ȳ��ϼ���~~~", "hello", "Good morning", "�ݰ����ϴ�^^"};
		
		System.out.println(Arrays.stream(greetings).reduce("", (s1, s2)-> 
		                          {if (s1.getBytes().length >= s2.getBytes().length) 
				                                  return s1;
		                          else return s2;})); 
		
		String str = Arrays.stream(greetings).reduce(new CompareString()).get(); //BinaryOperator�� ������ Ŭ���� �̿�
		System.out.println(str);
		                          
	}
}
