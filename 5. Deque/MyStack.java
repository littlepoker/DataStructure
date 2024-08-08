/*
 * This file implements a data structure functions
 * similar to stack data structure
 * Name: Jiaxin Tang 
 * ID: A15812786
 * EMAIL: jit008@ucsd.edu
 */

/*
 * This class uses MyDeque structure
 * and part of its methods to achieve the
 * functionality of stack
 */
public class MyStack<E> implements StackInterface<E> {
	MyDeque<E> theStack;
	
	public MyStack(int initialCapacity) {
		theStack = new MyDeque<E>(initialCapacity);
	}
	
	public boolean empty() {
		return theStack.size() == 0;
	}
	
	public void push(E e) {
		theStack.addFirst(e);
	}
	
	public E peek() {
		return theStack.peekFirst();
	}
	
	public E pop() {
		return theStack.removeFirst();
	}
}