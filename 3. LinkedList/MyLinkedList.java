/** This file implements a data structure similar to linked list
 *  with implementation of AbstractList class and generic
 *  as well as its iterator
 *  Name: Jiaxin Tang
 *  PID: A15812786
 *  EMAIL:jit008@ucsd.edu
 */

import java.util.*;

/** TODO: This class implements a data structure similar
 *  to linked list to store data with connected nodes
 *  with generics and its basic methods
 */

public class MyLinkedList<E> extends AbstractList<E> {

	int nelems; //size of linked list
	
	//dummy nodes with null content
	Node head;
	Node tail;
	
	//First index of the data structure and the empty size
	final int DEFUALT_INDEX = 0; 
	final int SHIFT_SPACE = 1;
	
	/**
	 * 
	 * Store individual nodes object and the references
	 * of previous and next nodes
	 *
	 */
	protected class Node {
		E data;
		Node next;
		Node prev;
		
		/** 
		 * Constructor of Node class
		 * Initialize the element of Node with given element  
		 * @param element Element of the Node
		 */
		public Node(E element)
		{
			data = element;
		}
		
		/** 
		 * set the previous Node pointer to given Node
		 *   
		 * @param n Node to be set as previous
		 * @return void
		 */
		public void setPrev(Node p)
		{
			prev = p;
		}
		
		/** 
		 * set the next Node pointer to given Node
		 *   
		 * @param n Node to be set as next
		 * @return void
		 */
		public void setNext(Node n)
		{
			next = n;
		}
		
		/** 
		 * set the element of Node as given element
		 *   
		 * @param e new element of the Node
		 * @return void
		 */
		public void setElement(E e)
		{
			data = e;
		}
		
		/** 
		 * get the next Node
		 *   
		 * @return next Node
		 */
		public Node getNext()
		{
			return next;
		}

		/** 
		 * get the previous Node
		 *   
		 * @return previous Node
		 */
		public Node getPrev()
		{
			return prev;
		} 
		
		/** 
		 * get element of the Node
		 *   
		 * @return element of the Node 
		 */
		public E getElement()
		{
			return data;
		} 
	}

	/** ListIterator implementation 
	 * This class implements ListIterator to build an iterator
	 * for the implemented MyLinkedList data structure
	 * */ 
	protected class MyListIterator implements ListIterator<E> {

		boolean forward; //current direction of iterator
		
		//whether can call remove or set, true after calling next or previous
		boolean canRemoveOrSet; 
		Node left,right; 
		int idx; //index of iterator

		/** 
		 * Constructor of MyListIterator
		 * Initialize the iterator at index 0 going forward  
		 */
		public MyListIterator()
		{
			//Initialize the default state of iterator
			forward = true;
			canRemoveOrSet = false;
			left = head;
			right = head.getNext();
			idx = DEFUALT_INDEX;
		}

		
		/** 
		 * add an element to the data structure
		 *   
		 * @param e new element to add
		 * @return void
		 */
		@Override
		public void add(E e)
		{
			if (e == null) {
				throw new NullPointerException();
			}
			else {	
				Node newNode = new Node(e);
				
				//Set the reference of previous adjacent Nodes
				left.setNext(newNode);
				right.setPrev(newNode);
				
				//set the reference of new Node
				newNode.setPrev(left);
				newNode.setNext(right);
				
				//set the reference of iterator 
				left = newNode;
				canRemoveOrSet = false; //cannot remove or set right after add
				idx++;
				nelems++;
			}
		}

		
		/** 
		 * check if there is another element going forward
		 *   
		 * @return true if there is, false otherwise
		 */
		@Override
		public boolean hasNext()
		{
			return right.getNext() != null;
		}

		/** 
		 * check if there is another element going backward
		 *   
		 * @return true if there is, false otherwise
		 */
		@Override
		public boolean hasPrevious()
		{
			return left.getPrev() != null;
		}

