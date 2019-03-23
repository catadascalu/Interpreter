package model;
import util.*;
import exception.MyException;
public class AssignmentStm implements IStatement{
	private String var;
	private IExpression exp;
	
	public AssignmentStm(String var, IExpression exp){
		this.var = var;
		this.exp = exp;
	}
	@Override
	
	public String toString() {
		return var + " = " + exp.toString();
	}
	@Override
	public PrgState execute(PrgState p) {
		IExecStack<IStatement> stack = p.getExecStack();
		/*try {
		stack.pop();
		}
		catch(MyException e) {
			System.out.println(e.getMessage());
		}*/
		ISymTable<String, Integer> symTable = p.getSymTable();
		IHeap<Integer> heap = p.getHeap();
		int result = 0;
		try {
			result = exp.evaluate(symTable, heap);
		}
		catch(MyException e) {
			System.out.println(e.getMessage());
		}
		if(symTable.isDefined(var)) {
			symTable.update(var, result);
		}
		else {
			symTable.add(var, result);
		}
		return null;
	}
}
