package util;

import java.util.Stack;

import exception.MyException;

public class MyStack<T> implements IExecStack<T>{
	Stack<T> stack;
	public MyStack() {
		stack = new Stack<T>();
	}
	
	
	@Override
	public void push(T stm) {
		this.stack.push(stm);	
	}


	@Override
	public boolean isEmpty() {
		return stack.isEmpty();
	}


	@Override
	public T pop() throws MyException{
		if(this.stack.size() != 0)
			return stack.pop();
		else {
			throw new MyException("Empty stack.");
		}
	}



	@Override
	public int size() {
		return stack.size();
	}
	
	@Override
	public Stack<T> getStack(){
		return this.stack;
	}
	
	@Override
	
	public String toString() {
		String stack = "";
		if(this.stack.size() == 0)
			return stack + " empty.";
		for(T e : this.stack) 
			stack = stack + e.toString() + "    " + "\n";
		
		return stack;
	}

}
