package model;

import java.io.IOException;

import exception.MyException;
import util.IHeap;
import util.ISymTable;

public class newStm implements IStatement {
	private String var_name;
	private IExpression expr;
	
	public newStm(String var, IExpression e) {
		this.var_name = var;
		this.expr = e;
	}
	@Override
	public PrgState execute(PrgState prg) throws MyException, IOException {
		IHeap<Integer> heap = prg.getHeap();
		ISymTable<String, Integer> table = prg.getSymTable();
		int address = heap.getFirstFree();
		int value = this.expr.evaluate(table, heap);
		heap.add(new Integer(address), new Integer(value));
		if(table.contains(this.var_name))
			table.update(var_name, address);
		else
			table.add(var_name, address);
		heap.setFirstFree(address+1);
		return null;
	}
	
	@Override
	
	public String toString() {
		String str = "";
		str = this.var_name + " ---> " + this.expr.toString();
		return str;
	}

}
