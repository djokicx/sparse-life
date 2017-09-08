package MySparseArray;
abstract class ElemIterator implements java.util.Iterator<MatrixElem>
{
    @Override
	public abstract boolean hasNext();
    public abstract boolean iteratingCol();
    public abstract boolean iteratingRow();
    @Override
	public abstract MatrixElem next();
    public abstract int nonIteratingIndex();
    @Override
	public void remove()
    {
        throw new UnsupportedOperationException();
    }
}