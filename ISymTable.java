package util;

import java.util.Map;

//import java.util.HashMap;

import exception.MyException;

public interface ISymTable<T1, T2> {
	void clean();
	void add(T1 name, T2 var);
	void update(T1 v1, T2 v2);
	T2 lookUp(T1 id) throws MyException;
	boolean find(T1 name);
	boolean contains(T1 v);
	boolean isDefined(String id);
	int getValueT(T1 name);
	void setValueT(T1 name, T2 var);
	int size();
	String toString();
	Map<T1, T2> getContent();
	ISymTable<T1, T2> copy();
}
