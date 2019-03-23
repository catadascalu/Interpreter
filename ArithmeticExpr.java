package model;
import util.*;
import exception.MyException;
public class ArithmeticExpr implements IExpression{
	private char operator;
	private IExpression operand1, operand2;
	
	public ArithmeticExpr(char operator, IExpression op1, IExpression op2){
		this.operator = operator;
		this.operand1 = op1;
		this.operand2 = op2;
	}
	
	@Override
	public String toString() {
		return operand1.toString() + " " + operator + " " + operand2.toString();
	}
	
	@Override
	public int evaluate(ISymTable<String, Integer> t, IHeap<Integer> h) throws MyException{
		int result1 = operand1.evaluate(t, h);
		int result2 = operand2.evaluate(t, h);
		switch(operator) {
		case '+':
			return result1 + result2;
		case '-':
			return result1 - result2;
		case '*':
			return result1*result2;
		case '/':{
			if(result2 == 0) {
				throw new MyException("Cannot divide by 0");
			} else {
				return result1/result2;
			}
		}
		default:
			throw new MyException("Invalid operator");
		}
	}
}
