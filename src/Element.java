package MySparseArray;

/**
 * Sparse Array Element class that manipulates the Sparse Array element
 * @author Dejan Djokic
 *
 */
public class Element implements MatrixElem{
	
	private int columnIndex;
	private Object value;
	private Element right;
	private Element below;
	private int rowIndex;
	
	// Initial constructor will have null right and below in most cases; they will be set later
	public Element(int rowIndex, int columnIndex, Object value) {
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.value = value;
		this.right = null;
		this.below = null;	
	}

	/**
	 * Returns the index of element's column
	 */
	@Override
	public int columnIndex() {
		return columnIndex;
	}
	
	/**
	 * Returns the below element in the sparse array
	 */
	public Element getBelow() {
		return below;
	}
	
	/**
	 * Returns the element to the right in the sparse array
	 */
	public Element getRight() {
		return right;
	}
	
	/**
	 * Returns the index of element's row
	 */
	public int getRowIndex() {
		return rowIndex;
	}
	
	/**
	 * Returns the value stored in the element
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Returns the index of element's row
	 */
	@Override
	public int rowIndex() {
		return getRowIndex();
	}

	/**
	 * Sets the below element in the sparse array
	 * @param below
	 */
	public void setBelow(Element below) {
		this.below = below;
	}
	
	/**
	 * Sets the column's index
	 * @param columnIndex
	 */
	public void setColumnIndex(int columnIndex) {
		this.columnIndex = columnIndex;
	}
	
	/**
	 * Sets the element to the right in the sparse array
	 * @param right
	 */
	public void setRight(Element right) {
		this.right = right;
	}
	
	/**
	 * Sets the row's index
	 * @param rowIndex
	 */
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * Sets the value of the element
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	/**
	 * Returns the value stored in the element
	 */
	@Override
	public Object value() {
		return getValue();
	}

	@Override
	public String toString() {
		return "Element [rowIndex=" + rowIndex + "," + 
				         " columnIndex=" + columnIndex + "," + 
				         " value=" + value + "]";
	}
	
	
}
