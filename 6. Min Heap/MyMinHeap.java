/**
 * Name: Jiaxin Tang
 * PID: A15812786
 * Email: jit008@ucsd.edu
 * Construct a data structure to achieve the goal
 * of min heap and obey its law of key order and complete
 * which parent must be less than children all nodes must
 * fill current level from left
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This class implements a data strucutre
 * similar to min heap by overriding its methods
 * to achieve the similar functionality by a list
 */

public class MyMinHeap<E extends Comparable<E>>
{
	//Constant used for computation
	private static final int CONSTANT_ONE = 1;
	private static final int CONSTANT_TWO = 2;
	
	//The list used to achieve the purpose of min heap and store elements
    protected List<E> list; 

    /** 
     * Constructor of MyMinHeap
     * Initialize the list as new empty ArrayList
     */
    public MyMinHeap() {
    	list = new ArrayList<E>();
    }

    /** 
     * Constructor of MyMinHeap
     * Initialize the list an ArrayList with elements in collection  
     * 
     * @param collection The collection of elements as initial elements
     */
    public MyMinHeap(Collection<? extends E> collection) {
    	//Throw an exception if collection is null or contains null element
    	if (collection == null) {
    		throw new NullPointerException();
    	}
    	else if (collection.contains(null)) {
    		throw new NullPointerException();
    	}
    	else {
    		list = new ArrayList<E>(collection);
    		//percolateDown the element in the collection for key order law
    		for (int i = list.size() - CONSTANT_ONE; i >= 0; i--) {
    			percolateDown(i);
    		}
    	}
    }

    /** 
     * Swap the position of two elements in the list
     *   
     * @param from The index of the first element in the swap process
     * @param to The index of the second element in the swap process
     * @return void
     */
    protected void swap(int from, int to) {
    	// Three steps to swap two variables
    	E temp = list.get(from);
    	list.set(from, list.get(to));
    	list.set(to, temp);
    }

    /** 
     * return the index of given node's parent
     *   
     * @param index The index of element to seek for parent
     * @return the index of given node's parent
     */
    protected int getParentIdx(int index) {
        return (index + CONSTANT_ONE) / CONSTANT_TWO - CONSTANT_ONE;
    }
    
    /** 
     * return the index of given node's left child
     *   
     * @param index The index of element to seek for left child
     * @return the index of given node's left child
     */
    protected int getLeftChildIdx(int index) {
        return (index + CONSTANT_ONE) * CONSTANT_TWO - CONSTANT_ONE;
    }

    /** 
     * return the index of given node's right child
     *   
     * @param index The index of element to seek for right child
     * @return the index of given node's right child
     */
    protected int getRightChildIdx(int index) {
        return (index + CONSTANT_ONE) * CONSTANT_TWO;
    }

    /** 
     * return the index of given node's minimum child of two
     *   
     * @param index The index of element to seek for minimum
     * @return the index of the given node's minimum child
     * the index of left child if only one child or two child with same key
     * -1 if no child exist 
     */
    protected int getMinChildIdx(int index) {
    	int left = getLeftChildIdx(index);
    	int right = getRightChildIdx(index);
    	//case when no child
        if (left >= list.size()) {
        	return -1;
        }
        //case when only left child exist
        else if (right >= list.size()) {
        	return left;
        }
        //case when left child is greater than left child
        else if (list.get(right).compareTo(list.get(left)) < 0) {
        	return right;
        }
        //case when left child is less than or equal to left child
        else {
        	return left;
        }
    }
    
    /** 
     * for a given node, check upward to see if some parent node
     * violates key order law, swap if it violates until no violation
     * for the given node
     *   
     * @param index The index of element to check violation upward
     * @return void
     */
    protected void percolateUp(int index) {
    	//Only start check if the given node is not the root 
    	if (index != 0) {
    		int parent = getParentIdx(index);
    		if (list.get(index).compareTo(list.get(parent)) < 0) {
    			swap(index, parent);
    			//recursive call to keep track
    			percolateUp(parent);
    		}
    	}
    }

    /** 
     * for a given node, check downward to see if some child node
     * violates key order law, swap with smaller child if it violates law
     * until no violation for the given node
     *   
     * @param index The index of element to check violation downward
     * @return void
     */
    protected void percolateDown(int index) {
    	int minChild = getMinChildIdx(index);
    	//Only check key order if there is child exists
    	if (minChild != -1) {
    		if (list.get(minChild).compareTo(list.get(index)) < 0) {
    			swap(index, minChild);
    			//recursive call to keep track
    			percolateDown(minChild);
    		}
    	}
    }
    
    /** 
     * remove the node at given index while maintaining the heap property
     *   
     * @param index The index of element to be removed
     * @return the element to be removed
     */
    protected E deleteIndex(int index) {
    	E orig = list.get(index);
    	int lastIndex = list.size() - CONSTANT_ONE;
        E replacement = list.get(lastIndex);
        //simply remove the element if it's just the last element in the list
        if (orig.compareTo(replacement) == 0) {
        	list.remove(lastIndex);
        }
        /*
         * for other cases, swap the removed element with the last element
         * Also adjust for possible violation of key order law after swap
         */
        else if (orig.compareTo(replacement) < 0){
        	swap(index, lastIndex);
        	list.remove(lastIndex);
        	percolateDown(index);
        }
        else {
        	swap(index, lastIndex);
        	list.remove(lastIndex);
        	percolateUp(index);
        }
        return orig;
    }

    /** 
     * Add an new element to the data structure and keep key order law
     *   
     * @param element The element to be added to the structure
     * @return void
     */
    public void insert(E element) {
    	// Throw an exception if element is null
    	if (element == null) {
    		throw new NullPointerException();
    	}
    	else {
    		//add the element to the end of list and check key order law
    		list.add(element);
    		percolateUp(list.size() - 1);
    	}
    }

    /** 
     * return the node with smallest value in the data structure
     *   
     * @return the first element of the list for well-functioned min heap
     * null if the list is empty
     */
    public E getMin() {
        if (list.size() == 0) {
        	return null;
        }
        else {
        	return list.get(0);
        }
    }

    /** 
     * remove the smallest element and return that element
     *   
     * @return the element being removed, or null if the list is empty
     */
    public E remove() {
        return deleteIndex(0);
    }
    
    /** 
     * return the number of elements in the data structure
     *   
     * @return the number of elements in the data structure
     */
    public int size() {
        return list.size();
    }

    /** 
     * clear all the elements in the data structure and
     * set the list as a new empty ArrayList
     *   
     * @return void
     */
    public void clear() {
    	list = new ArrayList<E>();
    }		
}
