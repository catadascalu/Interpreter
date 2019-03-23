package util;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import exception.MyException;

public class MySymTable<T1, T2> implements ISymTable<T1, T2>{
	HashMap<T1, T2> table;
	
	public MySymTable() {
		table = new HashMap<T1, T2>();
	}
	@Override
	public void clean() {
		this.table.clear();
	}
	@Override
	public boolean find(T1 name) {
		for(Map.Entry<T1, T2> e: table.entrySet()) {
			if(e.getKey().equals(name))
				return true;
		}
		return false;
	}

	@Override
	public int getValueT(T1 name) {
		for(Map.Entry<T1, T2> e: table.entrySet()) {
			if(e.getKey().equals(name))
				return (int) e.getValue();
		}
		return 0;
	}
	
	@Override
	public void setValueT(T1 name, T2 var) {
		this.table.put(name, var);	
	}

	@Override
	public void add(T1 name, T2 var) {
		this.table.put(name, var);
	}

	@Override
	public boolean isDefined(String id) {
		return table.get(id) != null;
		
	}

	@Override
	public boolean contains(T1 v) {
		return table.get(v) != null;
	}

	@Override
	public void update(T1 v1, T2 v2) {
		this.table.put(v1, v2);
	}
	
	@Override
	public String toString() {
		String dictString = "";
		if(this.table.size() == 0)
			return dictString + " empty.";
		for(HashMap.Entry<T1, T2> e : table.entrySet()) {
			dictString = dictString + e.getKey().toString() + " --> " + e.getValue().toString() + "     " + "\n";
		}
		return dictString;
	}

	@Override
	public T2 lookUp(T1 id) throws MyException {
		if(table.get(id) != null) {
			return table.get(id);
		}
		
		throw new MyException("Value does not exist.\n");
	}

	@Override
	public int size() {
		return this.table.size();
	}
	
	@Override
	public HashMap<T1, T2> getContent(){
		return this.table;
	}
	
	@Override
	public ISymTable<T1, T2> copy(){
		ISymTable<T1, T2> newTable = new MySymTable<T1, T2>();
		for(Entry<T1, T2> e : this.table.entrySet()) {
			T1 key = e.getKey();
			T2 value = e.getValue();
			newTable.add(key, value);
		}
		return newTable;
	}
}

	

	


