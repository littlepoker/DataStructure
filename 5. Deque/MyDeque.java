/*
 * This file implements a data structure functions
 * similar to deque data structure
 * Name: Jiaxin Tang 
 * ID: A15812786
 * EMAIL: jit008@ucsd.edu
 */

/*
 * This class override the various methods of 
 * deque interface to implement the functionality
 * of deque data structure
 */
public class MyDeque<E> implements DequeInterface<E>{
	//constants
	static final int CONSTANT_TEN = 10;
	static final int CONSTANT_TWO = 2;
	static final int CONSTANT_ONE = 1;
	
	Object[] data; //the array to store data
	int size; //the number of elements in the structure
	int front; //the index of element at top
	int rear; //the index of element at bottom
	
	/** 
	 * Constructor of the MyDeque data structure
	 *   
	 * @param initialCapacity The initial length of data array
	 */
	public MyDeque(int initialCapacity) {
		//throw an exception if the initialCapacity is negative
		if (initialCapacity < 0) {
			throw new IllegalArgumentException();
		}
		else {
			//initialize instance variables
			data = new Object[initialCapacity];
			size = 0;
			front = 0;
			rear = 0;
		}
	}
	
	/** 
	 * Return the size in the data structure
	 *   
	 * @return The number of elements in the data structure
	 */
	@Override
	public int size() {
		return size;
	}
	
	/** 
	 * Expand the array if full load
	 *   
	 * @return void
	 */
	@Override
	public void expandCapacity() {
		//if the array has length 0, set to 10, double the length otherwise
		if (data.length == 0) {
			data = new Object[CONSTANT_TEN];
		}
		else {
			Object[] newData = new Object[data.length * CONSTANT_TWO];
			//rearange all the elements as front at the head of array
			//based on different cases
			if (rear >= front) {
				for (int i = front; i <= rear; i++) {
					newData[i - front] = data[i]; 
				}
			}
			else {
				for (int i = front; i <= data.length + rear; i++) {
					if (i < data.length) {
						newData[i - front] = data[i];
					}
					else {
						newData[i - front] = data[i - data.length];
					}
				}
			}
			front = 0;
			if (size == 0) {
				rear = front;
			}
			else {
				rear = size - 1;
			}
			data = newData;
		}
	}
	
	/** 
	 * add element to the front of the structure
	 *   
	 * @param element The element to be added
	 * @return void
	 */
	@Override
	public void addFirst(E element) {
		//throw an exception if element is null
		if (element == null) {
			throw new NullPointerException();
		}
		else {
			//expand capacity if it's full before adding
			if (size == data.length) {
				expandCapacity();
			}
			//add element and adjust pointer index based on cases
			else {
				if (size == 0) {
					data[0] = element;
				}
				else if (front == 0) {
					int position = data.length - CONSTANT_ONE;
					data[position] = element;
					front = position;
				}
				else {
					data[front - CONSTANT_ONE] = element;
					front--;
				}
				size++; //increment size
			}
		}
	}
	
	/** 
	 * add element to the back of the structure
	 *   
	 * @param element The element to be added
	 * @return void
	 */
	@Override
	public void addLast(E element) {
		//throw an exception if element is null
		if (element == null) {
			throw new NullPointerException();
		}
		//expand capacity if it's full before adding
		else {
			if (size == data.length) {
				expandCapacity();
			}
			//add element and adjust pointer index based on cases
			else {
				if (size == 0) {
					data[0] = element;
				}
				else if (rear == data.length - CONSTANT_ONE) {
					data[0] = element;
					rear = 0;
				}
				else {
					data[rear + CONSTANT_ONE] = element;
					rear++;
				}
				size++; //increment size
			}
		}
	}
	
	/** 
	 * remove the head element of the structure
	 *   
	 * @return element being removed, null if its empty
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E removeFirst() {
		if (size == 0) {
			return null;
		}
		else {
			E removedElement = (E)data[front];
			data[front] = null;
			if (front != rear) {
				if (front == data.length - CONSTANT_ONE) {
					front = 0;
				}
				else {
					front++;
				}
			}
			size--;
			return removedElement;
		}
	}
	
	/** 
	 * remove the back element of the structure
	 *   
	 * @return element being removed, null if its empty
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E removeLast() {
		if (size == 0) {
			return null;
		}
		else {
			E removedElement = (E)data[rear];
			data[rear] = null;
				if (front != rear) {
				if (rear == 0) {
					rear = data.length - CONSTANT_ONE;
				}
				else {
					rear--;
				}
			}
			size--;
			return removedElement;
		}
	}
	
	/** 
	 * return the front element of the structure
	 *   
	 * @return element being removed, null if its empty
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E peekFirst() {
		return (E)data[front];
	}
	
	/** 
	 * return the back element of the structure
	 *   
	 * @return element being removed, null if its empty
	 */
	@Override
	@SuppressWarnings("unchecked")
	public E peekLast() {
		return (E)data[rear];
	}
}