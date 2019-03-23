package util;

import java.util.Stack;

//import java.util.Stack;

import exception.MyException;

public interface IExecStack<T> {
	void push(T v);
	T pop()throws MyException;
	boolean isEmpty();
	String toString();
	int size();
	Stack<T> getStack();
}
