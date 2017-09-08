package MySparseArray;


public class MySparseArray implements SparseArray {
	public RowColumnList columns;
	private Object dValue;
	public RowColumnList rows;
	
	// Constructor if the default value is not given
	public MySparseArray () {
		this.dValue = null;
		this.rows = new RowColumnList();
		this.columns = new RowColumnList();
	}
	
	// Constructor if the default value is given
	public MySparseArray (Object dValue) {
		this.dValue = dValue;
		this.rows = new RowColumnList();
		this.columns = new RowColumnList();
	}
	
	/**
	 * Adds an element to both the row and the column
	 * @param elem
	 */
	public void addElement(Element elem) {
		rows.getRowColumn(elem.getRowIndex()).addToRow(elem);
		columns.getRowColumn(elem.columnIndex()).addToColumn(elem);
	}
	
	/**
	 * Returns the default value
	 */
	@Override
	public Object defaultValue() {
		return dValue;
	}
	
	/**
	 * Returns the element at a specific row and column
	 */
	@Override
	public Object elementAt(int row, int col) {
		if (rows.contains(row) && columns.contains(col) && rows.getRowColumn(row).containsElement(col)) {
			return rows.getRowColumn(row).getElement(col).getValue();
		}	
		else {
			return dValue;
		}
	}
	
	/**
	 * Returns the column iterator
	 */
	@Override
	public ColumnIterator iterateColumns() {
		return columns.new MatrixColumnIterator();
	}
	
	/**
	 * Returns the row iterator
	 */
	@Override
	public RowIterator iterateRows() {
		return rows.new MatrixRowIterator();
	}
	
	/**
	 * Sets the value of an element within the sparse array
	 */
	@Override
	//TODO: Check your if statements!
	public void setValue(int row, int col, Object value) {
		
		// If the element already exists (if there is row and col), change it's value
		// If the value is default, remove the element
		// If the row is empty, remove it
		// If the column is empty, remove it
		if (rows.contains(row) && columns.contains(col) && rows.getRowColumn(row).containsElement(col)) {
			// Check if the value is equal to default value (if yes -> remove)
			if (value.equals(dValue)) {
				rows.getRowColumn(row).removeFromRow(row, col);
				columns.getRowColumn(col).removeFromColumn(row, col);
				
				if (rows.getRowColumn(row).isEmpty()) {
					rows.removeAtIndex(row);
				}
				
				if (columns.getRowColumn(col).isEmpty()) {
					columns.removeAtIndex(col);
				}
			}			
			else {
				rows.getRowColumn(row).getElement(col).setValue(value);	
			}
		}		
		else {
			if (!value.equals(dValue)) {
				if (!rows.contains(row) ) {
					rows.insert(new RowColumnElement(row));
				}
				
				if (!columns.contains(col)) {
					columns.insert(new RowColumnElement(col));
				}
				
				addElement(new Element(row, col, value));
			}
		}
	}
}
