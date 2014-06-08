package eg.edu.guc.dbms.steps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;
import eg.edu.guc.dbms.exceptions.DBEngineException;
import eg.edu.guc.dbms.pages.PageID;
import eg.edu.guc.dbms.utils.Properties;
import eg.edu.guc.dbms.utils.btrees.BTreeAdopter;
import eg.edu.guc.dbms.utils.btrees.BTreeFactory;

public class PageRead extends Step {

	private Properties properties;
	private BTreeFactory btfactory;
	private Hashtable<String, String> htblColNameValue;
	private ArrayList<PageID> pages;
	
	public PageRead() {

	}
	
	public void execute() {
		Set<String> keys = htblColNameValue.keySet();
		pages = new ArrayList<PageID>();
		for (String key : keys) {
			if (properties.isIndexed(this.tableName, key)) {

				BTreeAdopter tree = null;
				try {
					tree = btfactory.getBtree(this.tableName, key);

				} catch (DBEngineException e) {
				}
				try {
					ArrayList<String> pointers = (ArrayList<String>) tree
							.find(htblColNameValue.get(key));
					for (String s : pointers) {

						PageID pgID = new PageID(s);
						pages.add(pgID);

					}
				} catch (IOException e) {
				}
			}

		}
	}
}