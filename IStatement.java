package model;
//import util.*;

import java.io.IOException;

import exception.MyException;

public interface IStatement {
	PrgState execute(PrgState prg) throws MyException, IOException;
	//to do : IStatement deepCopy();
	//int evaluate(ISymTable<String, Integer> t);
}
