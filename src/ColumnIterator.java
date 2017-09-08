package MySparseArray;
abstract class ColumnIterator implements java.util.Iterator<ElemIterator>  
{	
    @Override
	public abstract boolean hasNext();
    @Override
	public abstract ElemIterator next();
    @Override
	public void remove()
    {
        throw new UnsupportedOperationException();
    }
    
}