package model;

import java.io.IOException;

import exception.MyException;
import util.IExecStack;
import util.IHeap;
import util.ISymTable;

public class whileStm implements IStatement {
	IExpression exp;
	IStatement stm;
	
	public whileStm(IExpression e, IStatement s) {
		this.exp = e;
		this.stm = s;
	}
	
	@Override
	public String toString() {
		return "while(" + this.exp.toString() + ") , " + this.stm.toString() +" "; 
	}
	@Override
	public PrgState execute(PrgState prg) throws MyException, IOException {
		IExecStack<IStatement> stack = prg.getExecStack();
		ISymTable<String, Integer> table = prg.getSymTable();
		IHeap<Integer> heap = prg.getHeap();
		if(this.exp.evaluate(table, heap) == 1) {
			stack.push(this);
			stack.push(this.stm);
		}
		
		return null;
	}

}
