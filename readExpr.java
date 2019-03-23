package model;

import exception.MyException;
import util.IHeap;
import util.ISymTable;

public class readExpr implements IExpression {
	private String var_name;
	
	public readExpr(String v) {
		this.var_name = v;
	}
	
	@Override
	public int evaluate(ISymTable<String, Integer> t, IHeap<Integer> h) throws MyException {
		int value = t.lookUp(var_name);
		int content = h.lookUp(new Integer(value));
		return content;
	}
	
	@Override
	public String toString() {
		String str = "";
		str = "read " + this.var_name + " ";
		return str;
	}

}
