/**
 * Name: Jiaxin Tang
 * PID:A15812786
 * Email: jit008@ucsd.edu
 * This file contains two classes two achieve the
 * functionality of a binary tree, the inner class
 * is used to express the single node object of the tree
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This outer class uses single node and its left and right child
 * to represent the relationship of elements in binary tree
 * and implements necessary methods for the tree
 */
public class BinaryTree <E extends Comparable <E>>{
	private static final int CONSTANT_TWO = 2;
	
	Node root; //the root (top) node of the tree
	int size; //number of elements in the tree
	
	/**
	 * This is the inner class of BinaryTree which store the
	 * methods and instance variable of individual node to
	 * support the functionality of BinaryTree
	 */
	protected class Node {
		Node right; //This node's left child
		Node left; //This node's right child
		E data; //the value stored in the node
		
		/** 
		 * Constructor of Node class
		 * initialize node with given data
		 * 
		 * @param data. Data contained of the node
		 */
		public Node(E data) {
			this.data = data;
			//initialize the new node's both child as null
			right = null;
			left = null;
		}
		
		/** 
		 * Set the left child node of the current node as given
		 *   
		 * @param left. The indicated left child node
		 * @return void
		 */
		public void setLeft(Node left) {
			this.left = left;
		}
		
		/** 
		 * Set the right child node of the current node as given
		 *   
		 * @param right. The indicated right child node
		 * @return void
		 */
		public void setRight(Node right) {
			this.right = right;
		}
		
		/** 
		 * Set the data of the current node as given
		 *   
		 * @param data. The new data of the node
		 * @return void
		 */
		public void setData(E data) {
			this.data = data;
		}
		
		/** 
		 * return the left child of the current node
		 *   
		 * @return the current node's right child, null if no right child
		 */
		public Node getLeft() {
			return left;
		}
		
		/** 
		 * return the right of the current node
		 *   
		 * @return the current node's right child, null if no right child
		 */
		public Node getRight() {
			return right;
		}
		
		/** 
		 * return the data of the current node
		 *   
		 * @return the current node's contained data
		 */
		public E getData() {
			return data;
		}
	}
	
	/** 
	 * Constructor of Node class with no parameter
	 * initialize the tree as an empty tree  
	 * 
	 */
	public BinaryTree() {
		root = null; //the reference to the top (root) node of the tree
		size = 0; //number of elements (nodes) currently in the tree
	}
	
	/** 
	 * Constructor of Node class with a data parameter
	 * initialize the tree as a tree with only one root node
	 * with value given by data
	 * 
	 * @param data. The data of the initial root node
	 */
	public BinaryTree(E data) {
		root = new Node(data);
		size = 1;
	}
	
	/** 
	 * Constructor of Node class with a list parameter
	 * initialize the tree as a tree which add each element
	 * in the list as an individual node respectively
	 * 
	 * @param list. The list of elements to add to the tree
	 */
	public BinaryTree(List<E> list) {
		size = 0;
		for (E element : list) {
			add(element);
		}
	}
	
	/** 
	 * Add an element to the next empty spot and maintain completeness
	 * 
	 * @param element. The element to be added to the tree
	 * @return void
	 */
	public void add(E element) {
		//throw an exception is element is null
		if (element == null) {
			throw new NullPointerException();
		}
		else {
			Node newNode = new Node(element);
			//if node is empty, just directly add to head
			if (root == null) {
				root = newNode;
			}
			//case with successful removal
			else {
				boolean inserted = false;
				//use a queue to start traversal from root 
				Queue<Node> queue = new LinkedList<Node>();
				queue.add(root);
				/*
				 * Based on queue's FIFO property, polling the head node
				 * add its left and right child to the tail could
				 * act as the traversal of level order from top to bottom,
				 * left to right.
				 */
				while (!inserted) {
					//Start from root node of binary tree
					Node currentNode = queue.poll();
					// null left pointer means end of the tree and add to left
					if (currentNode.getLeft() == null) {
						currentNode.setLeft(newNode);
						inserted = true; //stop looping
					}
					// if left pointer is not null but right is for given node
					// add it as the right child pointer of given node
					else if (currentNode.getRight() == null) {
						currentNode.setRight(newNode);
						inserted = true; //stop looping
					}
					// else, add child nodes and continue looping
					else {
						queue.add(currentNode.getLeft());
						queue.add(currentNode.getRight());
					}
				}
			}
			size++; //increment size
		}
	}
	
