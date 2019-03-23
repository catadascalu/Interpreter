package util;

import java.util.ArrayList;
import java.util.List;

import exception.MyException;

public interface IOutput<T> {
	void clean();
	void add(T t);
	//T pop()throws MyException;
	String toString();
	int size();
	List<T> getOutput();
}
