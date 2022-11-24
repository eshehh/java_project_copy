package ch14_exception_1025;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExceptionHandling2 {

	public static void main(String[] args) {

		FileInputStream fis = null;
		try {
			fis = new FileInputStream("a.txt");
		} catch (FileNotFoundException e) {
//			System.out.println(e);
//			e.printStackTrace();
			return;
		}finally{
			if(fis != null){
				try {
					fis.close(); // 자원 반납. 사용 했던 메모리는 사용 후 , 꼭 반납을 해야지 메모리 누수가 발생x.
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
				}
			}
			System.out.println("항상 수행 됩니다.");
		}
		System.out.println("여기도 수행됩니다.");
	}
}





