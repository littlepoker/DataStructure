/*
 * Name: Jiaxin Tang
 * PID: A15812786
 * Email: jit008@ucsd.edu
 * This file includes a class to create a data structure
 * similar to HashTable and implements its functionality 
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * This class implements CRC variant as hash functions to
 * hash Strings into data structure and implements functionality
 * similar to HashTable 
 */
public class MyHashTable implements MyHashTableInterface {
	//Constant used to double the size and do addition
	final static int CONSTANT_TWO = 2;
	
	//Other constants for computation
	final static int CONSTANT_FIVE = 5;
	final static int CONSTANT_TWENTY_SEVEN = 27;
	final static int CRC_CONSTANT = 0xf8000000;
	final static int CONSTANT_ONE = 1;
	final static double LOAD_FACTOR_MULTIPLIER = (double) 3 / 2;

	LinkedList<String>[] array;//Array that stores linkedlists
	int nelems;  //Number of element stored in the hash table
	int expand;  //Number of times that the table has been expanded
	int collision;  //Number of collisions since last expansion
	String statsFileName;     //FilePath for the file to write statistics
	//upon every rehash
	boolean printStats = false;   //Boolean to decide whether to write
	//stats to file or not after rehashing

	//Feel free to add more :)
	
	/** 
	 * Constructor of the data structure
	 *   
	 * @param size The number of initial bucket of the structure
	 */
	@SuppressWarnings( "unchecked" )
	public MyHashTable(int size) {
		// Throw an exception for negative size
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		else {
			// Initialize instance variables to indicated or default
			array = new LinkedList[size];
			nelems = 0;
			expand = 0;
			collision = 0;
		}		
	}

	/** 
	 * Constructor of the data structure
	 *   
	 * @param size The number of initial bucket of the structure
	 * @param fileName The name of file to store statistics
	 */
	@SuppressWarnings( "unchecked" )
	public MyHashTable(int size, String fileName){
		// Throw an exception for negative size
		if (size < 0) {
			throw new IllegalArgumentException();
		}
		// Throw an exception for null file name
		else if (fileName == null) {
			throw new NullPointerException();
		}
		else {
			// Initialize instance variables to indicated or default
			array = new LinkedList[size];
			nelems = 0;
			expand = 0;
			collision = 0;
			statsFileName = fileName;
			printStats = true; // Eligible to print stats with filename
		}
	}

	/** 
	 * Insert an non-duplicated element to the data structure.
	 *   
	 * @param value The element to be inserted
	 * @return true if no duplicated existed element, false otherwise
	 */
	@Override
	public boolean insert(String value) {
		// Throw an exception if value is null
		if (value == null) {
			throw new NullPointerException();
		}
		// no insertion if the structure already contains value 
		else if (contains(value)) {
				return false;
		}
		else {
			// check if load factor reach 2/3 after insertion, rehash if so
			if ((nelems + CONSTANT_ONE) * 
					LOAD_FACTOR_MULTIPLIER >= array.length) {
				//print stats if able before each rehash
				if (printStats) {
					printStatistics();
				}
				rehash();
			}
			//insert new element and increment size
			int insertionPosition = hashString(value);
			if (array[insertionPosition] == null) {
				array[insertionPosition] = new LinkedList<String>();
				array[insertionPosition].add(value);
			}
			else {
				array[insertionPosition].add(value);
				collision++; //increment collision if bucket is occupied
			}
			nelems++;
			return true;
		}
	}

	/** 
	 * Remove specified element from the data structure
	 *   
	 * @param value The element to be deleted
	 * @return true if deleted completely, false if no such element exists
	 */
	@Override
	public boolean delete(String value) {
		// Throw an exception if value is null
		if (value == null) {
			throw new NullPointerException();
		}
		// False to remove if such element doesn't exist
		else if (!contains(value)) {
			return false;
		}
		else {
			int bucket = hashString(value);
			array[bucket].remove(value);
			// If bucket is empty after removal, clear it as null
			if (array[bucket].size() == 0) {
				array[bucket] = null;
			}
			nelems--;
			return true;
		}
	}

