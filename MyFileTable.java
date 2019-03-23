package util;
import model.Pair;
import java.util.HashMap;
import java.util.Map;

import exception.MyException;

public class MyFileTable<Integer, Pair> implements IFileTable<Integer, Pair>{
	HashMap<Integer, Pair> fileTable;
	int descriptor;
	public MyFileTable() {
		fileTable = new HashMap<Integer, Pair>();
		this.descriptor = 0;
	}
	
	@Override
	public void clean() {
		this.fileTable.clear();
	}
	@Override
	
	public int getDescriptor() {
		return this.descriptor;
	}
	@Override
	public void setDescriptor(int d) {
		this.descriptor = d;
	}
	@Override
	public void add(Integer fd, Pair pair)throws MyException {
		if(this.contains(((model.Pair) pair).getFileName())== false)
			this.fileTable.put(fd,  pair);
		else
			throw new MyException("File already in the table.\n");
	}

	@Override
	public Pair lookUp(Integer fd) throws MyException {
		if(fileTable.get(fd) != null) {
			return (Pair) fileTable.get(fd);
		}
		
		throw new MyException("File is not open.\n");
	}
	

	@Override
	public boolean contains(String filename) {
		for(HashMap.Entry<Integer, Pair> e : fileTable.entrySet()) {
			Pair p = e.getValue();
			String fn = ((model.Pair) p).getFileName();
			if(fn.equals(filename)==true)
				return true;
		}
		return false;
	}

	@Override
	public int size() {
		return this.fileTable.size();
	}
	
	@Override
	
	public String toString() {
		String dictString = "";
		if(this.fileTable.size() == 0)
			return dictString + "empty.";
		for(HashMap.Entry<Integer, Pair> e : fileTable.entrySet()) {
			dictString = dictString + e.getKey().toString() + " --> " + e.getValue().toString() + "\n";
		}
		return dictString;
	}

	@Override
	public void delete(Integer d) throws MyException {
		if(this.lookUp(d) != null)
			this.fileTable.remove(d);
		else
			throw new MyException("File not open.\n");
	}

	@Override
	public Map<Integer, Pair> getContent() {
		return this.fileTable;
	}
	
}
