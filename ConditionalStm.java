package model;
import exception.MyException;
import util.*;

public class ConditionalStm implements IStatement{
	private IExpression exp;
	private IStatement thenS;
	private IStatement elseS;
	
	public ConditionalStm(IExpression e, IStatement s1, IStatement s2) {
		exp = e;
		thenS = s1;
		elseS = s2;
	}
	@Override
	public String toString() {
		return "IF(" + exp.toString()+ " THEN(" + thenS.toString() + ") ELSE(" + elseS.toString() + ")";
	}

	
	@Override
	public PrgState execute(PrgState prg) {
		ISymTable<String, Integer> t = prg.getSymTable();
		IExecStack<IStatement> stack = prg.getExecStack();
		IHeap<Integer> heap = prg.getHeap();
		try {
		if(exp.evaluate(t, heap) != 0) {
			stack.push(thenS);
		}
		else {
			stack.push(elseS);
		}
		}
		catch(MyException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	
}
