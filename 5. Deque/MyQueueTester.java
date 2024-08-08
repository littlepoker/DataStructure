/*
 * This file imports junit test to test MyQueue Data Structure
 * Name: Jiaxin Tang 
 * ID: A15812786
 * EMAIL: jit008@ucsd.edu
 */

import org.junit.*;
import static org.junit.Assert.*;

public class MyQueueTester {
	static final int CONSTANT_TWO = 2;
	static final int CONSTANT_THREE = 3;
	
	MyQueue<Integer> empty;
	MyQueue<Integer> zero;
	MyQueue<Integer> occupiedLast;
	MyQueue<Integer> occupiedMiddle;
	
	
	@Before
	public void setUp() {
		Integer[] last = {null, null, 0};
		Integer[] middle = {null, 0, null};
		empty = new MyQueue<Integer>(CONSTANT_TWO);
		zero = new MyQueue<Integer>(0);
		occupiedLast = new MyQueue<Integer>(CONSTANT_THREE);
		occupiedMiddle = new MyQueue<Integer>(CONSTANT_THREE);
		occupiedLast.theQueue.data = last;
		occupiedMiddle.theQueue.data = middle;
		occupiedLast.theQueue.front = 2;
		occupiedLast.theQueue.rear = 2;
		occupiedMiddle.theQueue.front = 1;
		occupiedMiddle.theQueue.rear = 1;
		occupiedLast.theQueue.size = 1;
		occupiedMiddle.theQueue.size = 1;
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testConstructorException() {
		try{
			MyQueue<Integer> illegal = new MyQueue<>(-1);
            fail("Did not catch constructor exception");
        }
        catch(IllegalArgumentException exc){

        }
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPush() {
		Integer[] expected = {0, null};
		empty.enqueue(0);
		assertEquals(0, empty.theQueue.front);
		assertEquals(0, empty.theQueue.rear);
		assertEquals(expected, empty.theQueue.data);
		
		occupiedLast.enqueue(0);
		Integer[] expectedLast = {0, null, 0};
		assertEquals(CONSTANT_TWO, occupiedLast.theQueue.front);
		assertEquals(0, occupiedLast.theQueue.rear);
		assertEquals(expectedLast, occupiedLast.theQueue.data);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPeek() {
		assertEquals(new Integer(0), occupiedLast.peek());
		occupiedLast.enqueue(CONSTANT_TWO);
		assertEquals(new Integer(0), occupiedLast.peek());
	}
	
	@Test
	public void testEmpty() {
		assertEquals(false, occupiedLast.empty());
		assertEquals(true, empty.empty());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDequeue() {
		empty.enqueue(CONSTANT_TWO);
		assertEquals(new Integer(CONSTANT_TWO), empty.dequeue());
		assertEquals(true, empty.empty());
		
		occupiedMiddle.enqueue(CONSTANT_TWO);
		assertEquals(new Integer(0), occupiedMiddle.dequeue());
		occupiedMiddle.dequeue();
		assertEquals(true, occupiedMiddle.empty());
	}
}