	/** 
	 * remove the first occurrence of given element in the tree
	 * and adjust the tree to maintain completeness property
	 * 
	 * @param element. The element to be removed
	 * @return true if removal is successful, false otherwise (not found)
	 */
	public boolean remove(E element) {
		//throw an exception if element is null
		if (element == null) {
			throw new NullPointerException();
		}
		//if target not in the tree, directly return false
		else if (!containsBFS(element)) { 
			return false;
		}
		else {
			//Queue to perform traversal
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(root); //start from root node
			
			//indicate the parent of target node need to be removed
			Node parentOfTarget = null;
			Node parentOfLast = null; //indicate parent of last node
			boolean rootAsTarget = false; // whether target node is the root
			
			// whether the parent node's left child is the target
			// true means left, false means right
			boolean targetPointerLeft = false; 
			
			// whether the parent node's left child is the last node
			// true means left, false means right
			boolean lastPointerLeft = false;
			int count = 1; //count how many nodes have been visited
			
			//Case: If root is the target to remove, directly return false
			if (root.getData() == element) {
				//update relevant variable as information
				parentOfTarget = root;
				rootAsTarget = true;
			}
			
			/*
			 * remove traversal is slightly different than others
			 * in addition to find the target, we also need to find
			 * the last node of the tree, then we need to always traverse
			 * down the tree and update both information
			 */
			while (count < getSize()) {
				//remove previous node and update the reference
				Node currentNode = queue.poll();
				Node left = currentNode.getLeft();
				Node right = currentNode.getRight();
				
				if (left.getData().equals(element)) {
					//update matching target only for the first occurrence
					if (parentOfTarget == null) {
						parentOfTarget = currentNode;
						targetPointerLeft = true;
					}
				}
				
				//finish looping: the tree ends with last node as left child 
				if (right == null) {
					parentOfLast = currentNode;
					lastPointerLeft = true;
					break;
				}
				//if not the end of tree, add new child and continue traversal
				else {
					if (right.getData().equals(element)) {
						//update matching target only for the first occurrence
						if (parentOfTarget == null) {
							parentOfTarget = currentNode;
							targetPointerLeft = false;
						}
					}
					queue.add(left);
					count++;
					queue.add(right);
					count++;
				}
				
				//finish looping: the tree ends with last node as right child
				if (count == getSize()) {
					parentOfLast = currentNode;
					lastPointerLeft = false;
				}
				
			}
			//for single-element tree to remove root, clearly the tree
			if (getSize() == 1) {
				root = null;
			}
			
			/*
			 * Assign the value of the last node to the node positioned
			 * planned for removal, and set the position of last node as null
			 * based on different cases indicated by the informative booleans
			 */
			else if (rootAsTarget) {
				if (lastPointerLeft) {
					root.setData(parentOfLast.getLeft().getData());
					parentOfLast.setLeft(null);
				}
				else {
					root.setData(parentOfLast.getRight().getData());
					parentOfLast.setRight(null);
				}
			}
			else if (targetPointerLeft) {
				if (lastPointerLeft) {
					parentOfTarget.getLeft()
					.setData(parentOfLast.getLeft().getData());
					parentOfLast.setLeft(null);
				}
				else {
					parentOfTarget.getLeft()
					.setData(parentOfLast.getRight().getData());
					parentOfLast.setRight(null);
				}
			}
			else {
				if (lastPointerLeft) {
					parentOfTarget.getRight()
					.setData(parentOfLast.getLeft().getData());
					parentOfLast.setLeft(null);
				}
				else {
					parentOfTarget.getRight()
					.setData(parentOfLast.getRight().getData());
					parentOfLast.setRight(null);
				}
			}
			size--; //decrement in size;
			return true;
		}
	}
	
	/** 
	 * check if an element exists in the tree
	 * 
	 * @param element. The element searching for
	 * @return true if element exists, false otherwise
	 */
	public boolean containsBFS(E element) {
		if (element == null) {
			throw new NullPointerException();
		}
		//automatically return false is tree is empty
		if (getSize() == 0) {
			return false;
		}
		else {
			//check root is the element seek
			if (root.getData().equals(element)) {
				return true;
			}
			else {
				//if not, start traversal from root by a queue
				Queue<Node> queue = new LinkedList<Node>();
				queue.add(root);
				while (true) {
					Node currentNode = queue.poll();
					Node left = currentNode.getLeft();
					Node right = currentNode.getRight();
					//two cases when reach the end of tree
					if (left == null) {
						return false;
					}
					else if (right == null) {
						return left.getData().equals(element);
					}
					//two cases when found the element
					else if (left.getData().equals(element)) {
						return true;
					}
					else if (right.getData().equals(element)) {
						return true;
					}
					//else, continue to traverse by adding more child to queue
					else {
						queue.add(currentNode.getLeft());
						queue.add(currentNode.getRight());
					}
				}
			}
		}
	}
	
	/** 
	 * return the current level the current binary tree reaches
	 * empty tree counts as level 0, while the root level is also level 0  
	 * 
	 * @return the level of the last node (highest level) of the tree
	 */
	public int getHeight() {
		if (getSize() == 0) {
			return 0;
		}
		else {
			return (int)(Math.log(getSize()) / Math.log(CONSTANT_TWO));
		}
	}
	
	/** 
	 * return the number of elements currently in the tree
	 * 
	 * @return number of elements in the tree
	 */
	public int getSize() {
		return size;
	}
	
	/** 
	 * search for the minimum value in the binary tree
	 * 
	 * @return the minimum value among nodes in the tree, null if empty tree
	 */
	public E minValue() {
		//return null if the tree is empty
		if (getSize() == 0) {
			return null;
		}
		else {
			//traverse the whole tree by a queue from root
			Queue<Node> queue = new LinkedList<Node>();
			queue.add(root);
			int count = 1;
			//set root's value as the initial value to compare
			E minValue = root.getData();
			while (count < size) {
				Node currentNode = queue.poll();
				Node left = currentNode.getLeft();
				Node right = currentNode.getRight();
				//check if left child's value is smaller than current smallest
				if (left.getData().compareTo(minValue) < 0 ) {
					minValue = left.getData();
					}
				//case for end of tree, for this loop, the case when
				//left child is null never happens, so only check right node
				if (right == null) {
					break;
				}
				//if not reach end, continue to add nodes and traverse
				else {
					queue.add(currentNode.getLeft());
					count++;
					queue.add(currentNode.getRight());
					count++;
					// compare smaller right value only if it's not null
					if (right.getData().compareTo(minValue) <	0) {
						minValue = right.getData();
					}
				}
			}
			return minValue;
		}
	}
}