/* TODO: Add your name, login, and ID as specified in the instructions */
import org.junit.*;
import static org.junit.Assert.*;

public class MyHashTableTester {

	private MyHashTable hashTable1;

	@Before
	public void setUp()
	{
		hashTable1 = new MyHashTable(1);
	}
	
	//Test constructor functionality
	@Test
	public void testConstructor()
	{
		assertEquals("Checking constructor initialize correct array length", 
				1, hashTable1.array.length);
		try {
			MyHashTable negativeIndex = new MyHashTable(-1);
			fail("Expected an IllegalArgumentException to be thrown");
		}
		catch (IllegalArgumentException e) {
			assertEquals(e.getClass().getName(),
					"java.lang.IllegalArgumentException");
		}
	}
	
	//Test insert functionality
	@Test
	public void testInsert()
	{
		assertEquals("Checking insert", true, hashTable1.insert("abc"));
		assertEquals("Checking contains after insert", true,
		hashTable1.contains("abc"));
	}

	//Test insert with exception
	@Test
	public void testInsertNullPointerException(){
		try{
			hashTable1.insert(null);
			fail("Expected an NullPointerException to be thrown");
		}catch(NullPointerException e){
			assertEquals(e.getClass().getName(), "java.lang.NullPointerException");
		}
	}

	//Test delete functionality
	@Test
	public void testDelete()
	{
		hashTable1.insert("abc");
		assertEquals("Checking delete", true, hashTable1.delete("abc"));
		assertEquals("Checking contains after delete", false,
		hashTable1.contains("abc"));
	}
	
	//Test getSize functionality
	@Test
	public void testGetSize()
	{
		hashTable1.insert("abc");
		hashTable1.insert("pqr");
		hashTable1.insert("xyz");
		assertEquals("Checking getSize", Integer.valueOf(3),
		Integer.valueOf(hashTable1.getSize()));
	}
	
}
