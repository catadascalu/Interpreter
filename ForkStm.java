package model;

import java.io.IOException;

import exception.MyException;
import util.IExecStack;
import util.ISymTable;
import util.MyStack;
import util.MySymTable;

public class ForkStm implements IStatement {
	IStatement stm;
	static int counter = 0;
	public ForkStm(IStatement s) { this.stm = s; }
	
	@Override
	public String toString() {
		return "FORK( " + stm.toString() + " ) \n";
	}
	@Override
	public PrgState execute(PrgState prg) throws MyException, IOException {
		
		ISymTable<String, Integer> newTable = prg.getSymTable().copy();
	
		PrgState newPS = new PrgState(new MyStack<IStatement>(), newTable, prg.getOutput(), prg.getFileTable(), prg.getHeap(), this.stm, prg.getId()*10 + counter);
		
		counter = counter + 1;
		return newPS;
	}

}
