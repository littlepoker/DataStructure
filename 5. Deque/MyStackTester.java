/*
 * This file imports junit test to test MyStack Data Structure
 * Name: Jiaxin Tang 
 * ID: A15812786
 * EMAIL: jit008@ucsd.edu
 */

import org.junit.*;
import static org.junit.Assert.*;

public class MyStackTester {
	static final int CONSTANT_TWO = 2;
	static final int CONSTANT_THREE = 3;
	
	MyStack<Integer> empty;
	MyStack<Integer> zero;
	MyStack<Integer> occupiedFirst;
	MyStack<Integer> occupiedMiddle;
	
	
	@Before
	public void setUp() {
		Integer[] first = {0, null, null};
		Integer[] middle = {null, 0, null};
		empty = new MyStack<Integer>(CONSTANT_TWO);
		zero = new MyStack<Integer>(0);
		occupiedFirst = new MyStack<Integer>(CONSTANT_THREE);
		occupiedMiddle = new MyStack<Integer>(CONSTANT_THREE);
		occupiedFirst.theStack.data = first;
		occupiedMiddle.theStack.data = middle;
		occupiedMiddle.theStack.front = 1;
		occupiedMiddle.theStack.rear = 1;
		occupiedFirst.theStack.size = 1;
		occupiedMiddle.theStack.size = 1;
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testConstructorException() {
		try{
            MyStack<Integer> illegal = new MyStack<>(-1);
            fail("Did not catch constructor exception");
        }
        catch(IllegalArgumentException exc){

        }
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPush() {
		Integer[] expected = {0, null};
		empty.push(0);
		assertEquals(0, empty.theStack.front);
		assertEquals(0, empty.theStack.rear);
		assertEquals(expected, empty.theStack.data);
		
		occupiedFirst.push(0);
		Integer[] expectedFirst = {0, null, 0};
		assertEquals(CONSTANT_TWO, occupiedFirst.theStack.front);
		assertEquals(0, occupiedFirst.theStack.rear);
		assertEquals(expectedFirst, occupiedFirst.theStack.data);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPeek() {
		assertEquals(new Integer(0), occupiedFirst.peek());
		occupiedFirst.push(CONSTANT_TWO);
		assertEquals(new Integer(CONSTANT_TWO), occupiedFirst.peek());
	}
	
	@Test
	public void testEmpty() {
		assertEquals(false, occupiedFirst.empty());
		assertEquals(true, empty.empty());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPop() {
		empty.push(CONSTANT_TWO);
		assertEquals(new Integer(CONSTANT_TWO), empty.pop());
		assertEquals(true, empty.empty());
		
		assertEquals(new Integer(0), occupiedMiddle.pop());
		assertEquals(true, occupiedMiddle.empty());
	}
}