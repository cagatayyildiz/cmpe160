package linkedList;

import java.util.LinkedList;
import java.util.Random;

import linkedList.MyLinkedList;

public class Test {

	
	public static void main(String[] args) {
		MyLinkedList l = new MyLinkedList(50 , 100);
		Random rand = new Random();
		for (int i = 0; i < 5; i++) {
			//l.addLast(rand.nextInt(100));
			l.addLast(i);
		}
		
		for(int i = 2; i >= 0 ; i--){
			l.remove(i);
		}
		
		int size = l.size();
		
		for(int i = 0 ; i < size ; i++){
			l.remove(0);
		}
		
		l.addLast(50);
		l.addLast(30);
		l.remove(1);
		l.remove(0);
		l.add(0,5);
		l.add(6);
		l.add(7);
		l.add(8);
		size = l.size() ;
		for(int i = size - 1 ; i >= 0 ; i--){
			l.remove(i);
		}

		//Exception
		l.remove(0);

	}
}
