package ch07_array_1019;

public class ObjectCopy3 {

	public static void main(String[] args) {
		Book[] bookArray1 = new Book[3];
		Book[] bookArray2 = new Book[3];
		
		bookArray1[0] = new Book("�¹���", "������");
		bookArray1[1] = new Book("���̾�", "�츣�� �켼");
		bookArray1[2] = new Book("��� �� ���ΰ�", "���ù�");
 		
		bookArray2[0] = new Book(); //��ü ���� ����
		bookArray2[1] = new Book();
		bookArray2[2] = new Book();
		
		for(int i=0; i<bookArray1.length; i++){   // ������ ��Ҹ� ����
			bookArray2[i].setBookName(bookArray1[i].getBookName());
			bookArray2[i].setAuthor(bookArray1[i].getAuthor());
		}
		
		for(int i=0; i<bookArray2.length; i++){  //����� ���� Ȯ��
			bookArray2[i].showBookInfo();
		}
		
		bookArray1[0].setBookName("����");   //bookArray1 �� ���� ����
		bookArray1[0].setAuthor("�ڿϼ�");
		
		System.out.println("=== bookArray1 ===");    //bookArray1 ���
		for(int i=0; i<bookArray1.length; i++){
			bookArray1[i].showBookInfo();
		}
		
		System.out.println("=== bookArray2 ===");    //bookArray2 ���
		for(int i=0; i<bookArray2.length; i++){
			bookArray2[i].showBookInfo();   // bookArray1 �� �ٸ� �������� ��µ�
		}
	}
}