		/** 
		 * 
		 * return the right hand element of the cursor
		 * then moving the cursor forward for one space
		 *  
		 * @return the next element going forward
		 */
		@Override
		public E next()
		{
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			else {
				// Move cursor forward by 1 space
				left = left.getNext();
				right = right.getNext();
				idx++;
				canRemoveOrSet = true;
				forward = true; //change direction to forward
				return left.getElement();
			}
		}

		/** 
		 * 
		 * return the index of right hand Node of the cursor
		 *  
		 * @return index of right hand Node
		 */
		@Override
		public int nextIndex()
		{
			return idx;	
		}

		/** 
		 * 
		 * return the left hand element of the cursor
		 * then moving the cursor backward for one space
		 *  
		 * @return the next element going backward
		 */
		@Override
		public E previous()
		{
			if (!hasPrevious()) {
				throw new NoSuchElementException();
			}
			else {
				
				// Move the cursor backward by 1 space
				left = left.getPrev();
				right = right.getPrev();
				idx--;
				canRemoveOrSet = true;
				forward = false; //change direction to backward
				return right.getElement();
			}
		}

		/** 
		 * 
		 * return the index of left hand Node of the cursor
		 *  
		 * @return index of left hand Node
		 */
		@Override
		public int previousIndex()
		{
			return idx - SHIFT_SPACE; 
		}

		/** 
		 * remove the Node returned by most recent called next or previous
		 *  
		 * @return void
		 */
		@Override
		public void remove()
		{
			if (!canRemoveOrSet) {
				throw new IllegalStateException();
			}
			else {
				//Remove after call next
				if (forward) {
					
					// Set new reference
					left.getPrev().setNext(right);
					right.setPrev(left.getPrev());
					left = right.getPrev();
					idx--;
				}
				//Remove after call previous
				else {
					
					// Set new reference
					right.getNext().setPrev(left);
					left.setNext(right.getNext());
					right = left.getNext();
				}
				nelems--;
				canRemoveOrSet = false;
			}
		}
		
		/** 
		 * set the Node returned by most recent called next or previous
		 * with given element
		 * @param e new element to be setted
		 * @return void
		 */
		@Override
		public void set(E e) 
		{
			if (!canRemoveOrSet) {
				throw new IllegalStateException();
			}
			else if (e == null) {
				throw new NullPointerException();
			}
			else {
				//case after call next
				if (forward) {
					left.setElement(e);
				}
				//case after call previous
				else {
					right.setElement(e);
				}
				canRemoveOrSet = false;
			}
		}
	}

	//  Implementation of the MyLinkedList Class
	/** Only 0-argument constructor is define */
	/** 
	 * Constructor of MyLinkedList
	 * Initialize the data structure and dummy Nodes
	 */
	public MyLinkedList()
	{
		nelems = DEFUALT_INDEX;
		
		//set up dummy Nodes
		head = new Node(null);
		tail = new Node(null);
		head.setNext(tail);
		tail.setPrev(head);
	}

	/** 
	 * return the size of the data structure
	 *  
	 * @return current number of Nodes (except dummies) in the structure
	 */
	@Override
	public int size()
	{
		// need to implement the size method
    	return nelems; // XXX-CHANGE-XXX
	}

	/** 
	 * get the element at specific index
	 * 
	 * @param index index to be accessed 
	 * @return element at given index
	 */
	@Override
	public E get(int index)
	{
		if (index < DEFUALT_INDEX || index >= nelems) {
			throw new IndexOutOfBoundsException();
		}
		else {
			return getNth(index).getElement(); // Access index by getNth
		}
	}

