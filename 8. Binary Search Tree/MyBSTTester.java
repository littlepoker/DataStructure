/**
 * Name: Jiaxin Tang
 * PID: A15812786
 * Email: jit008@ucsd.edu
 * This file implements JUnit tests to test the functionality
 * and correctness of methods in MyBST
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class MyBSTTester {
	MyBST<Integer, String> regular;
	MyBST<Integer, String> left;
	MyBST<Integer, String> right;
	MyBST<Integer, String> root;
	MyBST<Integer, String> empty;
	
	@Before
	public void setUp() {
		regular = new MyBST<Integer, String>();
		left = new MyBST<Integer, String>();
		right = new MyBST<Integer, String>();
		root = new MyBST<Integer, String>();
		empty = new MyBST<Integer, String>();
		
		regular.insert(4, "D");
		regular.insert(2, "B");
		regular.insert(6, "F");
		regular.insert(3, "C");
		regular.insert(7, "G");
		regular.insert(1, "A");
		regular.insert(5, "E");
		
		left.insert(8, "D");
		left.insert(6, "C");
		left.insert(4, "B");
		left.insert(2, "A");
		
		right.insert(1, "A");
		right.insert(3, "B");
		right.insert(5, "C");
		right.insert(7, "D");
		
		root.insert(5, "O");
	}
	
	@Test
	public void testInvalidInsert() {
		try {
			regular.insert(null, "A");
			fail("does not catch exception");
		}
		catch (NullPointerException e) {
			
		}
	}
	
	
	@Test
	public void testOrder() {			
		assertEquals("D", regular.root.getValue());
		assertEquals("B", regular.root.getLeft().getValue());
		assertEquals("F", regular.root.getRight().getValue());
		assertEquals("A", regular.root.getLeft().getLeft().getValue());
		assertEquals("C", regular.root.getLeft().getRight().getValue());
		assertEquals("E", regular.root.getRight().getLeft().getValue());
		assertEquals("G", regular.root.getRight().getRight().getValue());
		assertEquals("F", regular.root.getRight().getRight().
				getParent().getValue());
		
		assertEquals("A", right.root.getValue());
		assertEquals("B", right.root.getRight().getValue());
		assertEquals(null, right.root.getLeft());

		assertEquals("D", left.root.getValue());
		assertEquals("C", left.root.getLeft().getValue());
		assertEquals(null, left.root.getRight());
		
		assertEquals(null, empty.root);
	}
	
	@Test
	public void testSize() {
		assertEquals(0, empty.size());
		assertEquals(4, left.size());
		assertEquals(4, right.size());
		assertEquals(1, root.size());
		assertEquals(7, regular.size());
	}
	
	@Test
	public void testInsert() {
		assertEquals("B", regular.insert(2, "O"));
		assertEquals("O", regular.root.getLeft().getValue());
		assertEquals(null, regular.insert(8, "H"));
		assertEquals("H", regular.root.getRight().getRight()
				.getRight().getValue());
		assertEquals(8, regular.size());
		
		assertEquals(null, empty.insert(1, "A"));
		assertEquals(1, empty.size());
		assertEquals("A", empty.root.getValue());
		
		left.insert(10, "E");
		assertEquals("E", left.root.getRight().getValue());
		assertEquals(5, left.size());
	}
	
	@Test
	public void testSearch() {
		empty.insert(1, null);
		assertEquals("D", right.insert(7, null));
		regular.insert(9, null);
		assertEquals(null, right.search(7));
		assertEquals(null, empty.search(2));
		assertEquals(null, empty.search(1));
		assertEquals("C", right.search(5));
		assertEquals("B", regular.search(2));
		assertEquals(null, regular.search(9));
		assertEquals("O", root.search(5));
		assertEquals(null, regular.search(null));
	}
	
	@Test
	public void testSuccessor() {
		assertEquals("E", regular.successor(regular.root).getValue());
		assertEquals("D", regular.successor(regular.root.getLeft()
				.getRight()).getValue());
		assertEquals("B", right.successor(right.root).getValue());
		assertEquals(null, left.successor(left.root));
		assertEquals(null, empty.successor(empty.root));
		assertEquals(null, root.successor(root.root));
		root.insert(6, "D");
		assertEquals("D", root.successor(root.root).getValue());
	}
	
	@Test
	public void testRemove() {
		assertEquals("C", right.remove(5));
		assertEquals("D", right.root.getRight().getRight().getValue());
		assertEquals(3, right.size());
		
		assertEquals("D", regular.remove(4));
		assertEquals("E", regular.root.getValue());
		assertEquals("B", regular.root.getLeft().getValue());
		assertEquals("F", regular.root.getRight().getValue());
		assertEquals(null, regular.root.getRight().getLeft());
		assertEquals(6, regular.size());
		
		assertEquals("O", root.remove(5));
		assertEquals(0, empty.size());
		assertEquals(null, empty.remove(1));
		assertEquals(null, empty.remove(null));
	}
	
	@Test
	public void testInorder() {
		String[] reg = {"A", "B", "C", "D", "E", "F", "G"};
		assertEquals(reg[0], regular.inorder().get(0).getValue());
		assertEquals(reg[3], regular.inorder().get(3).getValue());
		assertEquals(reg[6], regular.inorder().get(6).getValue());
		assertEquals(7, regular.inorder().size());
		
		assertEquals(0, empty.inorder().size());
		assertEquals("D", left.inorder().get(3).getValue());
		assertEquals("D", right.inorder().get(3).getValue());
	}
}