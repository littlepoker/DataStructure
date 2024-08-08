/**
 * Class MyArrayList. construct a data structure
 * similar to java's own ArrayList.
 * 
 * NAME: Jiaxin Tang
 * ID: A15812786
 * Email: jit008@ucsd.edu
 */

/**
 * This class implements MyList interface and
 * use generic to build a data structure similar
 * to ArrayList with its main methods and properties
 */
public class MyArrayList<E> implements MyList<E> {
	
	Object[] data; // Store elements of data structure
	int size;	// Number of valid elements
	
	// Default capacity of data structure
	private static final int DEFAULT_CAPACITY = 10;
	
	private static final int DOUBLE_FACTOR = 2;
	
	// Default number of valid elements
	private static final int INITIAL_SIZE = 0;	
	
	/**
	 * Constructor of MyArrayList class
	 * 
	 * Initialize size and array capacity with default value
	 */
	public MyArrayList () {
		size = INITIAL_SIZE;
		data = new Object[DEFAULT_CAPACITY];
	}
	
	/**
	 * Constructor of MyArrayList class
	 * 
	 * @param initialCapacity - user specified initial capacity of data array
	 * Initialize size with default value 
	 * and data array with user specified capacity 
	 */
	public MyArrayList (int initialCapacity) {
		if (initialCapacity < INITIAL_SIZE) {
			throw new IllegalArgumentException();
		}
		else {
			size = INITIAL_SIZE;
			data = new Object[initialCapacity];
		}
	}
	
	/**
	 * Constructor of MyArrayList class
	 * 
	 * @param arr - array of initial elements
	 * Initialize size and capacity based on arr 
	 */
	public MyArrayList (E[] arr) {
		// If arr is null, refer to no-parameter constructor
		if (arr == null) {
			size = INITIAL_SIZE;
			data = new Object[DEFAULT_CAPACITY];
		}
		else {
			size = arr.length;
			data = arr;
		}
	}
	
	/**
     * checks whether capacity is full
     * expands capacity if necessary
     * does not change size and order of elements
     *
     * @param  requiredCapacity - capacity required for data array
     * @return void
     */
	public void checkCapacity(int requiredCapacity) {
		if (getCapacity() < requiredCapacity) {
			if (getCapacity() == 0) {
				data = new Object[Math.max(DEFAULT_CAPACITY,
						requiredCapacity)];
			}
			else {
				// Copy all elements in data array in a larger array
				Object[] tempData = new Object[Math.max(getCapacity() * 
						DOUBLE_FACTOR, requiredCapacity)];
				for (int i = 0; i < size; i++) {
					tempData[i] = data[i];
				}
				data = tempData; //assign data to the larger new array
			}
		}
	}
	
	/**
     * gets current maximum capacity of data array
     *
     * @return length of data array
     */
	public int getCapacity() {
		return data.length;
	}
	
	/**
     * inserts an element at specified index
     *
     * @param  index - index of the data array
     * @param  element - element to be inserted
     * @return void
     */
	public void insert(int index, E element) {
		// Throw an IndexOutOfBoundsException if invalid index
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			// Expand capacity if already full
			if (size == getCapacity()) {
				checkCapacity(size + 1);
			}
			// Move all elements at and after index one space afterward   
			for (int i = size; i > index; i--) {
				data[i] = data[i - 1];
			}
			data[index] = (Object) element;
			size++; // Increment size
		}	
	}
	
	/**
     * appends an element to the end of data array
     *
     * @param  element - element to be appended
     * @return void
     */
	public void append(E element) {
		// Expand Capacity if already full
		if (size == getCapacity()) {
			checkCapacity(size + 1);
		}
		data[size] = (Object) element;
		size++; // Increment size
	}
	
	/**
     * prepends an element to the beginning of data array
     *
     * @param  element - element to be prepended
     * @return void
     */
	public void prepend(E element) {
		// Expand Capacity if already full
		if (size == getCapacity()) {
			checkCapacity(size + 1);
		}
		// Move all existing elements one space afterward
		for (int i = size; i > 0; i--) {
			data[i] = data[i - 1];
		}
		data[0] = (Object) element;
		size++;	// Increment size	
	}
	
	/**
     * sets an element at certain index as a new element
     *
     * @param  index - index of the data array
     * @param  element - new element
     * @return previous element at index of data array
     */	
	@SuppressWarnings("unchecked") // Down casting from Object to E
	public E set(int index, E element) {
		// Throw an IndexOutOfBoundsException if invalid index
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		else {
			E replacedItem = (E) data[index];
			data[index] = (Object) element;
			return replacedItem; 
		}
	}
	
	/**
     * gets an element at certain index of data array
     *
     * @param  index - index of the data array
     * @return element at index of data array
     */	
	@SuppressWarnings("unchecked") // Down casting from Object to E
	public E get(int index) {
		// Throw an IndexOutOfBoundsException if invalid index
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		else {
			return (E) data[index]; 
		}
	}
	
	/**
     * removes an element at certain index of data array
     *
     * @param  index - index of the data array
     * @return element being removed
     */	
	@SuppressWarnings("unchecked") // Down casting from Object to E
	public E remove(int index) {
		// Throw an IndexOutOfBoundsException if invalid index
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		else {
			E removedItem = (E) data[index];
			// Move all elements at and after index one space afterward
			for (int i = index; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			size--; // Decrement size
			return removedItem; 
		}
	}
	
	/**
     * gets size of ArrayList
     *
     * @return number of valid elements in data array
     */	
	public int size() {
		return size;
	}
	
	/**
     * trims the size of data array to number of valid elements
     *
     * @return void
     */	
	public void trimToSize() {
		if (getCapacity() > size) {
			// Copy all elements in data array in a smaller array			
			Object[] tempData = new Object[size];
			for (int i = 0; i < size; i++) {
				tempData[i] = data[i];
			}
			data = tempData; //assign data to the smaller new array
		}
	}
}