	/** 
	 * Determine if the data structure contains specified element
	 *   
	 * @param value The element to be searched
	 * @return true if such element exists, false otherwise
	 */
	@Override
	public boolean contains(String value) {
		// Throw an exception if value is null
		if (value == null) {
			throw new NullPointerException();
		}
		else {
			int bucket = hashString(value);
			return array[bucket] != null && array[bucket].contains(value);
		}
	}

	/** 
	 * Print the elements of each bucket of the data structure
	 * As a single row
	 *   
	 * @return void
	 */
	@Override
	public void printTable() {
		// Double loop to print each element in each bucket
		for (int i = 0; i < array.length; i++) {
			if (array[i] == null) {
				System.out.println(i + ":");
			}
			else {
				System.out.print(i + ": ");
				for (int j = 0; j < array[i].size() - CONSTANT_ONE;j++) {
					System.out.print(array[i].get(j) + ", ");
				}
				System.out.println(array[i].getLast());
			}
		}
	}

	/** 
	 * Return number of elements in the data structure
	 *   
	 * @return number of elements in the data structure
	 */
	@Override
	public int getSize() {
		return nelems;
	}

	/** 
	 * Expand the capacity of the array and reorganize positions of elements
	 *   
	 * @return void
	 */
	@Override
	@SuppressWarnings( "unchecked" )
	public void rehash() {
		// Create a larger prime-sized array
		LinkedList<String>[] newArray = new LinkedList[primeGen()];
		// Store existing element in a temporary array
		String[] rehashing = new String[getSize()];
		int index = 0;
		for (LinkedList<String> list : array) {
			if (list != null) {
				Iterator<String> iter = list.iterator();
				while (iter.hasNext()) {
						rehashing[index] = iter.next();
						index++;
				}
			}
		}
		array = newArray;
		// rehash and insert all elements stored in temporary array
		for (String rehashingString : rehashing) {
			insert(rehashingString);
		}
		expand++;
		collision = 0; //reset collision after expansion
	}

	/**
	* Generate the hash value of a given string by CRC variant
	* @param str the string value
	* @return the hash value
	*/
	public int hashString(String str) {
		int h = 0;
		for (int i = 0; i < str.length(); i++) {
			int highOrder = h & CRC_CONSTANT;
			h = h << CONSTANT_FIVE;
			h = h ^ (highOrder >>> CONSTANT_TWENTY_SEVEN);
			h = h ^ (int)str.charAt(i);
		}
		return Math.abs(h) % array.length; 
	}

	/**
	* Print statistics to the given file.
	* @return True if successfully printed statistics, false if the file
	*         could not be opened/created.
	*/
	@Override
	public boolean printStatistics(){
		PrintStream out;
		try {
			out = new PrintStream( new FileOutputStream( this.statsFileName,
			true ) );
		} catch(FileNotFoundException e) {
			return false;
		}
		out.print(this.expand + " resizes, ");//Print resize times
		//Calculate the load factor
		double loadFactor = ( (double) nelems / array.length );
		DecimalFormat df = new DecimalFormat("#.##"); //Print the load factor
		out.print("load factor " + df.format( loadFactor ) + ", ");
		out.print(this.collision + " collisions, "); //Print collision times
		int length = 0;
		for(int i = 0; i < this.array.length; i++){
			if(this.array[i] != null && this.array[i].size() > length)
			length = this.array[i].size();
		}
		//Print the length of the longest chain
		out.println(length + " longest chain");
		out.close();
		return true;
	}

	/**
	* Generate a prime number that is close to the double of current array
	* size
	* @return a prime number used as array size
	*/
	private int primeGen(){
		boolean isPrime = false;
		int num = array.length*CONSTANT_TWO;//Double the size

		/*
		* Generate next prime number that is greater than the double of
		* current array size
		*/
		while(!isPrime){
			num++;
			/*
			* Try divides the number with all numbers greater than two and
			* less than or equal to the square root of itself
			*/
			for(int divisor = CONSTANT_TWO; divisor <= Math.sqrt(num);
			divisor++){
				if(num % divisor == 0)//The number is divisible
				break;//No need for further testing, break inner loop
				if(divisor == (int)Math.sqrt(num))//The number is indivisible
				isPrime = true;//Then it is a prime
			}
		}
		return num;
	}

}
