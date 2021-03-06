package eg.edu.guc.dbms.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import eg.edu.guc.dbms.exceptions.DBEngineException;
import eg.edu.guc.dbms.interfaces.Command;
import eg.edu.guc.dbms.pages.Page;
import eg.edu.guc.dbms.utils.CSVReader;
import eg.edu.guc.dbms.utils.Properties;
import eg.edu.guc.dbms.utils.btrees.BTreeAdopter;
import eg.edu.guc.dbms.utils.btrees.BTreeFactory;

public class DeleteCommand implements Command {
	String strTableName;
	Hashtable<String, String> htblColNameValue;
	String strOperator;
	CSVReader reader;
	BTreeFactory btfactory;
	Properties properties;
	SelectCommand select;
	Page page;
	boolean update;
	
	public DeleteCommand(boolean update, SelectCommand select,
			String strTableName, Hashtable<String, String> htblColNameValue,
			String strOperator, CSVReader reader, Properties properties,
			BTreeFactory btfactory, Page page) {
		this.strTableName = strTableName;
		this.htblColNameValue = htblColNameValue;
		this.strOperator = strOperator;
		this.reader = reader;
		this.properties = properties;
		this.btfactory = btfactory;
		this.page = page;
		if (update)
			this.select = select;
		else
			select = new SelectCommand(btfactory, this.reader, this.properties,
					this.strTableName, this.htblColNameValue, this.strOperator,
					page);
		this.update = update;
	}

	public void execute() throws DBEngineException {
		if(!update)
			select.execute();
		this.deleteFromTable();
		// try {
		// this.deleteFromTree();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	public void deleteFromTable() throws DBEngineException {
		ArrayList<String> pointers = select.getResultPointers();
		for (int i = 0; i < pointers.size(); i++) {
			int rowNumber = Integer.parseInt(pointers.get(i));
			select.page.deleteTuples(rowNumber);
		}
	}

	public void deleteFromTree() throws DBEngineException, IOException {
		ArrayList<String> indexedColumns = properties
				.getIndexedColumns(strTableName);
		ArrayList<String> pointers = select.getResultPointers();
		ArrayList<Hashtable<String, String>> results = select.getResults();
		for (int i = 0; i < indexedColumns.size(); i++) {
			BTreeAdopter adoptor = btfactory.getBtree(strTableName,
					indexedColumns.get(i));
			for (int j = 0; j < pointers.size(); j++) {
				adoptor.delete(results.get(j).get(indexedColumns.get(i)),
						pointers.get(j));
			}
		}
	}

}
