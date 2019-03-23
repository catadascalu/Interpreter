package model;
import java.io.BufferedReader;
import java.io.IOException;

import exception.MyException;
import util.*;
public class closeStm implements IStatement {
	IExpression exp_file_id;
	
	public closeStm(IExpression exp) {
		this.exp_file_id = exp;
	}
	
	@Override
	
	public String toString() {
		return this.exp_file_id.toString();
	}
	@Override
	public PrgState execute(PrgState prg) {
		//IExecStack<IStatement> stack = prg.getExecStack();
		ISymTable<String, Integer> table = prg.getSymTable();
		IFileTable<Integer, Pair> fileTable = prg.getFileTable();
		IHeap<Integer> heap = prg.getHeap();
		
		try {
			
			int value = this.exp_file_id.evaluate(table, heap);
			Pair p = fileTable.lookUp(new Integer(value));
			BufferedReader bf = p.getBF();
			bf.close();
			fileTable.delete(new Integer(value));
		} catch (MyException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
