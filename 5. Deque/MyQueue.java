/*
 * This file implements a data structure functions
 * similar to queue data structure
 * Name: Jiaxin Tang 
 * ID: A15812786
 * EMAIL: jit008@ucsd.edu
 */

/*
 * This class uses MyDeque structure
 * and part of its methods to achieve the
 * functionality of queue
 */
public class MyQueue<E> implements QueueInterface<E> {
	MyDeque<E> theQueue;
	
	public MyQueue(int initialCapacity) {
		theQueue = new MyDeque<E>(initialCapacity);
	}
	
	public boolean empty() {
		return theQueue.size() == 0;
	}
	
	public void enqueue(E e) {
		theQueue.addLast(e);
	}
	
	public E peek() {
		return theQueue.peekFirst();
	}
	
	public E dequeue() {
		return theQueue.removeFirst();
	}
}