/**
 * Name: Jiaxin Tang
 * PID: A15812786
 * Email: jit008@ucsd.edu
 * This file includes two generic classes to construct
 * a data structure to achieve the functionality of 
 * binary search tree with key and value
 */

import java.util.ArrayList; //implement ArrayList for functionality of inorder

/**
 * This class is the general tree structure which stores
 * the root of the binary search tree, the size, and
 * corresponding methods
 */
public class MyBST <K extends Comparable<K>, V> {
	
	MyBSTNode<K, V> root; //reference to the root of the tree
	int size; //number of nodes in the tree
	
	/**
	 * This inner class represents the single node of the tree
	 * with its data and connection to other nodes on the tree
	 * as well as proper method to access/mutate the node
	 */
	static class MyBSTNode<K, V> {
		K key; //key of the node, can't be null, can't be duplicated
		V value; //value of the node, can be null and duplicated
		MyBSTNode<K, V> parent; //parent node of the node
		MyBSTNode<K, V> left; //left child node of the node
		MyBSTNode<K, V> right; //right child node of the node
		
		/**
		 * Constructor of MyBSTNode
		 * initialize instance variables as indicated
		 * 
		 * @param key. key of the node.
		 * @param value. value of the node.
		 * @param parent. parent node of the new created node
		 */
		public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}
		
		/**
		 * return the key of the node
		 * 
		 * @return the key of the node
		 */
		public K getKey() {
			return key;
		}
		
		/**
		 * return the value of the node
		 * 
		 * @return the value of the node
		 */
		public V getValue() {
			return value;
		}
		
		/**
		 * return the parent node of the node
		 * 
		 * @return the parent node of the node
		 */
		public MyBSTNode<K, V> getParent() {
			return parent;
		}
		
		/**
		 * return the left child node of the node
		 * 
		 * @return the left child node of the node
		 */
		public MyBSTNode<K, V> getLeft() {
			return left;
		}
		
		
		/**
		 * return the right child node of the node
		 * 
		 * @return the right child node of the node
		 */
		public MyBSTNode<K, V> getRight() {
			return right;
		}
		
		/**
		 * set the key of the node as indicated by newKey
		 * 
		 * @param newKey. new value of the node's key
		 * @return void
		 */
		public void setKey(K newKey) {
			key = newKey;
		}
		
		/**
		 * set the value of the node as indicated by newValue
		 * 
		 * @param newValue. new value of the node's value
		 * @return void
		 */
		public void setValue(V newValue) {
			value = newValue;
		}
		
		/**
		 * set the left child node as indicated by newLeft
		 * 
		 * @param newLeft. new left child of the node
		 * @return void
		 */
		public void setLeft(MyBSTNode<K, V> newLeft) {
			left = newLeft;
		}
		
		/**
		 * set the right child node as indicated by newRight
		 * 
		 * @param newRight. new right child of the node
		 * @return void
		 */
		public void setRight(MyBSTNode<K, V> newRight) {
			right = newRight;
		}
		
		/**
		 * set the parent node as indicated by newParent
		 * 
		 * @param newParent. new parent of the node
		 * @return void
		 */
		public void setParent(MyBSTNode<K, V> newParent) {
			parent = newParent;
		}
		
