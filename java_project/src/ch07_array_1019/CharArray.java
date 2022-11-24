package ch07_array_1019;

public class CharArray {

	public static void main(String[] args) {

		char[] alpahabets = new char[26];
		char ch = 'A';
		
		for(int i=0 ; i<5; i++, ch++){
			alpahabets[i] = ch;
		}
		
		for(int i=0; i<5; i++){
			System.out.println(alpahabets[i] + "," + (int)alpahabets[i]);
		}
	}
}