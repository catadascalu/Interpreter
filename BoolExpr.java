package model;

import exception.MyException;
import util.IHeap;
import util.ISymTable;

public class BoolExpr implements IExpression {
	private String operator;
	private IExpression operand1, operand2;
	
	public BoolExpr(String operator, IExpression op1, IExpression op2){
		this.operator = operator;
		this.operand1 = op1;
		this.operand2 = op2;
	}
	
	@Override
	public String toString() {
		return "(" + operand1.toString() + " " + operator + " " + operand2.toString() + ") ";
	}
	
	
	@Override
	public int evaluate(ISymTable<String, Integer> t, IHeap<Integer> h) throws MyException {
		int result1 = operand1.evaluate(t, h);
		int result2 = operand2.evaluate(t, h);
		switch(operator) {
		case "<":
			{
				if(result1 < result2)
					return 1;
				else
					return 0;
			}
		case "<=":
		{
			if(result1 <= result2)
				return 1;
			else
				return 0;
		}		case "==":
		{
			if(result1 == result2)
				return 1;
			else
				return 0;
		}
		case "!=":
		{
			if(result1 != result2)
				return 1;
			else
				return 0;
		}
		case ">":
		{
			if(result1 > result2)
				return 1;
			else
				return 0;
		}
		case ">=":
		{
			if(result1 >= result2)
				return 1;
			else
				return 0;
		}
		default:
			throw new MyException("Invalid operator");
		}
	}

}
