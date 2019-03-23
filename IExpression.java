package model;
import util.*;
import exception.MyException;
public interface IExpression {
	int evaluate(ISymTable<String, Integer> t, IHeap<Integer> h)throws MyException;
}
