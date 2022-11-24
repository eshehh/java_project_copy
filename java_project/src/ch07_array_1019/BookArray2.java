package ch07_array_1019;

public class BookArray2 {

	public static void main(String[] args) {
		Book[] library = new Book[5];
		Student[] students = new Student[5];
		students[0] = new Student("이상용1",20000);
		students[1] = new Student("이상용2",20000);
		students[2] = new Student("이상용3",20000);
		students[3] = new Student("이상용4",20000);
		students[4] = new Student("이상용5",20000);
		
		library[0] = new Book("태백산맥", "조정래");
		library[1] = new Book("데미안", "헤르만 헤세");
		library[2] = new Book("어떻게 살 것인가", "유시민");
		library[3] = new Book("토지", "박경리");
		library[4] = new Book("어린왕자", "생텍쥐페리");
		
		for(int i=0; i<students.length; i++){
			students[i].showInfo();
		}
		
//		for(int i=0; i<library.length; i++){
//			library[i].showBookInfo();
//		}
//		
//		for(int i=0; i<library.length; i++){
//			System.out.println(library[i]);
//		}
	}
}
