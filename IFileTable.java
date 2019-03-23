package util;
import java.util.Map;

import exception.MyException;
import model.Pair;
public interface IFileTable<Integer, Pair> {
	void clean();
	void add(Integer i, Pair pair)throws MyException;
	Pair lookUp(Integer fd) throws MyException;
	boolean contains(String fileName);
	int size();
	String toString();
	int getDescriptor();
	void setDescriptor(int d);
	void delete(Integer d)throws MyException;
	Map<Integer, Pair> getContent();
}
