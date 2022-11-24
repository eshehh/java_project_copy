package ch06_staticex_1019;

public class StudentTest1 {

	public static void main(String[] args) {

		Student studentLee = new Student();
		studentLee.setStudentName("이지원");
		System.out.println(studentLee.serialNum); // 초깃값 출력
		studentLee.serialNum++;					// static 변수 증가
		System.out.println("현재 값 : " + studentLee.serialNum);
		
		Student studentSon = new Student();
		studentSon.setStudentName("손수경");
		studentSon.serialNum++;	
		System.out.println("한번더 클래스 변수 값 증가하기. ");
		System.out.println("studentSon 의 값" + studentSon.serialNum); // 증가된 값 출력
		System.out.println("studentLee 의 값" + studentLee.serialNum); // 증가된 값 출력
		System.out.println("studentLee 의 값" + studentLee);
		System.out.println("studentSon 의 값" + studentSon);
	}
}

