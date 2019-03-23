package model;

import java.io.IOException;

import exception.MyException;
import util.IHeap;
import util.ISymTable;

public class writeStm implements IStatement {
	private String var_name;
	private IExpression expr;
	
	public writeStm(String v, IExpression e) {
		this.var_name = v;
		this.expr = e;
	}
	@Override
	public PrgState execute(PrgState prg) throws MyException, IOException {
		ISymTable<String, Integer> table = prg.getSymTable();
		IHeap<Integer> heap = prg.getHeap();
		
		int value = this.expr.evaluate(table, heap);
		int address = table.lookUp(var_name);
		heap.update(address, value);
		return null;
	}
	
	@Override 
	public String toString() {
		String str = "";
		str = "write " + this.expr.toString() + " into " + this.var_name + " ";
		return str;
	}

}
