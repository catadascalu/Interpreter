package model;
import exception.MyException;
import util.*;

public class PrintStm implements IStatement{
	private IExpression expr;

	
	public PrintStm(IExpression e) {
		expr = e;
	}
	@Override
	
	public String toString() {
		return "print(" + expr.toString() + ")";
	}

	@Override
	public PrgState execute(PrgState prg) {
		IOutput<Integer> output = prg.getOutput();
		ISymTable<String, Integer> t = prg.getSymTable();
		IHeap<Integer> heap = prg.getHeap();
		try {
		output.add(expr.evaluate(t, heap));
		}
		catch(MyException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
}
