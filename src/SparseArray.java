package MySparseArray;


public interface SparseArray 
{
   public Object defaultValue();
   public Object elementAt(int row, int col);
   public ColumnIterator iterateColumns();
   public RowIterator iterateRows();
   public void setValue(int row, int col, Object value);
}