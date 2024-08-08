/**
 * Name: Jiaxin Tang
 * PID: A15812786
 * Email: jit008@ucsd.edu
 * This file tests the sanity and functionality correctness of MyPriorityQueue 
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

/**
 * This class implements junit test to use
 * unit tests to test the correctness of methods of MyPriorityQueue
 */

public class MyPriorityQueueTester {
	private MyPriorityQueue<Integer> empty;
	private MyPriorityQueue<Integer> occupied;
	private MyPriorityQueue<Character> charQueue;
	Integer[] sample = {5,3,2,1,4};
	Character[] charArray = {'E', 'C', 'B', 'A', 'D'};
	
	@Before
	public void setUp() {
		empty = new MyPriorityQueue<Integer>();
		occupied = new MyPriorityQueue<Integer>(Arrays.asList(sample));
		charQueue = new MyPriorityQueue<Character>(Arrays.asList(charArray));
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testInvalidConstructor() {
		try {
			MyPriorityQueue<Integer> invalid 
			= new MyPriorityQueue<Integer>(null);
			fail("Does not catch NullPointerException");
		}
		catch (NullPointerException e) {
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPeek() {
		assertEquals(new Integer(1), occupied.peek());
		assertEquals(new Character('A'), charQueue.peek());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPop() {
		assertEquals(new Integer(1), occupied.pop());
		assertEquals(new Character('A'), charQueue.pop());
		Integer[] newInt = {2, 3, 4, 5};
		Character[] newChar = {'B', 'C', 'D', 'E'};
		assertEquals(Arrays.asList(newInt), occupied.heap.list);
		assertEquals(Arrays.asList(newChar), charQueue.heap.list);
	}
	
	@Test
	public void testPush() {
		occupied.push(0);
		assertEquals(6, occupied.getLength());
		Integer[] newInt = {0, 3, 1, 5, 4, 2};
		assertEquals(Arrays.asList(newInt), occupied.heap.list);
	}
	
	@Test
	public void testClear() {
		empty.push(0);
		empty.clear();
		assertEquals(0, empty.getLength());
	}
}