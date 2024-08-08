/**
 * Name: Jiaxin Tang
 * PID: A15812786
 * Email: jit008@ucsd.edu
 * Construct a data structure to achieve the goal
 * of priority queue with the other implemenented data
 * structure min heap
 */

//Import collection to make parameter collection work
import java.util.Collection;

/**
 * This class implements a data strucutre
 * similar to priority queue by overriding its methods
 * to achieve the similar functionality by implemented min heap
 */

public class MyPriorityQueue<E extends Comparable<E>>{
	//MyMinHeap structure used for priority queue implementation
	protected MyMinHeap<E> heap;
	
	/** 
     * Constructor of MyPriorityQueue
     * Initialize the heap without any existing element
     */
	public MyPriorityQueue(){
		heap = new MyMinHeap<E>();
	}
	
	/** 
     * Constructor of MyPriorityQueue
     * Initialize the heap with elements in collection 
     * 
     * @param collection The collection of elements as initial elements
     */
	public MyPriorityQueue(Collection<? extends E> collection) {
		//Throw an exception if collection is null or contains null element
		if (collection == null) {
			throw new NullPointerException();
		}
		else if (collection.contains(null)) {
    		throw new NullPointerException();
    	}
		else {
			heap = new MyMinHeap<E>(collection);
		}
	}
	
	/** 
     * push an new element to the data structure and
     * adjust its position based on correct priority
     *   
     * @param element The element to be added to the structure
     * @return void
     */
	public void push(E element) {
		//Throw an exception if element is null
		if (element == null) {
			throw new NullPointerException();
		}
		else {
			heap.insert(element);
		}
	}
	
	/** 
     * return the element with highest priority in the data structure
     *   
     * @return the element with lowest value in the data strucutre
     * null if the list is empty
     */
	public E peek() {
		return heap.getMin();
	}
	
	/** 
     * remove the element with highest priority in the data structure
     * and return that removed element  
     *   
     * @return the element with lowest value in the data strucutre
     * null if the list is empty
     */
	public E pop() {
		return heap.remove();
	}
	
	/** 
     * return the number of elements in the data structure
     *   
     * @return the number of elements in the data structure
     */
	public int getLength() {
		return heap.size();
	}
	
	/** 
     * clear all the elements in the data structure and
     * set the list of heap as new empty ArrayList
     *   
     * @return void
     */
	public void clear() {
		heap.clear();
	}
}