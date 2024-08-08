/**
 * Name: Jiaxin Tang
 * PID:A15812786
 * Email: jit008@ucsd.edu
 * This file is the testing class of BinaryTree
 * which uses JUnit test to test the correctness and
 * functionality of methods for BinaryTree
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class BinaryTreeTester {
	Integer[] int1= {1, 2, 3, 4, 5};
	Integer[] int2 = {1, 2, 3, 4, 5, 6};
	BinaryTree<Integer> empty;
	BinaryTree<Integer> element;
	BinaryTree<Integer> list1;
	BinaryTree<Integer> list2;
	
	@Before
	public void setUp() {
		ArrayList<Integer> al1 = new ArrayList<Integer>(Arrays.asList(int1));
		ArrayList<Integer> al2 = new ArrayList<Integer>(Arrays.asList(int2));
		empty = new BinaryTree<Integer>();
		element = new BinaryTree<Integer>(1);
		list1 = new BinaryTree<Integer>(al1);
		list2 = new BinaryTree<Integer>(al2);
	}
	
	@Test
	public void testAddException() {
		try {
			list1.add(null);
			fail("does not catch exception");
		}
		catch (NullPointerException e) {
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testAdd() {
		assertEquals(new Integer(1), list1.root.getData());
		assertEquals(new Integer(2), list1.root.getLeft().getData());
		assertEquals(new Integer(3), list1.root.getRight().getData());
		assertEquals(new Integer(4), list1.root.getLeft().getLeft().getData());
		empty.add(1);
		assertEquals(1, empty.getSize());
		assertEquals(new Integer(1), empty.root.getData());
		list1.add(6);
		assertEquals(new Integer(6), list1.root.getRight().getLeft().getData());
		empty.add(2);
		empty.add(3);
		empty.add(4);
		assertEquals(new Integer(4), empty.root.getLeft().getLeft().getData());
	}
	
	@Test
	public void testGetHeight() {
		assertEquals(2, list1.getHeight());
		assertEquals(2, list2.getHeight());
		assertEquals(0, element.getHeight());
		assertEquals(0, empty.getHeight());
		element.add(2);
		assertEquals(1, element.getHeight());
		list2.add(7);
		list2.add(8);
		assertEquals(3, list2.getHeight());
	}
	
	@Test
	public void testContainsBFS() {
		assertEquals(true, element.containsBFS(1));
		assertEquals(true, list1.containsBFS(1));
		assertEquals(true, list1.containsBFS(5));
		assertEquals(false, list1.containsBFS(6));
		assertEquals(false, empty.containsBFS(2));
		assertEquals(true, list2.containsBFS(6));
		assertEquals(false, list2.containsBFS(7));
	}
	
	@Test
	public void testGetSize() {
		assertEquals(5, list1.getSize());
		assertEquals(6, list2.getSize());
		assertEquals(1, element.getSize());
		assertEquals(0, empty.getSize());
		element.add(2);
		assertEquals(2, element.getSize());
		list2.remove(6);
		assertEquals(5, list2.getSize());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testRemove() {
		assertEquals(false, list2.remove(7));
		assertEquals(true, list2.remove(1));
		assertEquals(new Integer(6), list2.root.getData());
		assertEquals(5, list2.getSize());
		assertEquals(true, list1.remove(2));
		assertEquals(new Integer(5), list1.root.getLeft().getData());
		assertEquals(new Integer(1), list1.root.getData());
		assertEquals(new Integer(3), list1.root.getRight().getData());
		assertEquals(new Integer(4), list1.root.getLeft().getLeft().getData());
		list1.remove(1);
		assertEquals(new Integer(4), list1.root.getData());
		assertEquals(3, list1.getSize());
		assertEquals(1, list1.getHeight());
		assertEquals(true, element.remove(1));
		assertEquals(0, element.getHeight());
		assertEquals(0, element.getSize());
		assertEquals(false, empty.remove(1));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testMinValue() {
		assertEquals(new Integer(1), list1.minValue());
		assertEquals(new Integer(1), list2.minValue());
		list1.add(0);
		list2.remove(1);
		assertEquals(new Integer(0), list1.minValue());
		assertEquals(new Integer(2), list2.minValue());
		assertEquals(new Integer(1), element.minValue());
		assertEquals(null, empty.minValue());
		empty.add(1);
		element.add(0);
		assertEquals(new Integer(0), element.minValue());
		assertEquals(new Integer(1), empty.minValue());
	}
}