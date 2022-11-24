package ch14_exception_1025;

import java.io.FileInputStream;

public class AutoCloseTest {
	
	public static void main(String[] args) {
		AutoCloseObj obj = new AutoCloseObj();
		try (AutoCloseObj obj2 = obj){
			// 임의로 예외를 발생시키는 코드.
			throw new Exception();
		}catch(Exception e) {
			System.out.println("예외부분입니다.");
		}
	}
}
