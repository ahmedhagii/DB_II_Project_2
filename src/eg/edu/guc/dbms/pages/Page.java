package eg.edu.guc.dbms.pages;

import java.util.ArrayList;
import java.util.Hashtable;

public class Page {
	
	String tableName;
	ArrayList<Hashtable<String,String>> tuples = new ArrayList<Hashtable<String,String>>();
	
	public Page() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Hashtable<String,String>> getTuples() {
		return tuples;
	}

	public void setTuples(ArrayList<Hashtable<String,String>> tuples) {
		this.tuples = tuples;
	}

}