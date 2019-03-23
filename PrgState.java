package model;
import java.io.IOException;

import exception.MyException;
import util.*;

public class PrgState {
	private IStatement program;
	private IExecStack<IStatement> stack;
	private ISymTable<String, Integer> symTable;
	private IFileTable<Integer, Pair> fileTable;
	private IHeap<Integer> heap;
	private IOutput<Integer> output;
	private int id;
	
	public PrgState() {}
	public PrgState(IExecStack<IStatement> s, ISymTable<String, Integer> sT, IOutput<Integer> o, IFileTable<Integer, Pair> fT, IHeap<Integer> h,  IStatement prg, int i) {
		this.stack = s;
		this.symTable = sT;
		this.output = o;
		this.fileTable = fT;
		this.heap = h;
		//IStatement prg2 = prg.deepCopy();
		this.program = prg;//prg2
		this.stack.push(prg);
		this.id = i;
	}
	public ISymTable<String, Integer> getSymTable() {
		return symTable;
	}
	public IFileTable<Integer, Pair> getFileTable() {
		return fileTable;
	}
	
	public IExecStack<IStatement> getExecStack() {
		return stack;
	}
	
	public IOutput<Integer> getOutput() {
		return output;
	}
	
	public IHeap<Integer> getHeap() {
		return this.heap;
	}
	
	public IStatement getPrg() {
		return program;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int i) {
		this.id = i;
	}
	
	@Override
	public String toString() {
		
		String display = "";
		display = display + "Program with ID " + new Integer(id).toString() + "\n";
		display = display + "STACK: \n" + stack.toString() + "\n";
		display = display + "SYMTABLE: \n" + symTable.toString() + "\n";
		display = display + "OUTPUT: \n" + output.toString() + "\n" ;
		return display;
	}
	
	public boolean isNotCompleted() {
		if(this.stack.isEmpty())
			return false;
		return true;
	}
	
	public PrgState oneStep()throws MyException, IOException {
		if(this.stack.isEmpty()) throw new MyException("Empty stack!\n");
		//return null;
		IStatement stm = this.stack.pop();
		return stm.execute(this);
	}

	
}
