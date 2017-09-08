package MySparseArray;
import java.util.NoSuchElementException;

	/**
	 * RowColumnList is a Linked List. In the context of a sparse array it is the 'frame'.
	 * Row ColumnList makes outside rows and columns, but not the actual sparse array
	 * element.
	 * @author Dejan Djokic
	 *
	 */
	public class RowColumnList {
		
		private RowColumnElement head;
		
		// Initially, sets the head to null for an empty Linked List
		public RowColumnList() {
			this.head = null;
		}
		
		/**
		 * If the row / column contains the index, return true, otherwise false
		 * @param index
		 * @return
		 */
		public boolean contains(int index) {
			RowColumnElement current = head; //keep track of current node
			
		    while (current != null) {
		        if (index == current.getIndex()){
		            return true;
		        }
		        
		        current = current.getNext();
		    }
		    
		    return false;
		}
		
		/**
		 * Gets the head of the RowColumnList
		 * @return head
		 */
		public RowColumnElement getHead() {
			return head;
		}
		
		/**
		 * Sets the head of the RowColumnList
		 * @param head
		 */
		public void setHead(RowColumnElement head) {
			this.head = head;
		}
	   
		/**
		 * Gets row or column based on the index
		 * @return element at the index
		 */
		public RowColumnElement getRowColumn(int index) {
			RowColumnElement current = head; //keep track of current node
			
		    while (current != null) {
		        if (index == current.getIndex()){
		            break;
		        }
		        
		        current = current.getNext();
		    }
		    
		    return current;
		}
		
		/**
		 * Inserts a row or column element into the list
		 * @param elem
		 */
		public void insert(RowColumnElement elem) {		
			if (head == null) {
				head = elem;
			}
			
			else if (elem.getIndex() < head.getIndex()) {
				elem.setNext(head);
				head = elem;
			}
			
			
			else {
	            RowColumnElement current = head.getNext();
	            RowColumnElement previous = head;
	            
	            while (current != null) {
	            	if (elem.getIndex() < current.getIndex()) {
	            		break;
	            	}
	            	previous = current;
	            	current = current.getNext();
	            }
	            
	            elem.setNext(previous.getNext());
	            previous.setNext(elem);
			}
		}
		
		/**
		 * Checks if the list is empty
		 * @return boolean
		 */
		public boolean isEmpty()
		{ 
			return (head == null); 
		}
	
		/**
		 * Removes a row/column at a specific index
		 * @param index
		 */
		public void removeAtIndex (int index) {
			
			if (head.getIndex() == index) {
				setHead(head.getNext());
			}
			
			else {
				RowColumnElement temp = head;
				
				while (head.getNext() != null) {
					if (temp.getNext().getIndex() == index) {
						temp.setNext(temp.getNext().getNext());
						break;
					}
					else {
						temp = temp.getNext();
					}			
				}
			}
		}
		
		/**
		 * Column Iterator
		 * @author Dejan Djokic
		 *
		 */
		public class MatrixColumnIterator extends ColumnIterator{
	
			private RowColumnElement current;
			
			public MatrixColumnIterator()
		     {
		        current = getHead();
		     }
			 
			@Override
			public boolean hasNext() {
				return current != null;
			}
		
			@Override
			public ElemIterator next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				
				ElemIterator returnvalue = current. new Iterator("col");
				current = current.getNext();
				return returnvalue;
			}
		}
		
		/**
		 * Row Iterator
		 * @author Dejan Djokic
		 *
		 */
		public class MatrixRowIterator extends RowIterator{
	
			private RowColumnElement current;
			
			public MatrixRowIterator()
		    {
		        current = getHead();
		    }
			 
			@Override
			public boolean hasNext() {
				return current != null;
			}
		
			@Override
			public ElemIterator next() {
				if (!hasNext()) {
					throw new NoSuchElementException();
				}
				
				ElemIterator returnvalue = current. new Iterator("row");
				current = current.getNext();
				return returnvalue;
			}
		}
	}