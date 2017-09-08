package MySparseArray;
import java.util.NoSuchElementException;

	/**
	 * RowColumnElement is a either Node in a column, or a Node in a row (both LinkedList)
	 * It contains its index, pointer to the next element, and pointer to the first of its
	 * row or column element in the Sparse Array
	 * @author Dejan Djokic
	 *
	 */
	public class RowColumnElement {
		private Element first;
		private int index;	
		private RowColumnElement next;
	
		public RowColumnElement(int index) {
			this.index = index;
			this.next = null;
			this.first = null;
		}
		
		/**
		 * Adds an element to the column
		 * @param elem
		 */
		public void addToColumn(Element elem) {		
			// Checks if any Matrix element exists at all, if not add it as first
			if (getFirst() == null) {
				setFirst(elem);
			}
			
			/*
			 * Else, checks if newly added element is smaller than the first element
			 * If it is, the first element goes down in the list, and the new element becomes first
			 */
			else if (elem.getRowIndex() < first.getRowIndex()) {
				elem.setBelow(first);
				setFirst(elem);
			}
			
			/*
			 * Iterates through the Linked List
			 * First element and its latter element are starting points
			 * While current element is not null, if newly added element is smaller than the current one
			 * break out of the loop, otherwise move forward by one
			 * If a bigger element is found, it goes down in the list, and the new element takes it's spot
			 */
			else {
	            Element current = first.getBelow();
	            Element previous = first;
	            
	            while (current != null) {
	            	if (elem.getRowIndex() < current.getRowIndex()) {
	            		break;
	            	}
	            	
	            	previous = current;
	            	current = current.getBelow();
	            }
	            
	            elem.setBelow(previous.getBelow());
	            previous.setBelow(elem);
			}
		}
		
		/**
		 * Adds an element to the row
		 * @param elem
		 */
		public void addToRow(Element elem) {
			// Checks if any Matrix element exists at all, if not add it as first
			if (getFirst() == null) {
				setFirst(elem);
			}			
			/*
			 * Else, checks if newly added element is smaller than the first element
			 * If it is, the first element goes down in the list, and the new element becomes first
			 */
			else if (elem.columnIndex() < first.columnIndex()) {
				elem.setRight(first);
				setFirst(elem);
			}			
			/*
			 * Iterates through the Linked List
			 * First element and its latter element are starting points
			 * While current element is not null, if newly added element is smaller than the current one
			 * break out of the loop, otherwise move forward by one
			 * If a bigger element is found, it goes down in the list, and the new element takes it's spot
			 */
			else {
	            Element current = first.getRight();
	            Element previous = first;
	            
	            while (current != null) {
	            	if (elem.columnIndex() < current.columnIndex()) {
	            		break;
	            	}
	            	
	            	previous = current;
	            	current = current.getRight();
	            }
	            
	            elem.setRight(previous.getRight());
	            previous.setRight(elem);
			}
		}
		
		/**
		 * Gets the element in row, by comparing column numbers. It does not matter whether
		 * it is done through the row or the column as they are both pointing to the same
		 * element
		 * @param col
		 * @return Element
		 */
		public Element getElement(int col) {	
			Element current = first;
			
			while (current != null) {
				if (col == current.columnIndex()) {
					break;
				}
				
				current = current.getRight();
			}
			
			return current;
		}
		
		/**
		 * Checks whether the Sparse Array contains a specific element
		 * @param col
		 * @return boolean
		 */
		public boolean containsElement(int col) {
			Element current = first;
			
			while (current != null) {
				if (col == current.columnIndex()) {
					return true;
				}
				
				current = current.getRight();
			}
			
			return false;
		}	
		
		/**
		 * Gets the very first element in the Sparse Array that the row or column 
		 * is pointing to
		 * @return Element
		 */
		public Element getFirst() {
			return first;
		}
		
		/**
		 * Gets the index of the row or column element
		 * @return
		 */
		public int getIndex() {
			return index;
		}
		
		/**
		 * Gets the next row or column element
		 * @return
		 */
		public RowColumnElement getNext() {
				return next;
			}
		
		/**
		 * Checks whether the row or column, whose head is this element, is empty
		 * @return boolean
		 */
		public boolean isEmpty() {
			return (first == null);
		}
		
		/**
		 * Removes an element from the column
		 * @param row
		 * @param col
		 */
		public void removeFromColumn(int row, int col) {
			if (first.getRowIndex() == row && first.columnIndex() == col) {
				setFirst(first.getBelow());
			}
			
			else {
				Element temp = first;
				while (temp.getBelow() != null) {
					if (temp.getBelow().getRowIndex() == row && temp.getBelow().columnIndex() == col) {
						temp.setBelow(temp.getBelow().getBelow());
						break;
					}
					else {
						temp = temp.getBelow();
					}			
				}
			}
		}
		
		/**
		 * Removes an element from the row
		 * @param row
		 * @param col
		 */
		public void removeFromRow(int row, int col) {
			// Special case if it is at the head
			if (first.getRowIndex() == row && first.columnIndex() == col) {
				setFirst(first.getRight());
			}
			
			else {
				Element temp = first;
				while (temp.getRight() != null) {
					if (temp.getRight().getRowIndex() == row && temp.getRight().columnIndex() == col) {
						temp.setRight(temp.getRight().getRight());
						break;
					}
					else {
						temp = temp.getRight();
					}			
				}
			}
		}
		
		/**
		 * Sets the first element in the Sparse Array that the row or column
		 * is pointing to
		 * @param first
		 */
		public void setFirst(Element first) {
			this.first = first;
		}
		
		/**
		 * Sets the index of the row or column
		 * @param index
		 */
		public void setIndex(int index) {
			this.index = index;
		}
		
		/**
		 * Sets the next row or column element
		 * @param next
		 */
		public void setNext(RowColumnElement next) {
			this.next = next;
		}
		
		/**
		 * Element Iterator Class
		 * @author Dejan Djoki
		 *
		 */
		public class Iterator extends ElemIterator{
	
			boolean col;
			private Element current;
			boolean row;
			
			public Iterator(String string) {
		        current = getFirst();
		        
		        if (string.equals("row")) {
		        	row = true;
		        }
		        
		        else if (string.equals("col")) {
		        	col = true;
		        }
		        
		        else {
		        	throw new IllegalArgumentException();
		        }
		     }
			
			@Override
			public boolean hasNext() {
				return current != null;
			}
	
			@Override
			public boolean iteratingCol() {
				return col;
			}
	
			@Override
			public boolean iteratingRow() {
				return row;
			}
	
			@Override
			public MatrixElem next() {
				Element returnValue;
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				else if (row) {
					returnValue = current;
					current = current.getRight();
					return returnValue;
				}
				else {
					returnValue = current;
					current = current.getBelow();
					return returnValue;
				}
			}
	
	//		ElemIterator returnvalue = current. new Iterator("col");
	//		current = current.getNext();
	//		return returnvalue;
			@Override
			public int nonIteratingIndex() {
				if (row) {
					return current.getRowIndex();
				}	
				else {
					return current.columnIndex();
				}
			}
	}
	}