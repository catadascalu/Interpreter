package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.BlockingQueue;

import exception.MyException;


public class MyOutput<T> implements IOutput<T>{
	List<T> output;

	public MyOutput() {
		output = new ArrayList<>();
	}
	@Override
	public void add(T t) {
		output.add(t);	
	}

	/*
	@Override
	public T pop() throws MyException{
		if(this.output.size() != 0)
			return output.pop();
		else
			throw new MyException("Empty output.");
	}
	*/

	@Override
	public int size() {
		return output.size();
	}	
	@Override
	public void clean() {
		this.output.clear();
	}
	@Override
	public String toString() {
		String listString = "";
		if(this.output.size() == 0)
			return listString + "empty.";
		for(T e : output) {
			listString = listString + e.toString() + "   " + "\n";
		}
		return listString;
	}
	
	@Override
	public List<T> getOutput(){
		return this.output;
	}
}
