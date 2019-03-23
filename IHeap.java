package util;

import java.util.Map;

import exception.MyException;

public interface IHeap<Integer>{
	void add(Integer address, Integer content) throws MyException;
	Integer lookUp(Integer address);
	int size();
	String toString();
	int getFirstFree();
	void setFirstFree(int firstFree) throws MyException;
	//Integer getValue(Integer address);
	void update(Integer address, Integer content) throws MyException;
	boolean contains(Integer address);
	void setContent(Map<Integer, Integer> map);
	Map<Integer, Integer> getContent();
}
