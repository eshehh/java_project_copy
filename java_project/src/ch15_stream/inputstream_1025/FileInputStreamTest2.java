package ch15_stream.inputstream_1025;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamTest2 {

	public static void main(String[] args) {
		//FileInputStream 의 부모 InputStream
		//InputStream는 implements Closeable
		// Closeable 는 상속을 extends AutoCloseable 
		// 그래서, try with resource 구문에서 매개변수에 , 해당 클래스가 
		// AutoCloseable 구현했는지.?
		//FileInputStream(클래스) -> InputStream (클래스)-> Closeable (인터페이스) -> AutoCloseable (인터페이스)
		try(FileInputStream fis = new FileInputStream("input.txt")){ 
			int i;
			while ( (i = fis.read()) != -1){
				System.out.println((char)i);
			}
			System.out.println("end");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
