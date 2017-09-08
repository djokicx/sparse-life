package MySparseArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Simple parser class that takes in the input .txt file and passes it to a Sparse Array, and
 * writes out a .txt file from a Sparse Array
 * @author Dejan Djokic
 *
 */
public class Parser {
	
	/**
	 * Reads in the file, splits it by a comma value, and passes each line to a Sparse Array
	 * for row and column valuse
	 * @param initial
	 * @param life
	 * @throws IOException
	 */
	public static void parseFile(String initial, Life life) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(initial));
		   
		    try {
		        String line = br.readLine();
	
		        while (line != null) {
		        	String data[] = line.split(",");
		        	life.setInitialGeneration(Integer.parseInt(data[0]), Integer.parseInt(data[1]), true);
		        	line = br.readLine();
		        }
		    }
		    finally {
		        br.close();
		    }		
		}
	
	public static void writeFile(MySparseArray current, String output) {		
		BufferedWriter writer = null;

		try {
		    writer = new BufferedWriter(new OutputStreamWriter(
		          new FileOutputStream(output), "utf-8"));		    
		    RowIterator rowItr = current.iterateRows();
		    
		    while (rowItr.hasNext()) {		    
		       ElemIterator elmItr = rowItr.next();
		       
		       while (elmItr.hasNext()) {
		          MatrixElem me = elmItr.next();	
		          writer.write(me.rowIndex() + "," + me.columnIndex());
		          writer.newLine();
		       }
		    } 
		} 
		catch (IOException ex) {
		} 
		finally {
		   try {
			   writer.close();
		   } 
		   catch (Exception ex) {}
		}
	}
}
