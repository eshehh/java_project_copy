package ch10_bookshelf_1021;

public interface Queue {

	void enQueue(String title);
	String deQueue();
	int getSize();
}
