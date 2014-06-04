package eg.edu.guc.dbms.classes;

import java.util.Vector;

public class Transaction {
	// Transaction class runs in its own thread to execute 
	// a sequence of steps. 
	// A transaction object is not reusable, i.e. every incoming SQL 
	// statement will result in the creation of a new transaction 
	// object. 
	// A transaction is responsible for calling the BufferManager 
	// to read and write pages and also responsible for calling the 
	// LogManager to record the steps being executed. 
	// The execute method starts the thread associated with 
	// the Transaction to run the steps. 
	// When a transaction ends, make sure to clear all it’s 
	// attributes by setting them to null. This will help Java 
	// garbage collector to identify those objects as being unused 
	// and removes them from memory faster. 
	 
	// methods 
	public void init( BufferManager bufManager, 
	 LogManager logManager, 
	 Vector<Step> vSteps){
		
	}
	 
	public void execute( ){
		
	}

}