		/**
		 * return the node with the smallest key larger than current node's 
		 * 
		 * @return the node with the smallest key larger than current node's
		 * null if current node has the largest key in the tree or empty tree
		 */
		public MyBSTNode<K, V> successor() {
			//keep track the node from current node
			MyBSTNode<K, V> currentNode = this;
			// for first priority, search for the smallest key
			// among the right children of the node
			if(currentNode.getRight() != null) {
				//start with right child and find the leftmost one
				currentNode = currentNode.getRight();
				while (currentNode.getLeft() != null) {
					currentNode = currentNode.getLeft();
				}
				return currentNode;
			}
			// if right child does not exist, search upward for a parent node
			// until the current node is the left child of that node
			else {
				while (true) {
					MyBSTNode<K, V> parentNode = currentNode.getParent();
					//if reach root and still not find, return null
					if (parentNode == null) {
						return null;
					}
					else if (parentNode.getLeft() == currentNode) {
						return parentNode;
					}
					else {
						currentNode = parentNode;
					}
				}
			}
		}
	}
	
	/**
	 * Constructor of MyBST
	 * Initialize instance variables to default
	 */
	public MyBST() {
		size = 0;
		root = null;
	}
	
	/**
	 * return the node with the smallest key larger than input node's 
	 * 
	 * @param node. the input node to find for successor
	 * @return return the node with the smallest key larger than current node's 
	 * null if input node has the largest key in the tree or empty tree 
	 */
	protected MyBSTNode<K, V> successor(MyBSTNode<K, V> node) {
		//instantly return null for null input
		if (node == null) {
			return null;
		}
		else {
			//call the same successor method for individual node object
			return node.successor();
		}
	}
	
	/**
	 * return the number of nodes in binary search tree
	 * 
	 * @return the number of nodes in binary search tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * insert a new node to the tree and setup connection references
	 * or replace the value of the node if the same key exists in the tree
	 * 
	 * @param key. the key of the node
	 * @param value. the value of the node
	 * @return the previous value of the node if replace value
	 * or null if there is insertion
	 */
	public V insert(K key, V value) {
		//throw an exception for null key
		if (key == null) {
			throw new NullPointerException();
		}
		else { 
			//if root is null, directly point the new node as root
			if (root == null) {
				root = new MyBSTNode<K, V>(key, value, null);
				size++;
				return null;
			}
			//else, keep traverse the tree to find proper spot
			else {
				//reference to nodes for convenience
				MyBSTNode<K, V> currentNode = root;
				MyBSTNode<K, V> left;
				MyBSTNode<K, V> right;
				while (true) {
					left = currentNode.getLeft();
					right = currentNode.getRight();
					//case when the new key is smaller than current node's
					if (key.compareTo(currentNode.getKey()) < 0) {
						//if left child is null, add to spot and finish
						if (left == null) {
							currentNode.setLeft(new MyBSTNode<K, V>
							(key, value, currentNode));
							size++;
							return null;
						}
						//else, keep looping to left child
						else {
							currentNode = left;
						}
					}
					//case when the new key is greater than current node's
					else if (key.compareTo(currentNode.getKey()) > 0) {
						//if right child is null, add to spot and finish
						if (right == null) {
							currentNode.setRight(new MyBSTNode<K, V>
							(key, value, currentNode));
							size++;
							return null;
						}
						//else, keep looping to right child
						else {
							currentNode = right;
						}
					}
					//case when the new key is equal to current node's
					//replacement needed, return value without size increment
					else {
						V previousValue = currentNode.getValue();
						currentNode.setValue(value);
						return previousValue;
					}
				}	
			}
		}
	}
	
	/**
	 * search for whether a node with the input key exists on the tree
	 * 
	 * @param key. the key to search for
	 * @return the value of the node with input key
	 * or null no node with given key exists on the tree
	 */
	public V search(K key) {
		// if the tree is empty or key is null, directly return null
		if (size == 0) {
			return null;
		}
		else if (key == null) {
			return null;
		}
		//else, traverse down the tree from root
		else {
			//reference to nodes for convenience
			MyBSTNode<K, V> currentNode = root;
			MyBSTNode<K, V> left;
			MyBSTNode<K, V> right;
			while (true) {
				left = currentNode.getLeft();
				right = currentNode.getRight();
				/*
				 * end looping or go to corresponding child node
				 * based on the comparison of current node's key and input key
				 */
				if (currentNode.getKey().compareTo(key) == 0) {
					return currentNode.getValue();
				}
				else if (currentNode.getKey().compareTo(key) > 0) {
					//if reach end without finding the node, return null
					if (left == null) {
						return null;
					}
					//keep traversal
					else {
						currentNode = left;
					}
				}
				else {
					//if reach end without finding the node, return null
					if (right == null) {
						return null;
					}
					//keep traversal
					else {
						currentNode = right;
					}
				}
			}
		}
	}
	
	/**
	 * remove a node from the tree if it exists and fix the structure
	 * 
	 * @param key. the key to remove from the tree
	 * @return the value of the removed node
	 * or null if not such node find and no removal performed
	 */
	public V remove(K key) {
		// if the tree is empty or key is null, directly return null
		if (key == null) {
			return null;
		}
		else if (root == null) {
			return null;
		}
		else {
			//reference to nodes for convenience
			MyBSTNode<K, V> currentNode = root;
			MyBSTNode<K, V> left;
			MyBSTNode<K, V> right;
			MyBSTNode<K, V> parent;
			
			// whether current node is its parent's left child
			Boolean parentPointerLeft = false;
			
			while (true) {
				left = currentNode.getLeft();
				right = currentNode.getRight();
				parent = currentNode.getParent();
				
				if (key.compareTo(currentNode.getKey()) > 0) {
					//if reach end without finding the node, return null
					if (right == null) {
						return null;
					}
					//else, keep traversal, and change pointer direction
					else {
						currentNode = right;
						parentPointerLeft = false;
					}
				}
				else if (key.compareTo(currentNode.getKey()) < 0){
					//if reach end without finding the node, return null
					if (left == null) {
						return null;
					}
					//else, keep traversal, and change pointer direction
					else {
						currentNode = left;
						parentPointerLeft = true;
					}
				}
				/*
				 * the case when node found, break the loop
				 * then current node will be fixed as target node
				 * and other references are associated with current node
				 */
				else {
					break;
				}
			}
			
			//store the value of the node to be removed
			V removedValue = currentNode.getValue();
			
			/*
			 * if the target node is a leaf node, directly set the root
			 * reference or its parent's corresponding reference to null
			 */
			if (left == null && right == null) {
				if (root.getKey().equals(key)) {
					root = null;
				}
				else if (parentPointerLeft) {
					parent.setLeft(null);
				}
				else {
					parent.setRight(null);
				}
			}
			/* 
			 * the two cases when the target node has one child, connect
			 * its parent child with its child depend on direction of both
			 * to remove the node
			 */
			else if (left != null && right == null) {
				if (root.getKey().equals(key)) {
					root = left;
				}
				else if (parentPointerLeft) {
					parent.setLeft(left);
					left.setParent(parent);
				}
				else {
					parent.setRight(left);
					left.setParent(parent);
				}
			}
			else if (left == null && right != null) {
				if (root.getKey().equals(key)) {
					root = right;
				}
				else if (parentPointerLeft) {
					parent.setLeft(right);
					right.setParent(parent);
				}
				else {
					parent.setRight(right);
					right.setParent(parent);
				}
			}
			/*
			 * if the target node has two children, find its successor
			 * as the target node must have successor as a leaf node 
			 * in this case, replace the key and value of target node
			 * to its successor's without changing connection references
			 * and remove its successor node like removing a leaf node
			 */
			else {
				MyBSTNode<K, V> successor = currentNode.successor();
				boolean successorParentPointLeft = false;
				if (successor != root) {
					if (successor.getParent().getLeft()
							.getKey().equals(successor.getKey())) {
						successorParentPointLeft = true;
					}
					else {
						successorParentPointLeft = false;
					}
				}
				if (root.equals(currentNode)) {
					root.setKey(successor.getKey());
					root.setValue(successor.getValue());
					if (successorParentPointLeft) {
						successor.getParent().setLeft(null);
					}
					else {
						successor.getParent().setRight(null);
					}
				}
				else {
					currentNode.setKey(successor.getKey());
					currentNode.setValue(successor.getValue());
					if (successorParentPointLeft) {
						successor.getParent().setLeft(null);
					}
					else {
						successor.getParent().setRight(null);
					}
				}
			}
			size--; //decrement size if successful removal
			return removedValue;
		}
	}
	
	/**
	 * sort all the node in an ArrayList based on ascending ordered keys
	 * 
	 * @return an arrayList with all nodes sorted in ascending ordered keys
	 */
	public ArrayList<MyBSTNode<K, V>> inorder() {
		//initialize ArrayList
		ArrayList<MyBSTNode<K, V>> sorted = new ArrayList<MyBSTNode<K, V>>();
		//for empty tree, directly return an empty list
		if (size == 0) {
			return sorted;
		}
		else {
			//start with the leftmost (smallest) key on the tree
			MyBSTNode<K, V> nextNode = root;
			while (nextNode.getLeft() != null) {
				nextNode = nextNode.getLeft();
			}
			//keep tracking and adding the previous node's successor node
			while (nextNode.successor() != null) {
				sorted.add(nextNode);
				nextNode = nextNode.successor();
			}
			//after looping, add the node with greatest key and return the list
			sorted.add(nextNode);
			return sorted;
		}
	}
}