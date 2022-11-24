package ch9_abstractex_1020;

public class ComputerTest {

	public static void main(String[] args) {
		//추상 클래스는 인스턴스화 할수 없다. 
//		Computer c1 = new Computer();
		Computer c2 = new DeskTop();
//		Computer c3 = new NoteBook();
		Computer c4 = new MyNoteBook();
	}
}
