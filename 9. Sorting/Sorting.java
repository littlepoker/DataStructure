/**
 * Name: Jiaxin Tang
 * PID: A15812786
 * Email: jit008@ucsd.edu
 * This file creates a class which contains
 * methods with sorting algorithms to sort 
 * arrays with comparable objects
 * 
 * Source using includes lecture materials, pa instruction, and zybooks
 */
import java.util.Arrays;

/**
 * This class implements three methods which
 * achieve the functionality of insertion and
 * merge sort to sort arrays with comparable objects
 * and print outputs of each iteration
 */

public class Sorting<E extends Comparable<E>> {
	//constants to use
	private static final int CONSTANT_FOUR = 4;
	private static final int CONSTANT_TWO = 2;
	
	/**
	 * Insertion sort algorithm with iteration
	 * print the current array before first iteration and after each iteration
	 * 
	 * @param array. Array to sort
	 * @return void
	 */
	public void insertionSort(E[] array) {
		//throw an exception if array is null or contains null
		if (array == null || Arrays.asList(array).contains(null)) {
			throw new NullPointerException();
		}
		//directly print the array if it has 0 or 1 element, which is sorted
		else if (array.length == 0) {
			System.out.println(Arrays.toString(array));
		}
		else if (array.length == 1) {
			System.out.println(Arrays.toString(array));
		}
		else {
			//print the initial array before sorting
			System.out.println(Arrays.toString(array));
			//start looping on the second element insertion
			for (int i = 1; i < array.length; i++) {
				E testElement = array[i];
				//inner loop checks elements left to current one right to left
				for(int j = i - 1; j >= 0; j--) {
					//if an element is greater than current, shift it to right
					if (array[j] .compareTo(testElement) > 0) {
						array[j + 1] = array[j];
						array[j] = testElement;
					}
					/* 
					 * since element left to current is sorted
					 * once meet an element smaller than current
					 * the current element is in right spot, stop looping
					 */
					else {
						break;
					}
				}
				//print current array
				System.out.println(Arrays.toString(array));
			}
		}
	}
	
	/**
	 * Merge sort algorithm with recursion
	 * recursively divide array into arrays of one element and merge
	 * print the result array of merge after each merge process
	 * 
	 * @param array. Array to sort
	 * @return void
	 */
	public void mergeSort(E[] array) {
		//throw an exception if array is null or contains null
		if (array == null || Arrays.asList(array).contains(null)) {
			throw new NullPointerException();
		}
		//if the array has 0 or 1 elements, stop recursion and do not print
		else if (array.length == 0 || array.length == 1) {
			return;
		}
		else {
			//if the array has at least 4 elements, divide it into 1:3 ratio
			if (array.length >= CONSTANT_FOUR) {
				E[] left = Arrays.copyOfRange(array, 0,
						array.length / CONSTANT_FOUR);
				E[] right = Arrays.copyOfRange(array, 
						array.length / CONSTANT_FOUR, array.length);
				//recursively continue to divide each half
				mergeSort(left);
				mergeSort(right);
				//merge the two parts after separation and print merge result
				merge(array, left, right, left.length, right.length);
				System.out.println(Arrays.toString(array));
			}
			//if the array has 3 elements, divide it in halves
			else if (array.length > CONSTANT_TWO) {
				E[] left = Arrays.copyOfRange(array, 0,
						array.length / CONSTANT_TWO);
				E[] right = Arrays.copyOfRange(array, 
						array.length / CONSTANT_TWO, array.length);
				//only need to divide right half since left has only 1 element
				mergeSort(right);
				//merge the two parts after separation and print merge result
				merge(array, left, right, left.length, right.length);
				System.out.println(Arrays.toString(array));
			}
			//case left for the array with 2 elements
			else {
				E[] left = Arrays.copyOfRange(array, 0,
						array.length / CONSTANT_TWO);
				E[] right = Arrays.copyOfRange(array, 
						array.length / CONSTANT_TWO, array.length);
				//no need to divide again, merge and print directly
				merge(array, left, right, left.length, right.length);
				System.out.println(Arrays.toString(array));
			}
		}
	}
	
	/**
	 * Merge two sorted array into a larger sorted array
	 * 
	 * @param array. The larger sized completion array
	 * @param leftArray. The left smaller sorted array
	 * @param rightArray. The right smaller sorted array
	 * @param left. Length of left array
	 * @param right. Length of right array
	 * @return void
	 */
	public void merge(E[] array, E[] leftArray, E[] rightArray,
			int left, int right) {
		//pointer of position on two arrays
		int leftPos = 0;
		int rightPos = 0;
		//looping until reach the end of one side array
		while (leftPos < left && rightPos < right) {
			//if current left element is smaller, add it to result
			if (leftArray[leftPos].compareTo(rightArray[rightPos]) < 0) {
				array[leftPos + rightPos] = leftArray[leftPos];
				leftPos++; //increment left pointer
			}
			//if current right element is smaller, add it to result
			else {
				array[leftPos + rightPos] = rightArray[rightPos];
				rightPos++; //increment right pointer
			}
		}
		//add elements in the array with elements left to the result
		if (leftPos >= left) {
			for (int i = leftPos + rightPos; i < left + right; i++) {
				array[i] = rightArray[i - leftPos];
			}
		}
		else {
			for (int i = leftPos + rightPos; i < left + right; i++) {
				array[i] = leftArray[i - rightPos];
			}
		}
	}
}
