import org.junit.*;	
import static org.junit.Assert.*;	
import java.util.LinkedList;	
import java.util.ListIterator;	


/**	
 *  Title: class MyLinkedListTester	
 *  @author Jiaxin Tang	
 *  @version 3.0 05-April-2015	
 *  Student ID: A15812786	
 *  CSE12 Account: cs12sp20bbw
 *  Date: 4/20/2020	
 *	
 *  Description: Implementing various JUnit test to test the sanity of
 *  linked list data structure	
 * */	

/**
 * 
 * This class implements testing for the sanity of linked
 * list data structure and iterator 
 *
 */
public class MyLinkedListTester	
{	
	private MyLinkedList<Integer> empty ;	
	private MyLinkedList<Integer> one ;	
	private MyLinkedList<Integer> several ;	
	private MyLinkedList<String>  slist ;	
	static final int DIM = 5;	
	static final int FIBMAX = 30;	
	private ListIterator<Integer> iterTest;	

	/**	
	 * Standard Test Fixture. An empty list, a list with one entry (0) and 	
	 * a list with several entries (0,1,2)	
	 */ 	
	@Before	
	public void setUp()	
	{	
		empty = new MyLinkedList<Integer>();	
		one = new MyLinkedList<Integer>();	
		one.add(0,new Integer(0));	
		several = new MyLinkedList<Integer>() ;	
		// List: 1,2,3,...,Dim	
		for (int i = DIM; i > 0; i--)	
			several.add(0,new Integer(i));	

		// List: "First","Last"	
		slist = new MyLinkedList<String>();	
		slist.add(0,"First");	
		slist.add(1,"Last");	
	}	

	/** Test if first node of the lists are correct */	
	@Test	
	public void testGetFirst()	
	{	
		assertEquals("Check 0",new Integer(0),one.get(0));	
		assertEquals("Check 0",new Integer(1),several.get(0));
	}	

	/** Test if size of lists are correct */	
	@Test	
	public void testListSize()	
	{	
		assertEquals("Check Empty Size",0,empty.size()) ;	
		assertEquals("Check One Size",1,one.size()) ;	
		assertEquals("Check Several Size",DIM,several.size()) ;	
	}	

	/** Test setting a specific entry */	
	@Test	
	public void testSet()	
	{	
		slist.set(1,"Final");	
		assertEquals("Setting specific value", "Final",slist.get(1));	
	}	

	/** Test isEmpty */	
	@Test	
	public void testEmpty()	
	{	
		assertTrue("empty is empty",empty.isEmpty()) ;	
		assertTrue("one is not empty",!one.isEmpty()) ;	
		assertTrue("several is not empty",!several.isEmpty()) ;	
	}	

	/** Test out of bounds exception on get */	
	@Test	
	public void testGetException()	
	{	
		try 	
		{	
			empty.get(0);	
			// This is how you can test when an exception is supposed 	
			// to be thrown	
			fail("Should have generated an exception");  	
		}	
		catch(IndexOutOfBoundsException e)	
		{	
			//  normal	
		}	
	}	

	/** Test iterator on empty list and several list */	
	@Test	
	public void testIterator()	
	{	
		int counter = 0 ;	
		ListIterator<Integer> iter;	
		for (iter = empty.listIterator() ; iter.hasNext(); )	
		{	
			fail("Iterating empty list and found element") ;	
		}	
		counter = 0 ;	
		for (iter = several.listIterator() ; iter.hasNext(); iter.next())	
			counter++;	
		assertEquals("Iterator several count", counter, DIM);	
	}	
	
	/* Add your own tests here */	

	//////////////////////////////////////////	
	//Begin testing on List Iterator methods//	
	/////////////////////////////////////////	


	/** Test listiterator hasnext method while it goes through the empty	
	 * and one list	
	 */	
	@Test 	
	public void testIteratorHasNext() {	

		ListIterator<Integer> iter = empty.listIterator();	
		ListIterator<Integer> iter1 = one.listIterator();	

		assertTrue(!iter.hasNext());	
		assertTrue(iter1.hasNext());							
	}	

	/** Test listiterator next method */	
	@Test	
	public void testIteratorNext() {	

		iterTest = several.listIterator();	

		assertEquals(new Integer(1),iterTest.next());	
		assertEquals(new Integer(2),iterTest.next());	
		assertEquals(new Integer(3),iterTest.next());	
		assertEquals(new Integer(4),iterTest.next());	
		assertEquals(new Integer(5),iterTest.next());
		
		//Test the interaction of remove to next and previous methods
		iterTest.remove();
		assertEquals(new Integer(4),iterTest.previous());
		assertEquals(new Integer(3),iterTest.previous());
		
		//Test the interaction of set to next and previous methods
		iterTest.set(5);
		
		//Test back and forth next and previous
		assertEquals(new Integer(5),iterTest.next());
		assertEquals(new Integer(5),iterTest.previous());
		assertEquals(new Integer(2),iterTest.previous());
		assertEquals(new Integer(1),iterTest.previous());
	}	
	
	/** Test nextIndex method of list iterator */	
	@Test	
	public void testIteratorNextIndex() {	
		iterTest = several.listIterator();	

		//Test the nextIndex method at the start of and end 	
		//of the list as well as middle of the list	
		assertEquals(0, iterTest.nextIndex());	

		iterTest.next();	
		iterTest.next();	
		iterTest.next();	

		assertEquals(3, iterTest.nextIndex());			

		iterTest.next();	
		iterTest.next();	

		assertEquals(5, iterTest.nextIndex());	
	}	

	/** Test the remove method of list iterator */	
	@SuppressWarnings("deprecation")
	@Test 	
	public void testIteratorRemove() {	
		iterTest = several.listIterator(); 	

		//Test whether removes method work after next method		
		iterTest.next();	
		iterTest.next();	
		iterTest.remove();	

		assertEquals(new Integer(1), iterTest.previous());	

		//Test whether remove method work after previous method	
		iterTest.next();	
		iterTest.next();	
		iterTest.previous();	
		iterTest.remove();	

		assertEquals(new Integer(4), iterTest.next());	
	} 			
	
	/* Add your own tests here */	

}
