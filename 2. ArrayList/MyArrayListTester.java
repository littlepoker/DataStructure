/** 
 * NAME: Jiaxin Tang
 * ID: A15812786
 * Email: jit008@ucsd.edu
 * 
 * This is a JUnit test file for MyArrayList class
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Arrays;


import org.junit.*;

/**
 * 
 * Class MyArrayListTester test for both valid and
 * invalid inputs to the methods and test if the
 * outputs are the expected
 *
 */

public class MyArrayListTester {

  static final int DEFAULT_CAPACITY = 10;
  static final int MY_CAPACITY = 3;

  Object[] arr = new Object[10];
  Integer[] arrInts = {1,2,3};

  private MyArrayList list1, list2, list3, list4, list5;
  
  @Before
  public void setUp() throws Exception {
    list1 = new MyArrayList();
    list2 = new MyArrayList(DEFAULT_CAPACITY);
    list3 = new MyArrayList(MY_CAPACITY);
    list4 = new MyArrayList(arr);
    list5 = new MyArrayList<Integer>(arrInts);
  }
  
  /**
   * 
   * Test for negative capacity parameter
   *
   */
  @Test
  public void testInvalidConstructor() {
    try {
      MyArrayList<Integer> invalid = new MyArrayList<Integer>(-1);
      fail("Expected IllegalArgumentException");
    } catch (IllegalArgumentException e) {
      // Pass
    }
  }
  
  /**
   * 
   * Test for null array parameter 
   *
   */
  @Test
  public void testNullParameter() {
	  Integer[] nullArray = null;
      MyArrayList<Integer> nullConstructor =
    		  new MyArrayList<Integer>(nullArray);
      assertEquals("Check size for null parameter",
    		  0, nullConstructor.size());
      assertEquals("Check capacity for null parameter", DEFAULT_CAPACITY,
    		  nullConstructor.getCapacity());
  }
  
  /**
   * 
   * Test for the default size of valid parameter
   *
   */
  @Test
  public void testDefaultSize() {
    assertEquals("Check size for default constructor", 0, list1.size());
    assertEquals("Check size for constructor with given capacity of 10",
    		0, list2.size());
    assertEquals("Check size for constructor with given capacity of 3",
    		0, list3.size());
    assertEquals("Check size for constructor with given array",
    		10, list4.size());
    assertEquals("Check size for constructor with given int array",
    		3, list5.size());
  }
  
  /**
   * 
   * Test for default capacity of valid parameter
   *
   */
  @Test
  public void testInitialCapacity() {
    assertEquals("Check default capacity", DEFAULT_CAPACITY,
    		list1.getCapacity());
    assertEquals("Check given capacity", MY_CAPACITY, list3.getCapacity());
  }
  
  /**
   * 
   * Test for negative capacity input
   *
   */
  @Test
  public void testCheckCapacity() {
    list3.checkCapacity(3);
    assertEquals("Check that capacity is unchanged", 3, list3.getCapacity());
    list3.checkCapacity(4);
    assertEquals("Check that capacity is doubled", 6, list3.getCapacity());
    list3.checkCapacity(20);
    assertEquals("Check that capacity is modified", 20, list3.getCapacity());
  }
  
  /**
   * 
   * Test for correct append
   *
   */
  @Test
  public void testAppend() {
    int[] nums = {2,4};
    list1.append(nums[0]);
    assertEquals("Check that append increments size", 1, list1.size());
    list1.append(nums[1]);
    assertEquals("Check that capacity is unchanged",
    		DEFAULT_CAPACITY, list1.getCapacity());
  }
  
  /**
   * 
   * Test for correct prepend
   *
   */
  @Test
  public void testPrepend() {
    int[] nums = {2,4};
    list1.prepend(nums[0]);
    assertEquals("Check that append increments size", 1, list1.size());
    list1.prepend(nums[1]);
    assertEquals("Check that capacity is unchanged",
    		DEFAULT_CAPACITY, list1.getCapacity());
  }  
  
  /**
   * 
   * Test for valid and one case of invalid insert
   *
   */
  @Test
  public void testInsert() {
    list5.insert(3, 4);
    assertEquals("Check that append increments size", 4, list5.size());
    try {
    	list5.insert(-1, 4);
    	fail("Expected IndexOutOfBoundsException");
    }
    catch (IndexOutOfBoundsException e) {
    	// pass
    }
  }
  
  /**
   * 
   * Test for another invalid insert
   *
   */
  @Test
  public void testInvalidGreaterInsert() {
    try {
    	list5.insert(4, 4);
    	fail("Expected IndexOutOfBoundsException");
    }
    catch (IndexOutOfBoundsException e) {
    	// pass
    }
  }
  
  /**
   * 
   * Test for valid and one case of invalid get
   *
   */
  @Test
  public void testGet() {
    assertEquals("Check that get correct element for valid index", 2, list5.get(1));
    try {
    	list5.get(-1);
    	fail("Expected IndexOutOfBoundsException");
    }
    catch (IndexOutOfBoundsException e) {
    	// pass
    }
  }
 
  /**
   * 
   * Test for another invalid insert
   *
   */
  @Test
  public void testInvalidGreaterGet() {
	 try {
		 list5.get(3);
		 fail("Expected IndexOutOfBoundsException");
	 }
	 catch (IndexOutOfBoundsException e) {
	    	// pass
	 }
  }

  /**
   * 
   * Test for valid and one case of invalid set
   *
   */
  @Test
  public void testSet() {
	  assertEquals("Check that set correct element for valid index", 2, list5.set(1, 5));
	  try {
	  	list5.set(-1, 5);
	  	fail("Expected IndexOutOfBoundsException");
	  }
	  catch (IndexOutOfBoundsException e) {
	  	// pass
	  }
  }
	
  /**
   * 
   * Test for another invalid insert
   *
   */
  @Test
  public void testInvalidGreaterSet() {
	  try {
		  list5.set(3, 5);
		  fail("Expected IndexOutOfBoundsException");
	  }
	  catch (IndexOutOfBoundsException e) {
		    	// pass
		 }
  }

  /**
   * 
   * Test for valid and one case of invalid remove
   *
   */
  @Test
  public void testRemove() {
	  assertEquals("Check that remove correct element for valid index", 2, list5.remove(1));
	  try {
		  list5.remove(-1);
		  fail("Expected IndexOutOfBoundsException");
	  }
	  catch (IndexOutOfBoundsException e) {
	  	// pass
	  }
  }
	
  /**
   * 
   * Test for another invalid insert
   *
   */
  @Test
  public void testInvalidGreaterRemove() {
	  try {
		  list5.remove(3);
		  fail("Expected IndexOutOfBoundsException");
	  }
	  catch (IndexOutOfBoundsException e) {
		  // pass
	  }
  }

  /**
   * 
   * Test for changed and unchanged trimToSize
   *
   */
  @Test
  public void testTrimToSize() {
	  list1.trimToSize();
	  assertEquals("Check that the capacity decreases", list1.size(), list1.getCapacity());
	  list5.trimToSize();
	  assertEquals("Check that the capacity unchanged", 3, list5.getCapacity());
  }
}

