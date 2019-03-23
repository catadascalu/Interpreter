package model;
import exception.MyException;
import util.*;

public class VarExpr implements IExpression{
	private String name;
	public VarExpr(String name){
		this.name = name;
	}
	public String toString() {
		return name;
	}
	
	@Override
	public int evaluate(ISymTable<String, Integer> t, IHeap<Integer> heap)throws MyException {
		/*if(t.find(name)) {
			return t.getValueT(name);
		} else {
			throw new RuntimeException("symTable does not " + "contain the variable");
		}
		*/
		//try {
		return t.lookUp(name);
		
		//}
		//catch(MyException e) {
			
			//System.out.println(e.getMessage());
			//return (Integer) null;
		//}
	}
}
