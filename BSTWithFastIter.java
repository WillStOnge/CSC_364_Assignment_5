import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * Filename: BSTWithFastIter.java
 * <p>
 * Description: BST with a more efficient iterator utilizing a stack. Overrides iterator method to return the faster iterator define as an anonymous inner class.
 * 
 * @author Will St. Onge
 *
 */

public class BSTWithFastIter<E extends Comparable<E>> extends BST<E> {
	
	@Override
	public Iterator<E> iterator() {
		return new FastIterator();
	}

	private class FastIterator implements Iterator<E> {
		private Stack<TreeNode<E>> stack = new Stack<>();
		private TreeNode<E> current = root;
		private E lastReturned = null;
		
		public FastIterator() {}

		@Override
		public boolean hasNext() {
			if(current != null || !stack.isEmpty())
				return true;
			return false;
		}

		@Override
		public E next() {
			if(!hasNext())
				throw new NoSuchElementException();
			while(current != null) {
				stack.push(current);
				current = current.left;
			}
			TreeNode<E> node = stack.pop();
			lastReturned = node.element;
			current = node.right;
			return lastReturned;
		}
		
		@Override
		public void remove() {
			if(lastReturned == null)
				throw new IllegalStateException();
			delete(lastReturned);
			lastReturned = null;
		}
	}
}