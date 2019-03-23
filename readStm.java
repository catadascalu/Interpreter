package model;

import java.io.BufferedReader;
import java.io.IOException;

import exception.MyException;
import util.IExecStack;
import util.IFileTable;
import util.IHeap;
import util.ISymTable;

public class readStm implements IStatement{
	IExpression exp_file_id;
	String var_name;
	
	public readStm(IExpression e, String vn) {
		this.exp_file_id = e;
		this.var_name = vn;
	}
	
	@Override
	public String toString() {
		return var_name + " --> " + exp_file_id.toString();
	}
	@Override
	public PrgState execute(PrgState prg)throws MyException, IOException {
		//IExecStack<IStatement> stack = prg.getExecStack();
		ISymTable<String, Integer> table = prg.getSymTable();
		IFileTable<Integer, Pair> fileTable = prg.getFileTable();
		IHeap<Integer> heap = prg.getHeap();
		//try {
			int e = this.exp_file_id.evaluate(table, heap);
			Pair p = fileTable.lookUp(new Integer(e));
			BufferedReader bf = p.getBF();
			String read = bf.readLine();
			int value;
			if(read == null)
				value = 0;
			else
				value = Integer.parseInt(read);
			if(table.contains(var_name))
				table.update(var_name, value);
			else
				table.add(var_name, value);
		/*} catch (MyException e) {
			System.out.println(e.getMessage());
			//System.exit(0);
			 * */
			 
		/*} catch (IOException e1) {
			e1.printStackTrace();
		}*/
		return null;
	}

}
