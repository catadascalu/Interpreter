package model;
import util.*;

public class ConstExpr implements IExpression{
	private int value;
	
	public ConstExpr(int value) {
		this.value = value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
	
	@Override
	public int evaluate(ISymTable<String, Integer> t, IHeap<Integer> heap) {
		return value;
	}
}
