package eg.edu.guc.dbms.steps;

import java.util.Hashtable;

import eg.edu.guc.dbms.commands.SelectCommand;
import eg.edu.guc.dbms.exceptions.DBEngineException;
import eg.edu.guc.dbms.pages.Page;
import eg.edu.guc.dbms.utils.CSVReader;
import eg.edu.guc.dbms.utils.Properties;
import eg.edu.guc.dbms.utils.btrees.BTreeFactory;

public class Select extends Step {

	private BTreeFactory btfactory;
	private CSVReader reader;
	private String strOperator;
	private Properties prop;
	
	public Select(BTreeFactory btfactory, CSVReader reader,
			Properties properties, String tableName,
			Hashtable<String, String> htblColNameValue, String strOperator) {
			super(tableName, htblColNameValue);
			this.btfactory =btfactory;
			this.reader = reader;
			this.htblColNameValue = htblColNameValue;
			this.tableName = tableName;
			this.strOperator = strOperator;
			prop = properties;
		// TODO Auto-generated constructor stub
	}

	public void execute(Page page) {
		SelectCommand select = new SelectCommand(btfactory, reader, prop, strOperator, htblColNameValue, strOperator, page);
		try {
			select.execute();
		} catch (DBEngineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		select.getResults();
		System.out.println(select.getResults().size());
		for(Hashtable<String, String> h : select.getResults())
			System.out.println(h);
	}

}