	/** 
	 * add the element as a new Node at specific index
	 * 
	 * @param index index to be added 
	 * @return void
	 */
	@Override
	public void add(int index, E data) 
	{
		/* Add your implementation here */
		if (index < DEFUALT_INDEX || index > nelems) {
			throw new IndexOutOfBoundsException();
		}
		else if (data == null) {
			throw new NullPointerException();
		}
		else {
			Node newNode = new Node(data);
			/*Set up the new Node's reference and change old Nodes' references
			*Based on different cases
			*/
			if (index == DEFUALT_INDEX) {
				newNode.setPrev(head);
				head.getNext().setPrev(newNode);
				newNode.setNext(head.getNext());
				head.setNext(newNode);
			}
			else if (index == nelems) {
				newNode.setPrev(tail.getPrev());
				tail.getPrev().setNext(newNode);
				newNode.setNext(tail);
				tail.setPrev(newNode);
			}
			else {
				Node origenNode = getNth(index);
				newNode.setPrev(origenNode.getPrev());
				origenNode.getPrev().setNext(newNode);
				newNode.setNext(origenNode);
				origenNode.setPrev(newNode);
			}
			nelems++; //increment size
		}
	}

	/** 
	 * add the element as a new Node at end of data structure
	 *
	 * @return true if no exception thrown
	 */
	public boolean add(E data)
	{
		if (data == null) {
			throw new NullPointerException();
		}
		else {
			Node newNode = new Node(data);
			
			// Set up the new Node's reference and change old Nodes' references
			newNode.setPrev(tail.getPrev());
			newNode.setNext(tail);
			tail.getPrev().setNext(newNode);
			tail.setPrev(newNode);
			nelems++; //increment size
			return true;
		}
	}

	/** 
	 * set the element of Node at specific index as given new data
	 * 
	 * @param index Index of the Node to be accessed
	 * @param data New data to override 
	 * @return previous data at given index
	 */
	public E set(int index, E data) 
	{	
		if (index < DEFUALT_INDEX || index >= nelems) {
			throw new IndexOutOfBoundsException();
		}
		else if (data == null) {
			throw new NullPointerException();
		}
		else {
			E previousElement = getNth(index).getElement();
			getNth(index).setElement(data); // Access Node by index 
			return previousElement;
		}
	}
	
	/** 
	 * remove the Node at specific index
	 * 
	 * @param index Index of the Node to be removed
	 * @return the data of removed Node
	 */
	public E remove(int index)
	{
		if (index < DEFUALT_INDEX || index >= nelems) {
			throw new IndexOutOfBoundsException();
		}
		else {
			// Set up new adjacent Nodes' references
			Node removedNode = getNth(index);
			removedNode.prev.setNext(removedNode.getNext());
			removedNode.next.setPrev(removedNode.getPrev());
			nelems--; //decrement size
			return removedNode.getElement();
		}
	}

	/** 
	 * clear the data structure to be empty, left only dummy Nodes
	 * 
	 * @return void
	 */
	public void clear()
	{
		// set data structure to initial state
		head.setNext(tail);
		tail.setPrev(head);
		nelems = DEFUALT_INDEX;
	}

	/** 
	 * check whether the data structure is empty
	 * 
	 * @return true if only dummy Nodes exist
	 */
	public boolean isEmpty()
	{
		return nelems == DEFUALT_INDEX;
	}

	/** 
	 * access Nodes by index indirectly
	 * 
	 * @param index index of the Nodes to be accessed
	 * @return Node at given index
	 */
	protected Node getNth(int index)
	{
		if (index < DEFUALT_INDEX || index >= nelems) {
			throw new IndexOutOfBoundsException();
		}
		else {
			Node currentNode = head.getNext();
			
			//loop throw index and next to simulate index
			while (index > DEFUALT_INDEX) {
				currentNode = currentNode.getNext();
				index--;
			}
			return currentNode;
		}
	}

	/** 
	 * create a MyListIterator of the data structure
	 * 
	 * @return upper casted MyListIterator
	 */
	public Iterator<E> iterator()
	{
		return (Iterator<E>) new MyListIterator();
	}
	
	/** 
	 * create a MyListIterator of the data structure
	 * 
	 * @return upper casted MyListIterator
	 */
	public ListIterator<E> listIterator()
	{
		return (ListIterator<E>) new MyListIterator();
	}

}

// VIM: set the tabstop and shiftwidth to 4 
// vim:tw=78:ts=4:et:sw=4