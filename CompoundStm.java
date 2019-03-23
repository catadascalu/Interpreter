package model;
import util.*;

public class CompoundStm implements IStatement {
	private IStatement first, second;
	
	public CompoundStm(IStatement f, IStatement s) {
		first = f;
		second = s;
	}
	@Override
	
	public String toString() {
		return "(" + first.toString() + "; " + second.toString() + ")";
	}
	@Override
	public PrgState execute(PrgState p)
	{
		IExecStack<IStatement> exec = p.getExecStack();
		exec.push(second);
		exec.push(first);
		return null;
	}
	
	/*
	 @Override
	 public IStatement deepCopy(){
	 
	 return new CompoundStm(first.deepCopy(), second.deepCopy());
	 }
	 */
}
