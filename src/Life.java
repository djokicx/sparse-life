package MySparseArray;

import java.io.IOException;

/**
 * Life class implements Sparse Arrays to simulate game of life.
 * Three instance members (MySparseArray):
 * currentGeneration - the initial generation (boolean)
 * numNeighbors      - the calculation of the neighbors (int)
 * nextGeneration    - base on currentGeneraiton and numNeighbors
 * 					   the next generation is set (boolean
 * @author Dejan Djokic
 *
 */
public class Life {
	private MySparseArray currentGeneration; //boolean
	private MySparseArray numNeighbors; //int
	private MySparseArray nextGeneration; //boolean

	// File initial,String output, int generations
	public Life () {
		this.currentGeneration = new MySparseArray(false);
		this.numNeighbors = new MySparseArray(0);
		this.nextGeneration = new MySparseArray(false);
	}	
	
	/**
	 * Gets the sparse array of neighbors
	 * @return numNeighbors
	 */
	public MySparseArray getNumNeighbors() {
		return numNeighbors;
	}

	/**
	 * Sets the sparse array of neighbors
	 * @param numNeighbors
	 */
	public void setNumNeighbors(MySparseArray numNeighbors) {
		this.numNeighbors = numNeighbors;
	}

	/**
	 * Gets the sparse array of current generation
	 * @return numNeighbors
	 */
	public MySparseArray getCurrentGeneration() {
		return currentGeneration;
	}
	
	/**
	 * Sets the sparse array of current generation
	 * @param currentGeneration
	 */
	public void setCurrentGeneration(MySparseArray currentGeneration) {
		this.currentGeneration = currentGeneration;
	}
	
	/**
	 * Gets the sparse array of next generation
	 * @return nextGeneration
	 */
	public MySparseArray getNextGeneration() {
		return nextGeneration;
	}

	public void setNextGeneration(MySparseArray nextGeneration) {
		this.nextGeneration = nextGeneration;
	}
	
	/**
	 * Sets the initial generation
	 * @see Parser
	 * @param row
	 * @param col
	 * @param value
	 */
	public void setInitialGeneration(int row, int col, Object value) {
		this.currentGeneration.setValue(row, col, value);
	}
	
	public void setNeighbors() {
		RowIterator rowItr = currentGeneration.iterateRows();
		
		while (rowItr.hasNext()) { // Create a row iterator
			ElemIterator elmItr = rowItr.next();
		   
			while (elmItr.hasNext()) { // Create a element iterator
				MatrixElem me = elmItr.next(); // Read the element (it will start with the head)
				for (int i = -1; i < 2; i++) {
					for (int j = -1; j < 2; j++) {
						if (((me.rowIndex() + i > -1)) && ((me.columnIndex() + j) > -1)) {
							if (i == 0 && j == 0) {
								continue;
							}
		    			  
							int currValue = (Integer) numNeighbors.elementAt(me.rowIndex() + i, me.columnIndex() + j);
							numNeighbors.setValue(me.rowIndex() + i, me.columnIndex() + j, (currValue + 1));
						}
					}
				}
		   	}	
		}
	}
	
	public void setNextGeneration() {
		RowIterator rowItr = numNeighbors.iterateRows();
		
		while (rowItr.hasNext()) { // Create a row iterator
			ElemIterator elmItr = rowItr.next();
		   
			while (elmItr.hasNext()) { // Create a element iterator
				MatrixElem me = elmItr.next(); // Read the element (it will start with the head)

				if (currentGeneration.elementAt(me.rowIndex(), me.columnIndex()) != currentGeneration.defaultValue()) {
					if ((Integer)me.value() == 0 || (Integer)me.value() == 1) {
						nextGeneration.setValue(me.rowIndex(), me.columnIndex(), nextGeneration.defaultValue());
					}
					
					else if ((Integer)me.value() == 2 || (Integer)me.value() == 3) {
						nextGeneration.setValue(me.rowIndex(), me.columnIndex(), true);
					}
					
					else if ((Integer)me.value() >= 4) {
						nextGeneration.setValue(me.rowIndex(), me.columnIndex(), nextGeneration.defaultValue());
					}
				}
				
				else {
					if ((Integer)me.value() == 3) {
						nextGeneration.setValue(me.rowIndex(), me.columnIndex(), true);
					}
				}
			}
		}
		
		currentGeneration = nextGeneration;
		nextGeneration = new MySparseArray(false);
		numNeighbors = new MySparseArray(0);
	}	
	
	public void print(MySparseArray array) {
		RowIterator r = array.iterateRows();
		while (r.hasNext())
		{
		   ElemIterator elmItr = r.next();
		   while (elmItr.hasNext())
		   {
		      MatrixElem me = elmItr.next();
		      System.out.print("row:" + me.rowIndex() + 
		                       "col:" + me.columnIndex() + 
		                       "val:" + me.value() + " ");
		      System.out.println();
		   }
		   System.out.println();
		}
	}
	
	/** 
	 * Driver that takes the input file (test.txt), args[0], parses the row/columns of the
	 * file, sets the initial generation and updates the next generation for number of
	 * simulations, args[2], and finally saves it into a .txt file, args[1] 
	 * @param args
	 */
	public static void main(String [ ] args) {
		Life life = new Life();
		
		try {
			Parser.parseFile(args[0], life);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < Integer.parseInt(args[2]); i++) {
			life.setNeighbors();
			life.setNextGeneration();
		}
		
		Parser.writeFile(life.getCurrentGeneration(), args[1]);	
	}
}
		
	